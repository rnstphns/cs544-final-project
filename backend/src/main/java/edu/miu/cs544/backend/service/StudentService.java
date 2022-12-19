package edu.miu.cs544.backend.service;

import edu.miu.cs544.backend.Repositories.StudentRepository;
import edu.miu.cs544.backend.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public  void addStudent(Student student){
        studentRepository.save(student);
    }

    public  void deletestudent(Long id){

        studentRepository.deleteById(id);

    }

    public  Student updateStudent(Long id, Student student){
        var tobeupdated=studentRepository.findById(id);
        Student student1=null;
        if (tobeupdated.isPresent()){
            student1=tobeupdated.stream()
                    .map(newstudent->{
                        newstudent.setId(student.getId());
                        newstudent.setName(student.getName());
                        newstudent.setAddress(student.getAddress());
                        newstudent.setEmail(student.getEmail());
                        return  newstudent;
                    }).findFirst().get();
            studentRepository.save(student1);

        }
   return student1; }

    public List<Student> getstudents(){
        return studentRepository.findAll();
    }

}
