package edu.miu.compro.cs544.CS544FinalProjectGroup8.controller;

import edu.miu.compro.cs544.CS544FinalProjectGroup8.domain.RegistrationEvent;
import edu.miu.compro.cs544.CS544FinalProjectGroup8.service.RegistrationEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired
    private RegistrationEventService registrationEventService;

    @GetMapping("/registration-events/latest")
    public ResponseEntity<RegistrationEvent> getAllCourseOfferings(){
        return ResponseEntity.ok(registrationEventService.latest());
    }

}
