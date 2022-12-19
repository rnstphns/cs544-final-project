package edu.miu.cs544.backend.web;


import edu.miu.cs544.backend.domain.RegistrationEvent;
import edu.miu.cs544.backend.domain.Student;
import edu.miu.cs544.backend.service.RegistrationEventService;
import edu.miu.cs544.backend.service.StudentService;
import edu.miu.cs544.backend.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
@Autowired
private StudentService studentServiceImpl;
    @Autowired
    private RegistrationEventService registrationEventService;

    @GetMapping("/registration-events/latest")
    public ResponseEntity<RegistrationEvent> getAllCourseOfferings(){

        return ResponseEntity.ok(registrationEventService.latest());
    }

@PostMapping("/addstudent")
    public ResponseEntity<Void> addstudent(@RequestBody Student student){
      studentServiceImpl.create(student);
      return  new ResponseEntity<>(HttpStatus.CREATED);
}
@DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<Void> deletestudent(Long id){
        studentServiceImpl.delete(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

@PutMapping("/update/{id}")
    public  ResponseEntity<?> updateStudent(@RequestBody Student student , Long id){
        return  ResponseEntity.ok(studentServiceImpl.update(id, student));
}
@GetMapping("/studentlist")
    public  ResponseEntity<List<Student>> getallstudents(){
        var allstudents= studentServiceImpl.findAll();
        return ResponseEntity.ok(allstudents);
}
}
