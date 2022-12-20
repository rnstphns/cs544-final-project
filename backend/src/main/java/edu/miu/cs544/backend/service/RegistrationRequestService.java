package edu.miu.cs544.backend.service;

import edu.miu.cs544.backend.Repositories.RegistrationRequestRepository;
import edu.miu.cs544.backend.domain.Registration;
import edu.miu.cs544.backend.domain.RegistrationRequest;
import edu.miu.cs544.backend.domain.Student;
import edu.miu.cs544.backend.exceptions.EventNotOpenException;
import edu.miu.cs544.backend.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public interface RegistrationRequestService {

    public RegistrationRequest getRegistrationRequestById(Long id);
    public Collection<RegistrationRequest> getRegistrationRequests();
    public RegistrationRequest createRegistrationRequest(RegistrationRequest registrationRequest) throws EventNotOpenException, ObjectNotFoundException;
    public void deleteRegistrationRequest(Long id);
}
