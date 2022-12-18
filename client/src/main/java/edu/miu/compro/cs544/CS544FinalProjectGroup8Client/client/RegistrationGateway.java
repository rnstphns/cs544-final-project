package edu.miu.compro.cs544.CS544FinalProjectGroup8Client.client;


import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model.RegistrationEvent;
import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model.RegistrationEvents;
import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model.RegistrationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Component
public class RegistrationGateway {

    private String backendUrl = "http://localhost:8081";

    RestTemplate restTemplate = new RestTemplate();

//    GET /registration-events/latest
    //TODO be sure this calls the student filtered method
    public RegistrationEvents getRegistrationEvents(){
        RegistrationEvents registrationEvents = restTemplate.getForObject(backendUrl+"/registration-events/latest", RegistrationEvents.class);
        return registrationEvents;
    }
//    POST /registration-events/latest -- List<RegistrationRequest> in body
    public RegistrationRequest registerStudent(@RequestBody RegistrationRequest registrationRequest){
        return restTemplate.postForObject(backendUrl+"/registration-events/latest", registrationRequest, RegistrationRequest.class);
    }
//    GET /registrations/{studentId}
    //TODO be sure this calls a method to find registration by studentid
    public Registrations getRegistrationsByStudent(Integer studentId){
        Registrations registrations = restTemplate.getForObject(backendUrl+"/registrations/{studentId}", Registrations.class);
        return registrations;
    }

//    sysadmin access:
//    GET POST PUT DELETE /registration-events/{id}
    public RegistrationEvent getRegistrationEventById(Long id){
        return restTemplate.getForObject(backendUrl+"/registration-events/{id}", RegistrationEvent.class);
    }

//    GET POST PUT DELETE /student/{id}/registrations/{id}
//    GET POST PUT DELETE /student/{id}/registration-requests/{id}
//    GET POST PUT DELETE /registration-groups/{id}

//    PATCH /registration-events/{id}?processed=true
    //TODO patch method requires an object be passed, but what is supposed to be sent here?
    //TODO everything should be in the database and this method called only as a command?
    public RegistrationEvent processRegistrationEvent(Long id){
        return restTemplate.patchForObject(backendUrl+"/registration-events/{id}?processed=true", new Random(), RegistrationEvent.class);
    }

    public ResponseEntity<Void> createRegistrationEvent(@RequestBody RegistrationEvent registrationEvent){
        String uri = backendUrl+"/registration-events/create";
        System.out.println(uri);
        return restTemplate.postForObject(uri, registrationEvent, ResponseEntity.class);
    }
}
