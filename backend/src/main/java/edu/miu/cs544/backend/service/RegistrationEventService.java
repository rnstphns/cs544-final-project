package edu.miu.cs544.backend.service;



import edu.miu.cs544.backend.domain.Registration;
import edu.miu.cs544.backend.domain.RegistrationEvent;
import edu.miu.cs544.backend.domain.RegistrationGroup;
import edu.miu.cs544.backend.domain.RegistrationRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
@Service
public interface RegistrationEventService {

    public Collection<RegistrationGroup> findRegistrationGroupsWithStudent(String studentId);
    public RegistrationEvent getRegistrationEventById(long id);
    public Collection<RegistrationEvent> getRegistrationEvents();
    public RegistrationEvent createRegistrationEvent(RegistrationEvent registrationEvent);
    public void deleteRegistrationEvent(Long id);
    public RegistrationEvent updateWindow(Long id, LocalDate start, LocalDate end);
    public RegistrationRequest getRegistrationRequestById(Long id);
    public Collection<RegistrationRequest> getRegistrationRequests();
    public RegistrationRequest createRegistrationRequest(RegistrationRequest registrationRequestDTO);
    public void deleteRegistrationRequest(Long id);
    public boolean updateRegistrationRequest(Long id, RegistrationRequest registrationRequestDTO);

    public Registration getRegistrationById(Long id);
    public Collection<Registration> getRegistrations();
    public Registration createRegistration(Registration registration);
    public void deleteRegistration(Long id);
    public boolean updateRegistration(Long id, Registration registration);
    public RegistrationGroup getRegistrationGroupById(Long id);
    public Collection<RegistrationGroup> getRegistrationGroups();
    public RegistrationGroup createRegistrationGroup(RegistrationGroup registrationGroup);
    public void deleteRegistrationGroup(Long id);
    public boolean updateRegistrationGroup(Long id, RegistrationGroup registrationGroup);
    public RegistrationEvent latest();

}
