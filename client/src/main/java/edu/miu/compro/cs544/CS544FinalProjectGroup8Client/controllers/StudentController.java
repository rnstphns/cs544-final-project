package edu.miu.compro.cs544.CS544FinalProjectGroup8Client.controllers;


import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.client.StudentGateway;
import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.client.Students;
import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentGateway gateway;

    @PostMapping("/addstudent")
    public ResponseEntity<?> addstudent(@RequestBody Student student){
        return gateway.addstudent(student);
    }

    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<Void> deletestudent(@PathVariable Long id){
        return gateway.deletestudent(id);
}

    @PutMapping("/update/{id}")
    public  ResponseEntity<?> updateStudent(@RequestBody Student student , @PathVariable Long id){
        return  gateway.updateStudent(student, id);
}
    @GetMapping("/studentlist")
    public  ResponseEntity<Students> getallstudents(){
        Students students = gateway.getallstudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
}
}
