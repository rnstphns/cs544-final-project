package edu.miu.cs544.compro.backend.service;

import edu.miu.cs544.compro.backend.domain.RegistrationRequest;
import edu.miu.cs544.compro.backend.exceptions.DatabaseException;
import edu.miu.cs544.compro.backend.exceptions.EventNotOpenException;
import edu.miu.cs544.compro.backend.exceptions.ObjectNotFoundException;

import java.util.Collection;
import java.util.List;

public interface RegistrationRequestService {

    public RegistrationRequest getRegistrationRequestById(Long id) throws ObjectNotFoundException;
    public Collection<RegistrationRequest> getRegistrationRequests();

    boolean createRegistrationRequest(List<RegistrationRequest> requests, Integer studentID) throws EventNotOpenException, ObjectNotFoundException, DatabaseException;

    public void deleteRegistrationRequest(Long id);
}
