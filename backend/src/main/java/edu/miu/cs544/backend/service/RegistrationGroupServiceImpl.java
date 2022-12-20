package edu.miu.cs544.backend.service;

import edu.miu.cs544.backend.repositories.RegistrationGroupRepository;
import edu.miu.cs544.backend.domain.CourseOffering;
import edu.miu.cs544.backend.domain.RegistrationGroup;
import edu.miu.cs544.backend.domain.Student;
import edu.miu.cs544.backend.exceptions.DatabaseException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RegistrationGroupServiceImpl implements RegistrationGroupService{

    @Autowired
    private RegistrationGroupRepository repository;

    @Autowired
    private CourseOfferingService courseOfferingService;
    @Autowired
    private StudentService studentService;

    @Override
    public Collection<RegistrationGroup> findByStudent(Integer studentId) {
        //TODO implement
        return null;
    }

    @Override
    public List<RegistrationGroup> findAll() {
        return repository.findAll();
    }

    @Override
    public RegistrationGroup findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public RegistrationGroup create(RegistrationGroup registrationGroup) throws DatabaseException {
        Collection<Student> students = registrationGroup.getStudents();
        for(Student s: students){
            studentService.create(s);
        }
        Collection<CourseOffering> courses = registrationGroup.getCourses();
        for(CourseOffering c: courses){
            courseOfferingService.create(c);
        }
        return repository.save(registrationGroup);
    }

    @Override
    public boolean update(Long id, RegistrationGroup registrationGroup) {
        Optional<RegistrationGroup> registrationOptional = repository.findById(id);
        if(registrationOptional.isPresent()) {
            RegistrationGroup savedRegistrationGroup = registrationOptional.get();
            savedRegistrationGroup.setStudents(registrationGroup.getStudents());
            savedRegistrationGroup.setCourses(registrationGroup.getCourses());
            repository.save(savedRegistrationGroup);
            return true;
        }
        else return false;
    }
}
