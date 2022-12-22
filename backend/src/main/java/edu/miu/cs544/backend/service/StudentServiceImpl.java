package edu.miu.cs544.backend.service;

import edu.miu.cs544.backend.exceptions.ObjectNotFoundException;
import edu.miu.cs544.backend.repositories.StudentRepository;
import edu.miu.cs544.backend.domain.Student;
import edu.miu.cs544.backend.exceptions.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Long id) throws ObjectNotFoundException {
        try{
            return studentRepository.findById(id).get();
        }catch(Exception e){
            throw new ObjectNotFoundException("Student is not in database");
        }
    }

    @Override
    public Student findByStudentId(Integer studentId) throws DatabaseException{
        Optional<Student> s = studentRepository.findByStudentId(studentId);
        if(s.isPresent()){
            return s.get();
        }else{
            throw new DatabaseException("Student"+studentId+"is not in the database");
        }
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student create(Student student) throws DatabaseException {
        try{
            return studentRepository.save(student);
        }catch(Exception e){
            throw new DatabaseException(e.getMessage());
        }

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
