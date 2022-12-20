package edu.miu.cs544.backend.controller;


import edu.miu.cs544.backend.domain.CourseOffering;
import edu.miu.cs544.backend.domain.RegistrationEvent;
import edu.miu.cs544.backend.service.CourseOfferingServiceImpl;
import edu.miu.cs544.backend.service.RegistrationEventService;
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
    private CourseOfferingServiceImpl courseOfferingService;

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

    @PostMapping("/course-offering")
    public ResponseEntity<CourseOffering> createCourseOffering(@RequestBody CourseOffering courseOffering){
        return ResponseEntity.ok(courseOfferingService.createCourseOffering(courseOffering));
    }

}
