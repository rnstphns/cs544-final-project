package edu.miu.compro.cs544.CS544FinalProjectGroup8.controller;

import edu.miu.compro.cs544.CS544FinalProjectGroup8.domain.RegistrationEvent;
import edu.miu.compro.cs544.CS544FinalProjectGroup8.service.RegistrationEventService;
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
    public ResponseEntity<RegistrationEvent> getEventById(@PathVariable long eventId){
        return ResponseEntity.ok(registrationEventService.getRegistrationEventById(eventId));
    }

}
