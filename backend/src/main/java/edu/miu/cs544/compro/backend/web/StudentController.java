package edu.miu.cs544.compro.backend.web;


import edu.miu.cs544.compro.backend.domain.Registration;
import edu.miu.cs544.compro.backend.domain.Student;
import edu.miu.cs544.compro.backend.exceptions.DatabaseException;
import edu.miu.cs544.compro.backend.service.RegistrationEventService;
import edu.miu.cs544.compro.backend.service.RegistrationService;
import edu.miu.cs544.compro.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
@Autowired
private StudentService studentServiceImpl;
    @Autowired
    private RegistrationEventService registrationEventService;

    @Autowired
    private RegistrationService registrationService;



@PostMapping("/addstudent")
    public ResponseEntity<?> addstudent(@RequestBody Student student){
    try {
        studentServiceImpl.create(student);
        return  new ResponseEntity<>(HttpStatus.CREATED);
    } catch (DatabaseException e) {
        return new ResponseEntity<>("Student is already in database", HttpStatus.SEE_OTHER);
    }

}
    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<Void> deletestudent(@PathVariable Long id){
        studentServiceImpl.delete(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

    @PutMapping("/update/{id}")
    public  ResponseEntity<?> updateStudent(@RequestBody Student student , @PathVariable Long id){
        return  ResponseEntity.ok(studentServiceImpl.update(id, student));
}
    @GetMapping("/studentlist")
    public  ResponseEntity<List<Student>> getallstudents(){
        var allstudents= studentServiceImpl.findAll();
        return ResponseEntity.ok(allstudents);
}

    @GetMapping("{studentId}/registrations")
    public ResponseEntity<Collection<Registration>> getRegistrationsByStudentId(@PathVariable Integer studentId){
        Collection<Registration> registrations = registrationService.getRegistrationsByStudentId(studentId);
        return new ResponseEntity<Collection<Registration>>(registrations, HttpStatus.OK);
    }
}
