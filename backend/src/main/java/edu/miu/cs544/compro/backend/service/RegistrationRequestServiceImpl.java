package edu.miu.cs544.compro.backend.service;

import edu.miu.cs544.backend.domain.*;
import edu.miu.cs544.compro.backend.domain.*;
import edu.miu.cs544.compro.backend.exceptions.DatabaseException;
import edu.miu.cs544.compro.backend.repositories.RegistrationEventRepository;
import edu.miu.cs544.compro.backend.repositories.RegistrationRequestRepository;
import edu.miu.cs544.compro.backend.repositories.StudentRepository;
import edu.miu.cs544.compro.backend.exceptions.EventNotOpenException;
import edu.miu.cs544.compro.backend.exceptions.ObjectNotFoundException;
import edu.miu.cs544.compro.backend.util.RegistrationEventUtilities;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
@Slf4j
public class RegistrationRequestServiceImpl implements RegistrationRequestService{

    @Autowired
    private RegistrationRequestRepository registrationRequestRepository;

    @Autowired
    private RegistrationEventRepository registrationEventRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseOfferingService courseOfferingService;

    @Override
    public RegistrationRequest getRegistrationRequestById(Long id) throws ObjectNotFoundException {
        Optional<RegistrationRequest> found =registrationRequestRepository.findById(id);
        if(found.isPresent())
            return found.get();
        else throw new ObjectNotFoundException("Registration request is not in the databse");
    }

    @Override
    public Collection<RegistrationRequest> getRegistrationRequests() {
        return registrationRequestRepository.findAll();
    }

    @Override
    public boolean createRegistrationRequest(List<RegistrationRequest> requests, Integer studentID) throws EventNotOpenException, ObjectNotFoundException, DatabaseException {
            Set<String> offeringCodes = new HashSet<>();
            boolean savedSuccessfully = false;
            RegistrationRequest registrationRequestReturn = null;

            LocalDate localDateNow = LocalDate.now();
            List<RegistrationEvent> registrationEventList = registrationEventRepository.findAll(Sort.by(Sort.Direction.DESC, "endDate"));
            RegistrationEvent latestRegistrationEvent = new RegistrationEvent();
            try {
                latestRegistrationEvent = registrationEventList.get(0);
            } catch (IndexOutOfBoundsException e) {
                log.error("No events in the database");
            }

        if(RegistrationEventUtilities.isOpen(latestRegistrationEvent)) {

            Collection<RegistrationGroup> registrationGroups = latestRegistrationEvent.getRegistrationGroups();

            for(RegistrationGroup group : registrationGroups){

                Collection<CourseOffering> courseOfferings = group.getCourses();

                for(CourseOffering course : courseOfferings){
                    offeringCodes.add(course.getCode());

                }
            }

        }
        for(RegistrationRequest request : requests){
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+request.toString());
            Student student = studentRepository.findByStudentId(studentID).get();
            request.setStudent(student);
            List<CourseOffering> courseOfferings = request.getCourseList();
            List<CourseOffering> validatedList = new ArrayList<>();
            for(CourseOffering course : courseOfferings){
                if(offeringCodes.contains(course.getCode())){
                    validatedList.add(courseOfferingService.findByCode(course.getCode()));
                }
                request.setCourseList(validatedList);
            }
            registrationRequestRepository.save(request);
            savedSuccessfully = true;
        }

            if(savedSuccessfully){
               System.out.println("The request is successfully saved");
            }
            else{
                System.out.println("The request was not saved");
            }

        return savedSuccessfully;
    }

    @Override
    public void deleteRegistrationRequest(Long id) {
        registrationRequestRepository.deleteById(id);
    }




}
