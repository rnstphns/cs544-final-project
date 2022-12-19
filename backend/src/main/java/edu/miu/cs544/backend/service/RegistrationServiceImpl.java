package edu.miu.cs544.backend.service;

import edu.miu.cs544.backend.Repositories.RegistrationRepository;
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
    public Registration getRegistrationById(Long id) {
        return registrationRepository.findById(id).get();
    }
    public Collection<Registration> getRegistrationsByStudentId(Integer studentId){
        return registrationRepository.findByStudentId(studentId);
    }

    @Override
    public Collection<Registration> getRegistrations() {
        return registrationRepository.findAll();
    }

    @Override
    public Registration createRegistration(Registration registration) {
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
