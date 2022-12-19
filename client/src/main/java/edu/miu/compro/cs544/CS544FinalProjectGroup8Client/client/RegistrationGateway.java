package edu.miu.compro.cs544.CS544FinalProjectGroup8Client.client;


import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model.CourseOffering;
import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model.RegistrationEvent;
import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model.RegistrationEvents;
import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model.RegistrationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Component
@Slf4j
public class RegistrationGateway {

    private String backendUrl = "http://localhost:8081";

    RestTemplate restTemplate = new RestTemplate();

    //TODO be sure this calls the student filtered method
    public RegistrationEvents getRegistrationEvents(){
        log.info("Sending GET to "+backendUrl+"/registration-events/latest");
        RegistrationEvents registrationEvents = restTemplate.getForObject(backendUrl+"/registration-events/latest", RegistrationEvents.class);
        return registrationEvents;
    }
    public RegistrationRequest registerStudent(@RequestBody RegistrationRequest registrationRequest){
        log.info("Sending POST to "+backendUrl+"/registration-events/latest with body" + registrationRequest);
        return restTemplate.postForObject(backendUrl+"/registration-events/latest", registrationRequest, RegistrationRequest.class);
    }
    //TODO be sure this calls a method to find registration by studentid
    public Registrations getRegistrationsByStudent(Integer studentId){
        log.info("Sending GET to "+backendUrl+"/registrations/"+studentId);
        Registrations registrations = restTemplate.getForObject(backendUrl+"/registrations/{studentId}", Registrations.class);
        return registrations;
    }

//    sysadmin access:
//    GET POST PUT DELETE /registration-events/{id} //GET and POST done
    public RegistrationEvent getRegistrationEventById(Long id){
        log.info("Sending GET to "+backendUrl+"/registration-events/"+id);
        return restTemplate.getForObject(backendUrl+"/registration-events/{id}", RegistrationEvent.class);
    }

//    GET POST PUT DELETE /student/{id}/registrations/{id}
//    GET POST PUT DELETE /student/{id}/registration-requests/{id}
//    GET POST PUT DELETE /registration-groups/{id}

//    PATCH /registration-events/{id}?processed=true
    //TODO patch method requires an object be passed, but what is supposed to be sent here?
    //TODO everything should be in the database and this method called only as a command?
    public RegistrationEvent processRegistrationEvent(Long id){
        log.info("Sending PATCH to "+backendUrl+"/registration-events/"+id+"?processed=true");
        return restTemplate.patchForObject(backendUrl+"/registration-events/{id}?processed=true", new Random(), RegistrationEvent.class);
    }

    public ResponseEntity<Void> createRegistrationEvent(@RequestBody RegistrationEvent registrationEvent){
        String uri = backendUrl+"/registration-events/create";
        log.info("Sending POST to "+uri+" with body " + registrationEvent);
        return restTemplate.postForObject(uri, registrationEvent, ResponseEntity.class);
    }

    public CourseOffering createCourseOffering(@RequestBody CourseOffering courseOffering){
        log.info("Sending POST to"+backendUrl+"/registration-events/course-offering");
        return  restTemplate.postForObject(backendUrl+"/registration-events/course-offering", courseOffering, CourseOffering.class);
    }
}
