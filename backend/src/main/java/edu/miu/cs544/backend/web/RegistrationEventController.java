package edu.miu.cs544.backend.web;


import edu.miu.cs544.backend.domain.CourseOffering;
import edu.miu.cs544.backend.domain.RegistrationEvent;
import edu.miu.cs544.backend.domain.RegistrationGroup;
import edu.miu.cs544.backend.service.CourseOfferingServiceImpl;
import edu.miu.cs544.backend.service.RegistrationEventService;
import edu.miu.cs544.backend.service.RegistrationGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;

@RestController
@RequestMapping("/registration-events")
public class RegistrationEventController {

    @Autowired
    private RegistrationEventService registrationEventService;

    @Autowired
    private RegistrationGroupService registrationGroupService;
    @Autowired
    private CourseOfferingServiceImpl courseOfferingService;

    @GetMapping("/latest")
    public ResponseEntity<?> getLatest(){
        return new ResponseEntity<>(registrationEventService.latest(), HttpStatus.OK);
    }

//    @PostMapping
//    public

    @PostMapping("/create")
    public ResponseEntity<Void> createEvent(@RequestBody RegistrationEvent registrationEvent){
        registrationEventService.createRegistrationEvent(registrationEvent);
        return new ResponseEntity<>(HttpStatus.CREATED);
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

    @GetMapping("/getbyid/{eventId}")
    public ResponseEntity<RegistrationEvent> getEventById(@PathVariable Long eventId){
        return ResponseEntity.ok(registrationEventService.getRegistrationEventById(eventId));
    }

    //TODO TEST CODE :: DO WE NEED THIS IN FINAL IMPL
    @PostMapping("/course-offering")
    public ResponseEntity<CourseOffering> createCourseOffering(@RequestBody CourseOffering courseOffering){
        return ResponseEntity.ok(courseOfferingService.create(courseOffering));
    }

    @PostMapping("/registration-group")
    public ResponseEntity<RegistrationGroup> createRegistrationGroup(@RequestBody RegistrationGroup registrationGroup){
        return ResponseEntity.ok(registrationGroupService.create(registrationGroup));
    }

}
