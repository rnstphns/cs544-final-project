package edu.miu.cs544.backend.service;

import edu.miu.cs544.backend.domain.*;
import edu.miu.cs544.backend.exceptions.DatabaseException;
import edu.miu.cs544.backend.repositories.RegistrationRequestRepository;
import edu.miu.cs544.backend.repositories.StudentRepository;
import edu.miu.cs544.backend.exceptions.EventNotOpenException;
import edu.miu.cs544.backend.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class RegistrationRequestServiceImpl implements RegistrationRequestService{

    @Autowired
    private RegistrationRequestRepository registrationRequestRepository;

    @Autowired
    private RegistrationEventService registrationEventService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseOfferingService courseOfferingService;

    @Override
    public RegistrationRequest getRegistrationRequestById(Long id) {
        return registrationRequestRepository.findById(id).get();
    }

    @Override
    public Collection<RegistrationRequest> getRegistrationRequests() {
        return registrationRequestRepository.findAll();
    }

    @Override
    public boolean createRegistrationRequest(List<RegistrationRequest> requests, Integer studentID) throws EventNotOpenException, ObjectNotFoundException, DatabaseException {
            Set<Long> offeringID = new HashSet<>();
            boolean savedSuccessfully = false;
            RegistrationRequest registrationRequestReturn = null;
            RegistrationEvent latestRegistrationEvent = registrationEventService.latest();

        if(isOpen()) {

            Collection<RegistrationGroup> registrationGroups = latestRegistrationEvent.getRegistrationGroups();

            for(RegistrationGroup group : registrationGroups){

                Collection<CourseOffering> courseOfferings = group.getCourses();

                for(CourseOffering course : courseOfferings){
                    offeringID.add(course.getId());

                }
            }

        }
        for(RegistrationRequest request : requests){
            Student student = studentRepository.findByStudentId(studentID).get();
            request.setStudent(student);
            List<CourseOffering> courseOfferings = request.getCourseList();
            List<CourseOffering> validatedList = new ArrayList<>();
            for(CourseOffering course : courseOfferings){
                if(offeringID.contains(course.getId())){
                    validatedList.add(course);
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

//    protected boolean isOpen(){
//        LocalDate now = LocalDate.now();
//        RegistrationEvent currentEvent = registrationEventService.latest();
//        if(now.isAfter(ChronoLocalDate.from(currentEvent.getStartDate().atStartOfDay()).minus(Period.ofDays(1))) &&
//                now.isBefore(ChronoLocalDate.from(currentEvent.getEndDate().atTime(LocalTime.parse("11:59pm")))))
//            return true;
//        else return false;
//    }
        //TODO turned off real check for testing purposes
    protected boolean isOpen(){
        return true;
    }



}
