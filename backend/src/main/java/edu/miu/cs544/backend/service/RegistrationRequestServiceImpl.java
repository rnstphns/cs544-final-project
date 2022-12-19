package edu.miu.cs544.backend.service;

import edu.miu.cs544.backend.Repositories.RegistrationRequestRepository;
import edu.miu.cs544.backend.Repositories.StudentRepository;
import edu.miu.cs544.backend.domain.Registration;
import edu.miu.cs544.backend.domain.RegistrationRequest;
import edu.miu.cs544.backend.domain.Student;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Transactional
public class RegistrationRequestServiceImpl implements RegistrationRequestService{

    @Autowired
    private RegistrationRequestRepository registrationRequestRepository;

    @Autowired
    private StudentRepository studentRepository;
    @Override
    public RegistrationRequest getRegistrationRequestById(Long id) {
        return registrationRequestRepository.findById(id).get();
    }

    @Override
    public Collection<RegistrationRequest> getRegistrationRequests() {
        return registrationRequestRepository.findAll();
    }

    @Override
    public RegistrationRequest createRegistrationRequest(RegistrationRequest registrationRequest) {
        Student s = registrationRequest.getStudent();
        studentRepository.save(s);
        return registrationRequestRepository.save(registrationRequest);
    }

    @Override
    public void deleteRegistrationRequest(Long id) {
        registrationRequestRepository.deleteById(id);
    }

}
