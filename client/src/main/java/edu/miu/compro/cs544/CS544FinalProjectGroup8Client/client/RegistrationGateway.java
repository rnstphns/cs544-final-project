package edu.miu.compro.cs544.CS544FinalProjectGroup8Client.client;


import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Random;

@Component
@Slf4j
public class RegistrationGateway {

    private String backendUrl = "http://localhost:8081";

    RestTemplate restTemplate = new RestTemplate();

    public RegistrationEvents getLatestRegistrationEvent(Integer studentId){
        String url = backendUrl+"/registration-events/latest/"+studentId;
        log.info("Sending GET to "+url);
        RegistrationEvents registrationEvents = restTemplate.getForObject(url, RegistrationEvents.class);
        return registrationEvents;
    }
    public ResponseEntity<?> sendRegistrationRequest(@RequestBody List<RegistrationRequest> registrationRequest){
        String studentId = registrationRequest.get(0).getStudent().getStudentId().toString();
        String url = backendUrl+"/registration-events/request/"+studentId;
        log.info("Sending POST to "+ url);
        return restTemplate.postForObject(url, registrationRequest, ResponseEntity.class);
    }

    public Registrations getRegistrationsByStudent(Integer studentId){
        log.info("Sending GET to "+backendUrl+"/registrations/"+studentId);
        Registrations registrations = restTemplate.getForObject(backendUrl+"/registrations/{studentId}", Registrations.class);
        return registrations;
    }

//    sysadmin access:
    public RegistrationEvent getRegistrationEventById(Long id){
        String url = backendUrl+"/registration-events/"+id;
        log.info("Sending GET to "+url);
        return restTemplate.getForObject(url, RegistrationEvent.class);
    }
      public ResponseEntity<?> processRegistrationEvent(Long id){
        String url = backendUrl+"/registration-events/"+id+"?processed=true";
        log.info("Sending PATCH to "+backendUrl+"/registration-events/"+id+"?processed=true");
        return restTemplate.postForEntity(url, new Random(), ResponseEntity.class);
    }

    public ResponseEntity<?> createRegistrationEvent(@RequestBody RegistrationEvent registrationEvent){
        String uri = backendUrl+"/registration-events/create";
        log.info("Sending POST to "+uri+" with body " + registrationEvent);
        return restTemplate.postForObject(uri, registrationEvent, ResponseEntity.class);
    }

    public ResponseEntity<?>  createCourseOffering(@RequestBody CourseOffering courseOffering){
        log.info("Sending POST to"+backendUrl+"/registration-events/course-offering");
        return  restTemplate.postForEntity(backendUrl+"/registration-events/course-offering", courseOffering, CourseOffering.class);
    }

    public ResponseEntity<?>  createRegistrationGroup(@RequestBody RegistrationGroup registrationGroup){
        String uri = backendUrl+"/registration-events/registration-group";
        log.info("Sending POST to "+uri);
        return restTemplate.postForEntity(uri, registrationGroup, RegistrationGroup.class);
    }

    public Collection<RegistrationEvent> getRegistrationEvents() {
        String uri = backendUrl+"/registration-events/get";
        log.info("Sending GET to "+uri);
        return restTemplate.getForObject(uri, Collection.class);
    }
}
