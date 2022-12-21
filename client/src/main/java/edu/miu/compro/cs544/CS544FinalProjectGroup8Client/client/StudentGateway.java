package edu.miu.compro.cs544.CS544FinalProjectGroup8Client.client;

import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Component
@Slf4j
public class StudentGateway {

    private String backendUrl = "http://localhost:8081";

    RestTemplate restTemplate = new RestTemplate();


    public ResponseEntity<?> addstudent(@RequestBody Student student){
        String url = backendUrl+"/students/addstudent";
        return  restTemplate.postForObject(url, student, ResponseEntity.class);
    }


    public ResponseEntity<Void> deletestudent(Long id){
        String url = backendUrl+"/students/deletebyid/"+id;
        restTemplate.delete(url);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    public  ResponseEntity<?> updateStudent(@RequestBody Student student , Long id){
        String url = backendUrl+"/students/update/"+id;
        restTemplate.put(url, student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public Students getallstudents(){
        String url = backendUrl+"/students/studentlist";
        return restTemplate.getForObject(url, Students.class);
    }
}
