package edu.miu.cs544.backend.service;


import edu.miu.cs544.backend.Repositories.*;
import edu.miu.cs544.backend.domain.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class RegistrationEventServiceImpl implements RegistrationEventService {

    @Autowired
    private RegistrationEventRepository registrationEventRepository;
    @Autowired
    private RegistrationGroupRepository registrationGroupRepository;
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private RegistrationRequestRepository registrationRequestRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseOfferingRepository courseOfferingRepository;
    @Autowired
    AcademicBlockRepository academicBlockRepository;
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    private CourseOfferingServiceImpl courseOfferingService;

    //TODO email student when their registration is saved
    //TODO fix update methods


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
        Collection<RegistrationGroup> groups = registrationEvent.getRegistrationGroups();
        for (RegistrationGroup g : groups) {
            registrationGroupRepository.save(g);
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
    public RegistrationRequest getRegistrationRequestById(Long id) {
        return registrationRequestRepository.findById(id).get();
    }

    @Override
    public Collection<RegistrationRequest> getRegistrationRequests() {
        return registrationRequestRepository.findAll();
    }

    @Override
    public RegistrationRequest createRegistrationRequest(RegistrationRequest registrationRequest) {
        Student s = registrationRequest.getStudent();
        studentRepository.save(s);
        return registrationRequestRepository.save(registrationRequest);
    }

    @Override
    public void deleteRegistrationRequest(Long id) {
        registrationRequestRepository.deleteById(id);
    }

    @Override
    public boolean updateRegistrationRequest(Long id, RegistrationRequest registrationRequest) {
//        Optional<RegistrationRequest> registrationRequestOptional = registrationRequestRepository.findById(id);
//        if(registrationRequestOptional.isPresent()) {
//            RegistrationRequest savedRegistrationRequest = registrationRequestOptional.get();
//            savedRegistrationRequest.setCourseList(registrationRequest.getCourseList());
//            savedRegistrationRequest.setStudent(registrationRequest.getStudent());
//            registrationRequestRepository.save(savedRegistrationRequest);
//            return true;
//        }
        return false;
    }

    @Override
    public Registration getRegistrationById(Long id) {
        return registrationRepository.findById(id).get();
    }

    public Collection<Registration> getRegistrationsByStudentId(Integer studentId){
        return registrationRepository.findByStudentId(studentId);
    }

    @Override
    public Collection<Registration> getRegistrations() {
        return registrationRepository.findAll();
    }

    @Override
    public Registration createRegistration(Registration registration) {
        List<CourseOffering> courseOfferings = registration.getCourseList();
        for (CourseOffering c: courseOfferings) {
            courseOfferingService.createCourseOffering(c);
        }
        return registrationRepository.save(registration);
    }

    @Override
    public void deleteRegistration(Long id) {
        registrationRepository.deleteById(id);
    }

    @Override
    public boolean updateRegistration(Long id, Registration registration) {
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
    public RegistrationGroup getRegistrationGroupById(Long id) {
        return registrationGroupRepository.findById(id).get();
    }

    @Override
    public Collection<RegistrationGroup> getRegistrationGroups() {
        return registrationGroupRepository.findAll();
    }

    @Override
    public RegistrationGroup createRegistrationGroup(RegistrationGroup registrationGroup) {
        Collection<Student> students = registrationGroup.getStudents();
        Collection<CourseOffering> courses = registrationGroup.getCourses();
        for (Student s: students) {
            studentRepository.save(s);
        }
        for (CourseOffering c: courses) {
            courseOfferingRepository.save(c);
        }
        return registrationGroupRepository.save(registrationGroup);
    }

    @Override
    public void deleteRegistrationGroup(Long id) {
        registrationGroupRepository.deleteById(id);
    }

    @Override
    public boolean updateRegistrationGroup(Long id, RegistrationGroup registrationGroup) {
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

    @Override
    public RegistrationEvent latest() {
        LocalDate localDateNow = LocalDate.now();
        List<RegistrationEvent> registrationEventList = registrationEventRepository.findAll(Sort.by(Sort.Direction.DESC,"endDate"));
        return registrationEventList.get(0);
    }
}
