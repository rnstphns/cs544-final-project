package edu.miu.cs544.compro.backend.service;

import edu.miu.cs544.compro.backend.domain.CourseOffering;
import edu.miu.cs544.compro.backend.domain.Registration;
import edu.miu.cs544.compro.backend.domain.Student;
import edu.miu.cs544.compro.backend.exceptions.DatabaseException;
import edu.miu.cs544.compro.backend.exceptions.ObjectNotFoundException;
import edu.miu.cs544.compro.backend.repositories.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService{

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private CourseOfferingService courseOfferingService;

    @Autowired
    private StudentService studentService;
    @Override
    public Registration getRegistrationById(Long id) throws ObjectNotFoundException {
        Optional<Registration> found = registrationRepository.findById(id);
        if(found.isPresent())
            return found.get();
        else throw new ObjectNotFoundException("Registration not found");

    }
    public Collection<Registration> getRegistrationsByStudentId(Integer studentId){
        try {
            Student s = studentService.findByStudentId(studentId);
            return registrationRepository.findTop12ByStudent(s);
        }catch(DatabaseException ex){
            return new ArrayList<Registration>();
        }
    }

    @Override
    public Collection<Registration> getRegistrations() {
        return registrationRepository.findAll();
    }

    @Override
    public Registration createRegistration(Registration registration) throws DatabaseException {
        List<CourseOffering> courseOfferings = registration.getCourseList();
        for (CourseOffering c: courseOfferings) {
            courseOfferingService.create(c);
        }
        return registrationRepository.save(registration);
    }

    @Override
    public void deleteRegistration(Long id) {
        registrationRepository.deleteById(id);
    }

    @Override
    public boolean updateRegistration(Long id, Registration registration) {
        Optional<Registration> registrationOptional = registrationRepository.findById(id);
        if(registrationOptional.isPresent()) {
            Registration savedRegistration = registrationOptional.get();
            savedRegistration.setCourseList(registration.getCourseList());
            savedRegistration.setStudent(registration.getStudent());
            registrationRepository.save(savedRegistration);
            return true;
        }
        else return false;
    }
}
