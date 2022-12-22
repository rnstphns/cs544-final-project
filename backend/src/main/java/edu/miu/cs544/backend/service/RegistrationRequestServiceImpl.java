package edu.miu.cs544.backend.service;

import edu.miu.cs544.backend.domain.*;
import edu.miu.cs544.backend.exceptions.DatabaseException;
import edu.miu.cs544.backend.repositories.RegistrationRequestRepository;
import edu.miu.cs544.backend.repositories.StudentRepository;
import edu.miu.cs544.backend.exceptions.EventNotOpenException;
import edu.miu.cs544.backend.exceptions.ObjectNotFoundException;
import edu.miu.cs544.backend.util.RegistrationEventUtilities;
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
            RegistrationEvent latestRegistrationEvent = registrationEventService.latest();

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
