package edu.miu.compro.cs544.CS544FinalProjectGroup8Client.controllers;

import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.client.RegistrationGateway;
import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.client.Registrations;
import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
public class RegistrationClientController {

    @Autowired
    private RegistrationGateway registrationGateway;

    //Student access:
    //TODO Returns empty array
    @GetMapping("/registration-events/latest")
    public ResponseEntity<?> getLatestRegistrationEvent(){
       RegistrationEvents registrationEvents = registrationGateway.getLatestRegistrationEvent();
       return new ResponseEntity<RegistrationEvents>(registrationEvents, HttpStatus.OK);
    }
    @PostMapping("/registration-events/request/{studentId}")
    public ResponseEntity<?> registerStudent(@PathVariable Integer studentId, @RequestBody List<RegistrationRequest> registrationRequest){
        return registrationGateway.sendRegistrationRequest(registrationRequest);
    }

    @GetMapping("/registration-events/request/{studentId}")
    public ResponseEntity<?> getRegistrationsByStudent(@PathVariable("studentId") Integer studentId){
        Registrations registrations = registrationGateway.getRegistrationsByStudent(studentId);
        return new ResponseEntity<Registrations>(registrations, HttpStatus.OK);
    }

    //    sysadmin access:
//    GET POST PUT DELETE /registration-events/{id}
    @GetMapping("/registration-events/{id}")
    public ResponseEntity<?> getRegistrationEventById(@PathVariable("id") Long id){
        RegistrationEvent registrationEvent = registrationGateway.getRegistrationEventById(id);
        return new ResponseEntity<RegistrationEvent>(registrationEvent, HttpStatus.OK);
    }

    @GetMapping("/registration-events/get")
    public ResponseEntity<Collection<RegistrationEvent>> getRegistrationEvents(){
        return ResponseEntity.ok(registrationGateway.getRegistrationEvents());
    }
    @PostMapping("/registration-events/create")
    public ResponseEntity<?> createRegistrationEvent(@RequestBody RegistrationEvent registrationEvent){
        registrationGateway.createRegistrationEvent(registrationEvent);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    //    PATCH /registration-events/{id}?processed=true
    @PatchMapping("/registration-events/{id}")
    public ResponseEntity<?> processRegistrationEvent(@PathVariable("id") Long id){
        RegistrationEvent registrationEvent = registrationGateway.processRegistrationEvent(id);
        return new ResponseEntity<>(HttpStatus.PROCESSING);
    }

    @PostMapping("registration-events/course-offering")
    public ResponseEntity<?> createCourseOffering(@RequestBody CourseOffering courseOffering) {
        return registrationGateway.createCourseOffering(courseOffering);
    }

    @PostMapping("registration-events/registration-group")
    public ResponseEntity<?> createRegistrationGroup(@RequestBody RegistrationGroup registrationGroup){
        return registrationGateway.createRegistrationGroup(registrationGroup);
    }

}
