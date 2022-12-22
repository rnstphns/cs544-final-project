package edu.miu.cs544.backend.service;

import edu.miu.cs544.backend.exceptions.DatabaseException;
import edu.miu.cs544.backend.exceptions.ObjectNotFoundException;
import edu.miu.cs544.backend.repositories.RegistrationRepository;
import edu.miu.cs544.backend.domain.CourseOffering;
import edu.miu.cs544.backend.domain.Registration;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class RegistrationServiceImpl implements RegistrationService{

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private CourseOfferingService courseOfferingService;
    @Override
    public Registration getRegistrationById(Long id) throws ObjectNotFoundException {
        try {
            return registrationRepository.findById(id).get();
        }catch(Exception e){
            throw new ObjectNotFoundException("Registration not found"+e);
        }
    }

    //TODO - filter registrations by student id
    public Collection<Registration> getRegistrationsByStudentId(Integer studentId){
        return null;
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
