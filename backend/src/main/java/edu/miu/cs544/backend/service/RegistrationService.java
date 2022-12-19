package edu.miu.cs544.backend.service;

import edu.miu.cs544.backend.domain.CourseOffering;
import edu.miu.cs544.backend.domain.Registration;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RegistrationService {
    public Registration getRegistrationById(Long id);
    public Collection<Registration> getRegistrationsByStudentId(Integer studentId);
    public Collection<Registration> getRegistrations() ;

    public Registration createRegistration(Registration registration);
    public void deleteRegistration(Long id) ;
    public boolean updateRegistration(Long id, Registration registration);

}
