package edu.miu.cs544.backend.service;


import edu.miu.cs544.backend.domain.*;
import edu.miu.cs544.backend.exceptions.DatabaseException;
import edu.miu.cs544.backend.exceptions.ObjectNotFoundException;
import edu.miu.cs544.backend.kafka.EmailKafkaSender;
import edu.miu.cs544.backend.repositories.RegistrationEventRepository;
import edu.miu.cs544.backend.util.RegistrationEventUtilities;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class RegistrationEventServiceImpl implements RegistrationEventService {
    @Autowired
    private RegistrationGroupService registrationGroupService;
    @Autowired
    private RegistrationEventRepository registrationEventRepository;

    @Autowired
    private RegistrationRequestService registrationRequestService;

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private CourseOfferingService courseOfferingService;
    @Autowired
    private EmailKafkaSender emailKafkaSender;

    @Override
    public RegistrationEvent getRegistrationEventById(long id) throws ObjectNotFoundException {
            Optional<RegistrationEvent> found = registrationEventRepository.findById(id);
            if(found.isPresent())
                return found.get();
            else throw new ObjectNotFoundException("RegistrationEvent is not in database");
    }

    @Override
    public Collection<RegistrationEvent> getRegistrationEvents() {
        return registrationEventRepository.findAll();
    }

    @Override
    public RegistrationEvent createRegistrationEvent(RegistrationEvent registrationEvent) throws DatabaseException{
        Collection<RegistrationGroup> groups = registrationEvent.getRegistrationGroups();
        for (RegistrationGroup g : groups) {
                registrationGroupService.create(g);
        }
        return registrationEventRepository.save(registrationEvent);
    }

    @Override
    public void deleteRegistrationEvent(Long id) {
        registrationEventRepository.deleteById(id);
    }

    @Override
    public RegistrationEvent updateWindow(Long id, LocalDate start, LocalDate end) {
        Optional<RegistrationEvent> registrationEventOptional = registrationEventRepository.findById(id);
        if(registrationEventOptional.isPresent()) {
            RegistrationEvent registrationEvent = registrationEventOptional.get();
            registrationEvent.setStartDate(start);
            registrationEvent.setEndDate(end);
            registrationEventRepository.save(registrationEvent);
            return registrationEvent;
        }
        else return null;
    }
    @Override
    public RegistrationEvent latest() {
        LocalDate localDateNow = LocalDate.now();
        List<RegistrationEvent> registrationEventList = registrationEventRepository.findAll(Sort.by(Sort.Direction.DESC, "endDate"));
        RegistrationEvent returnEvent = new RegistrationEvent();
        try {
            returnEvent = registrationEventList.get(0);
        } catch (IndexOutOfBoundsException e) {
            log.error("No events in the database");
        }
        Collection<RegistrationGroup> groups = returnEvent.getRegistrationGroups();
        for (RegistrationGroup g : groups) {
            g.setStudents(new ArrayList<Student>());
        }
        returnEvent.setRegistrationGroups(groups);
        return returnEvent;
    }

    @Override
    public boolean processEvent(Long id) {
        boolean processedSuccessfully = false;
        try {
            RegistrationEvent latestEvent = getRegistrationEventById(id);
        if(RegistrationEventUtilities.isOpen(latestEvent)) {
            Collection<RegistrationGroup> allRegistrationGroups = latestEvent.getRegistrationGroups();
            for (RegistrationGroup r : allRegistrationGroups) {
                Collection<CourseOffering> allCourseOfferings = r.getCourses();
                for (CourseOffering co : allCourseOfferings) {
                    Collection<RegistrationRequest> requests = registrationRequestService.getRegistrationRequests();
                    for (RegistrationRequest rr : requests) {
                        if (co.getAvailableSeats() > 0 && rr.getCourseList().contains(co)) {
                            try {
                                Registration registration = processRegistration(rr, co);
                            } catch (DatabaseException de) {
                                log.error("Registration for student " + rr.getStudent() + " has failed"+de.getMessage());
                            }
                        }
                    }
                }
                Collection<Student> allStudents = r.getStudents();
                for(Student s : allStudents){
                    emailKafkaSender.send(s);
                }
            }
            processedSuccessfully = true;
        }

        }catch(ObjectNotFoundException e){
            log.error("Caught request to process registrationEvent"+id+"that is not in database"+e.getMessage());
        }
        return processedSuccessfully;
    }

    private Registration processRegistration(RegistrationRequest rr, CourseOffering co) throws DatabaseException {
        Registration registration = new Registration();
        registration.setStudent(rr.getStudent());
        if(co.getAvailableSeats()>0) {
            Integer seats = co.getAvailableSeats();
            seats--;
            co.setAvailableSeats(seats);
            courseOfferingService.update(co.getId(), co);
        }else throw new DatabaseException("Course Offering"+co.getCode()+" is full!");
        registration.getCourseList().add(co);
        registrationService.createRegistration(registration);
        return registration;
    }


}
