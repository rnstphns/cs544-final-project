package edu.miu.cs544.backend.web;


import edu.miu.cs544.backend.domain.CourseOffering;
import edu.miu.cs544.backend.domain.RegistrationEvent;
import edu.miu.cs544.backend.domain.RegistrationGroup;
import edu.miu.cs544.backend.domain.RegistrationRequest;
import edu.miu.cs544.backend.exceptions.DatabaseException;
import edu.miu.cs544.backend.exceptions.EventNotOpenException;
import edu.miu.cs544.backend.exceptions.ObjectNotFoundException;
import edu.miu.cs544.backend.service.CourseOfferingServiceImpl;
import edu.miu.cs544.backend.service.RegistrationEventService;
import edu.miu.cs544.backend.service.RegistrationGroupService;
import edu.miu.cs544.backend.service.RegistrationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/registration-events")
public class RegistrationEventController {

    @Autowired
    private RegistrationEventService registrationEventService;

    @Autowired
    private RegistrationGroupService registrationGroupService;
    @Autowired
    private CourseOfferingServiceImpl courseOfferingService;

    @Autowired
    private RegistrationRequestService requestService;

    @GetMapping("/latest")
    public ResponseEntity<?> getLatest(){
        return new ResponseEntity<>(registrationEventService.latest(), HttpStatus.OK);
    }

    @PostMapping("/request/{studentId}")
    public ResponseEntity<?> saveRequest(@RequestBody List<RegistrationRequest> requests, @PathVariable Integer studentId) {
        try{
            boolean success = requestService.createRegistrationRequest(requests, studentId);
            if (success) {
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
            else return new ResponseEntity<>("Registration request failed to save.", HttpStatus.BAD_REQUEST);
        }catch(EventNotOpenException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        catch(ObjectNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        } catch (DatabaseException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }

    }

    @PostMapping("/create")
    public ResponseEntity<?> createEvent(@RequestBody RegistrationEvent registrationEvent){
        try {
            registrationEventService.createRegistrationEvent(registrationEvent);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DatabaseException e) {
            return new ResponseEntity<>("Error saving RegistrationEvent:"+e.getMessage(), HttpStatus.SEE_OTHER);
        }

    }


    @DeleteMapping("/delete/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long eventId){
        registrationEventService.deleteRegistrationEvent(eventId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{eventId}/{startDate}/{endDate}")
    public ResponseEntity<RegistrationEvent> updateEvent(@PathVariable long eventId, @PathVariable LocalDate startDate, @PathVariable LocalDate endDate){

        return ResponseEntity.ok(registrationEventService.updateWindow(eventId,startDate,endDate));
    }

    @GetMapping("/get")
    public ResponseEntity<Collection<RegistrationEvent>> getEvent(){
       return ResponseEntity.ok(registrationEventService.getRegistrationEvents());
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<RegistrationEvent> getEventById(@PathVariable Long eventId){
        return ResponseEntity.ok(registrationEventService.getRegistrationEventById(eventId));
    }

    @PostMapping("/course-offering")
    public ResponseEntity<?> createCourseOffering(@RequestBody CourseOffering courseOffering) {
        courseOfferingService.create(courseOffering);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/registration-group")
    public ResponseEntity<?> createRegistrationGroup(@RequestBody RegistrationGroup registrationGroup){
        try{
            return ResponseEntity.ok(registrationGroupService.create(registrationGroup));
        }catch(DatabaseException e){
            return new ResponseEntity<>("Error saving CourseOffering:"+e.getMessage(), HttpStatus.SEE_OTHER);
        }

    }

}
