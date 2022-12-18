package edu.miu.compro.cs544.CS544FinalProjectGroup8.service;

import java.time.LocalDate;
import java.util.Collection;

import edu.miu.compro.cs544.CS544FinalProjectGroup8.domain.Registration;
import edu.miu.compro.cs544.CS544FinalProjectGroup8.domain.RegistrationEvent;
import edu.miu.compro.cs544.CS544FinalProjectGroup8.domain.RegistrationGroup;
import edu.miu.compro.cs544.CS544FinalProjectGroup8.domain.RegistrationRequest;

public interface RegistrationEventService {

    public Collection<RegistrationGroup> findRegistrationGroupsWithStudent(String studentId);

    public RegistrationEvent getRegistrationEventById(long id);
    public Collection<RegistrationEvent> getRegistrationEvents();
    public RegistrationEvent createRegistrationEvent(RegistrationEvent registrationEvent);
    public void deleteRegistrationEvent(long id);
    public RegistrationEvent updateWindow(long id, LocalDate start, LocalDate end);
    public RegistrationRequest getRegistrationRequestById(long id);
    public Collection<RegistrationRequest> getRegistrationRequests();
    public RegistrationRequest createRegistrationRequest(RegistrationRequest registrationRequestDTO);
    public void deleteRegistrationRequest(long id);
    public boolean updateRegistrationRequest(long id, RegistrationRequest registrationRequestDTO);

    public Registration getRegistrationById(long id);
    public Collection<Registration> getRegistrations();
    public Registration createRegistration(Registration registration);
    public void deleteRegistration(long id);
    public boolean updateRegistration(long id, Registration registration);
    public RegistrationGroup getRegistrationGroupById(long id);
    public Collection<RegistrationGroup> getRegistrationGroups();
    public RegistrationGroup createRegistrationGroup(RegistrationGroup registrationGroup);
    public void deleteRegistrationGroup(long id);
    public boolean updateRegistrationGroup(long id, RegistrationGroup registrationGroup);
    public RegistrationEvent latest();

}
