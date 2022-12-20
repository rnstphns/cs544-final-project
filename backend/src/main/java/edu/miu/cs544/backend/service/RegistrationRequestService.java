package edu.miu.cs544.backend.service;

import edu.miu.cs544.backend.domain.RegistrationRequest;
import edu.miu.cs544.backend.exceptions.DatabaseException;
import edu.miu.cs544.backend.exceptions.EventNotOpenException;
import edu.miu.cs544.backend.exceptions.ObjectNotFoundException;

import java.util.Collection;

public interface RegistrationRequestService {

    public RegistrationRequest getRegistrationRequestById(Long id);
    public Collection<RegistrationRequest> getRegistrationRequests();
    public RegistrationRequest createRegistrationRequest(RegistrationRequest registrationRequest) throws EventNotOpenException, ObjectNotFoundException, DatabaseException;
    public void deleteRegistrationRequest(Long id);
}
