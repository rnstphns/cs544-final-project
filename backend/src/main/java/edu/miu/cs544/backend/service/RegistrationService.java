package edu.miu.cs544.backend.service;

import edu.miu.cs544.backend.domain.Registration;
import edu.miu.cs544.backend.exceptions.DatabaseException;

import java.util.Collection;

public interface RegistrationService {
    public Registration getRegistrationById(Long id);
    public Collection<Registration> getRegistrations() ;
    public Registration createRegistration(Registration registration) throws DatabaseException;
    public void deleteRegistration(Long id) ;
    public boolean updateRegistration(Long id, Registration registration);

}
