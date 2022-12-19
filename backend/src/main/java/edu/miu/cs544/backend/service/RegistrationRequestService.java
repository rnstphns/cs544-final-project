package edu.miu.cs544.backend.service;

import edu.miu.cs544.backend.Repositories.RegistrationRequestRepository;
import edu.miu.cs544.backend.domain.Registration;
import edu.miu.cs544.backend.domain.RegistrationRequest;
import edu.miu.cs544.backend.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public interface RegistrationRequestService {

    public RegistrationRequest getRegistrationRequestById(Long id);
    public Collection<RegistrationRequest> getRegistrationRequests();
    public RegistrationRequest createRegistrationRequest(RegistrationRequest registrationRequest);
    public void deleteRegistrationRequest(Long id);
}
