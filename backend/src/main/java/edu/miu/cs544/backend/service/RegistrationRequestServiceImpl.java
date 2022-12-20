package edu.miu.cs544.backend.service;

import edu.miu.cs544.backend.domain.*;
import edu.miu.cs544.backend.exceptions.DatabaseException;
import edu.miu.cs544.backend.repositories.RegistrationRequestRepository;
import edu.miu.cs544.backend.repositories.StudentRepository;
import edu.miu.cs544.backend.exceptions.EventNotOpenException;
import edu.miu.cs544.backend.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public RegistrationRequest createRegistrationRequest(List<RegistrationRequest> registrationRequest, Integer studentID) throws EventNotOpenException, ObjectNotFoundException, DatabaseException {

            Set<Long> offeringID = new HashSet<>();
            boolean savedSuccessfully = false;
            RegistrationRequest registrationRequestReturn = null;
            Student student = studentRepository.findByStudentId(studentID).get();
            RegistrationEvent latestRegistrationEvent = registrationEventService.latest();

        if(isOpen()) {

            Collection<RegistrationGroup> registrationGroups = latestRegistrationEvent.getRegistrationGroups();

            for(RegistrationGroup  group : registrationGroups){

                Collection<CourseOffering> courseOfferings = group.getCourses();

                for(CourseOffering course : courseOfferings){
                    offeringID.add(course.getId());

                }
            }

        }

        for (RegistrationRequest request : registrationRequest ){

            List<CourseOffering> courseOfferings = request.getCourseList();
            for(CourseOffering course : courseOfferings){

                if(offeringID.contains(course.getId())){
                    request.setStudent(student);
                    registrationRequestRepository.save(request);
                    savedSuccessfully = true;
                    registrationRequestReturn = request;
                }
            }
            if(savedSuccessfully){
               System.out.println("The request is successfully saved");
            }
            else{
                System.out.println("The request was not saved");
            }
        }

//
//            Optional<Student> exists = studentRepository.findByStudentId(registrationRequest.getStudent().getStudentId());
//            if(exists.isEmpty()) {
//                throw new ObjectNotFoundException("Student "+student.getStudentId()+" is not in the database");
//            }
//            registrationRequest.setStudent(exists.get());
//            List<CourseOffering> requestedCourses = registrationRequest.getCourseList();
//            List<CourseOffering> existingCourses = courseOfferingService.findAll();
//            List<CourseOffering> returnList = new ArrayList<>();
//            HashMap<Integer, String> existingMap = new HashMap<>();
//            for(CourseOffering co: existingCourses){
//                existingMap.put(1, co.getCode());
//            }
//            for(CourseOffering req: requestedCourses){
//                if(!existingMap.containsValue(req.getCode()))
//                    throw new ObjectNotFoundException("Course "+ req.getCode()+" does not exist.");
//                else{
//                   returnList.add(req);
//                }
//            }
//            registrationRequest.setCourseList(returnList);
//            return registrationRequestRepository.save(registrationRequest);
//
//        else throw new EventNotOpenException("Current registration event is not open");
        return registrationRequestReturn;
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
