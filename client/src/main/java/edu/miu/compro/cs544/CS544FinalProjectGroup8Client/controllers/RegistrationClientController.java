package edu.miu.compro.cs544.CS544FinalProjectGroup8Client.controllers;

import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.client.RegistrationGateway;
import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.client.Registrations;
import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model.RegistrationEvent;
import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model.RegistrationEvents;
import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RegistrationClientController {

    @Autowired
    private RegistrationGateway registrationGateway;

    //Student access:
    @GetMapping("/registration-events/latest")
    public ResponseEntity<?> getRegistrationEvents(){
       RegistrationEvents registrationEvents = registrationGateway.getRegistrationEvents();
       return new ResponseEntity<RegistrationEvents>(registrationEvents, HttpStatus.OK);
    }
    @PostMapping("/registration-events/latest")
    public ResponseEntity<?> registerStudent(@RequestBody List<RegistrationRequest> registrationRequest){
        Integer studentId = registrationRequest.get(0).getStudent().getStudentId();
        registrationGateway.registerStudent(registrationRequest);
        return new ResponseEntity<Boolean>(HttpStatus.CREATED);
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


}
