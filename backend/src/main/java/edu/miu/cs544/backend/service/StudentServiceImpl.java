package edu.miu.cs544.backend.service;

import edu.miu.cs544.backend.Repositories.StudentRepository;
import edu.miu.cs544.backend.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public boolean update(Long id, Student student) {
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
            return true;
        }
        else return false;
    }
}