package edu.miu.compro.cs544.CS544FinalProjectGroup8.service;

import edu.miu.compro.cs544.CS544FinalProjectGroup8.domain.Registration;
import edu.miu.compro.cs544.CS544FinalProjectGroup8.domain.RegistrationEvent;
import edu.miu.compro.cs544.CS544FinalProjectGroup8.domain.RegistrationGroup;
import edu.miu.compro.cs544.CS544FinalProjectGroup8.domain.RegistrationRequest;
import edu.miu.compro.cs544.CS544FinalProjectGroup8.repositories.RegistrationEventRepository;
import edu.miu.compro.cs544.CS544FinalProjectGroup8.repositories.RegistrationGroupRepository;
import edu.miu.compro.cs544.CS544FinalProjectGroup8.repositories.RegistrationRepository;
import edu.miu.compro.cs544.CS544FinalProjectGroup8.repositories.RegistrationRequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class RegistrationEventServiceImpl implements RegistrationEventService {

    @Autowired
    private RegistrationEventRepository registrationEventRepository;
    @Autowired
    private RegistrationGroupRepository registrationGroupRepository;
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private RegistrationRequestRepository registrationRequestRepository;

    //TODO email student when their registration is saved

    @Override
    public Collection<RegistrationGroup> findRegistrationGroupsWithStudent(String studentId) {
        //TODO implement this or repo level query
//        Collection<RegistrationGroup> registrationGroups = registrationGroupRepository.findAll();
//        Collection<RegistrationGroup> filteredRegistrationGroup = registrationGroups.stream()
//                .filter(rg ->
//                    rg.getStudents()
//                            .stream()
//                            .filter(student -> (student.getStudentId().equals(studentId)))
//                            .map(s -> s.)
//                ).collect(Collectors.toList());
//        return filteredRegistrationGroup;
        return null;
    }

    @Override
    public RegistrationEvent getRegistrationEventById(long id) {
        return registrationEventRepository.findById(id).get();
    }

    @Override
    public Collection<RegistrationEvent> getRegistrationEvents() {
        return registrationEventRepository.findAll();
    }

    @Override
    public RegistrationEvent createRegistrationEvent(RegistrationEvent registrationEvent) {
        return registrationEventRepository.save(registrationEvent);
    }

    @Override
    public void deleteRegistrationEvent(long id) {
        registrationEventRepository.deleteById(id);
    }

    @Override
    public boolean updateWindow(long id, LocalDate start, LocalDate end) {
        Optional<RegistrationEvent> registrationEventOptional = registrationEventRepository.findById(id);
        if(registrationEventOptional.isPresent()) {
            RegistrationEvent registrationEvent = registrationEventOptional.get();
            registrationEvent.setStartDate(start);
            registrationEvent.setEndDate(end);
            registrationEventRepository.save(registrationEvent);
            return true;
        }
        else return false;
    }

    @Override
    public RegistrationRequest getRegistrationRequestById(long id) {
        return registrationRequestRepository.findById(id).get();
    }

    @Override
    public Collection<RegistrationRequest> getRegistrationRequests() {
        return registrationRequestRepository.findAll();
    }

    @Override
    public RegistrationRequest createRegistrationRequest(RegistrationRequest registrationRequest) {
        return registrationRequestRepository.save(registrationRequest);
    }

    @Override
    public void deleteRegistrationRequest(long id) {
        registrationRequestRepository.deleteById(id);
    }

    @Override
    public boolean updateRegistrationRequest(long id, RegistrationRequest registrationRequest) {
        Optional<RegistrationRequest> registrationRequestOptional = registrationRequestRepository.findById(id);
        if(registrationRequestOptional.isPresent()) {
            RegistrationRequest savedRegistrationRequest = registrationRequestOptional.get();
            savedRegistrationRequest.setCourseList(registrationRequest.getCourseList());
            savedRegistrationRequest.setStudent(registrationRequest.getStudent());
            registrationRequestRepository.save(savedRegistrationRequest);
            return true;
        }
        else return false;
    }

    @Override
    public Registration getRegistrationById(long id) {
        return registrationRepository.findById(id).get();
    }

    @Override
    public Collection<Registration> getRegistrations() {
        return registrationRepository.findAll();
    }

    @Override
    public Registration createRegistration(Registration registration) {
        return registrationRepository.save(registration);
    }

    @Override
    public void deleteRegistration(long id) {
        registrationRepository.deleteById(id);
    }

    @Override
    public boolean updateRegistration(long id, Registration registration) {
        Optional<Registration> registrationOptional = registrationRepository.findById(id);
        if(registrationOptional.isPresent()) {
            Registration savedRegistration = registrationOptional.get();
            savedRegistration.setCourseList(registration.getCourseList());
            savedRegistration.setStudent(registration.getStudent());
            registrationRepository.save(savedRegistration);
            return true;
        }
        else return false;
    }
    @Override
    public RegistrationGroup getRegistrationGroupById(long id) {
        return registrationGroupRepository.findById(id).get();
    }

    @Override
    public Collection<RegistrationGroup> getRegistrationGroups() {
        return registrationGroupRepository.findAll();
    }

    @Override
    public RegistrationGroup createRegistrationGroup(RegistrationGroup registrationGroup) {
        return registrationGroupRepository.save(registrationGroup);
    }

    @Override
    public void deleteRegistrationGroup(long id) {
        registrationGroupRepository.deleteById(id);
    }

    @Override
    public boolean updateRegistrationGroup(long id, RegistrationGroup registrationGroup) {
        Optional<RegistrationGroup> registrationOptional = registrationGroupRepository.findById(id);
        if(registrationOptional.isPresent()) {
            RegistrationGroup savedRegistrationGroup = registrationOptional.get();
            savedRegistrationGroup.setStudents(registrationGroup.getStudents());
            savedRegistrationGroup.setCourses(registrationGroup.getCourses());
            registrationGroupRepository.save(savedRegistrationGroup);
            return true;
        }
        else return false;
    }
}
