package edu.miu.cs544.backend.controller;


import edu.miu.cs544.backend.domain.RegistrationEvent;
import edu.miu.cs544.backend.domain.Student;
import edu.miu.cs544.backend.service.RegistrationEventService;
import edu.miu.cs544.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
@Autowired
private StudentService studentService;
    @Autowired
    private RegistrationEventService registrationEventService;

    @GetMapping("/registration-events/latest")
    public ResponseEntity<RegistrationEvent> getAllCourseOfferings(){

        return ResponseEntity.ok(registrationEventService.latest());
    }

@PostMapping("/addstudent")
    public ResponseEntity<Void> addstudent(@RequestBody Student student){
      studentService.addStudent(student);
      return  new ResponseEntity<>(HttpStatus.CREATED);
}
@DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<Void> deletestudent(Long id){
        studentService.deletestudent(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

@PutMapping("/update/{id}")
    public  ResponseEntity<Student> updateStudent(@RequestBody Student student ,Long id){

        return   ResponseEntity.ok(studentService.updateStudent(id,student));
}
@GetMapping("/studentlist")
    public  ResponseEntity<List<Student>> getallstudents(){
        var allstudents=studentService.getstudents();
        return ResponseEntity.ok(allstudents);
}
}
