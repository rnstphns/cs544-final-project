package edu.miu.cs544.compro.backend.service;

import edu.miu.cs544.compro.backend.domain.Student;
import edu.miu.cs544.compro.backend.exceptions.DatabaseException;
import edu.miu.cs544.compro.backend.exceptions.ObjectNotFoundException;
import edu.miu.cs544.compro.backend.repositories.StudentRepository;
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
            Optional<Student> s = studentRepository.findById(id);
            if(s.isPresent())
                return s.get();
            else throw new ObjectNotFoundException("Student is not in database");

    }

    @Override
    public Student findByStudentId(Integer studentId) throws DatabaseException {
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
