package edu.miu.cs544.backend.service;

import edu.miu.cs544.backend.exceptions.DatabaseException;
import edu.miu.cs544.backend.repositories.RegistrationRequestRepository;
import edu.miu.cs544.backend.repositories.StudentRepository;
import edu.miu.cs544.backend.domain.CourseOffering;
import edu.miu.cs544.backend.domain.RegistrationRequest;
import edu.miu.cs544.backend.domain.Student;
import edu.miu.cs544.backend.exceptions.EventNotOpenException;
import edu.miu.cs544.backend.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

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
    public RegistrationRequest createRegistrationRequest(RegistrationRequest registrationRequest) throws EventNotOpenException, ObjectNotFoundException, DatabaseException {
        if(isOpen()) {
            Student s = registrationRequest.getStudent();
            studentRepository.save(s);
            List<CourseOffering> requestedCourses = registrationRequest.getCourseList();
            List<CourseOffering> existingCourses = courseOfferingService.findAll();
            HashMap<Integer, String> existingMap = new HashMap<>();
            for(CourseOffering co: existingCourses){
                existingMap.put(1, co.getCode());
            }
            for(CourseOffering req: requestedCourses){
                if(!existingMap.containsValue(req.getCode()))
                    throw new ObjectNotFoundException("Course "+ req.getCode()+" does not exist.");
                else{
                    courseOfferingService.create(req);
                }
            }
            return registrationRequestRepository.save(registrationRequest);
        }
        else throw new EventNotOpenException("Current registration event is not open");
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
