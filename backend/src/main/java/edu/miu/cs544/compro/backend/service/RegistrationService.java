package edu.miu.cs544.compro.backend.service;

import edu.miu.cs544.compro.backend.domain.Registration;
import edu.miu.cs544.compro.backend.exceptions.DatabaseException;
import edu.miu.cs544.compro.backend.exceptions.ObjectNotFoundException;

import java.util.Collection;

public interface RegistrationService {

    public Registration getRegistrationById(Long id) throws ObjectNotFoundException;
    public Collection<Registration> getRegistrationsByStudentId(Integer studentId);
    public Collection<Registration> getRegistrations() ;
    public Registration createRegistration(Registration registration) throws DatabaseException;
    public void deleteRegistration(Long id) ;
    public boolean updateRegistration(Long id, Registration registration);

}
