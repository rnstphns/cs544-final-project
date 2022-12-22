package edu.miu.cs544.compro.backend.service;

import edu.miu.cs544.compro.backend.domain.CourseOffering;
import edu.miu.cs544.compro.backend.domain.RegistrationGroup;
import edu.miu.cs544.compro.backend.domain.Student;
import edu.miu.cs544.compro.backend.exceptions.DatabaseException;
import edu.miu.cs544.compro.backend.exceptions.ObjectNotFoundException;
import edu.miu.cs544.compro.backend.repositories.RegistrationGroupRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class RegistrationGroupServiceImpl implements RegistrationGroupService{

    @Autowired
    private RegistrationGroupRepository repository;

    @Autowired
    private CourseOfferingService courseOfferingService;
    @Autowired
    private StudentService studentService;


    @Override
    public List<RegistrationGroup> findAll() {
        return repository.findAll();
    }

    @Override
    public RegistrationGroup findById(Long id) throws ObjectNotFoundException {
        Optional<RegistrationGroup> found = repository.findById(id);
        if(found.isPresent())
            return found.get();
        else throw new ObjectNotFoundException("RegistrationGroup is not in the database");
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public RegistrationGroup create(RegistrationGroup registrationGroup) throws DatabaseException {
        Collection<Student> students = registrationGroup.getStudents();
        Collection<Student> foundStudents = new ArrayList<>();
        for(Student s: students){
            Student found = studentService.findByStudentId(s.getStudentId());
            foundStudents.add(found);
        }
        Collection<CourseOffering> courses = registrationGroup.getCourses();
        Collection<CourseOffering> foundCourses = new ArrayList<>();
        for(CourseOffering c: courses){
            CourseOffering found = courseOfferingService.findByCode(c.getCode());
            foundCourses.add(found);
        }
        registrationGroup.setStudents(foundStudents);
        registrationGroup.setCourses(foundCourses);
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
