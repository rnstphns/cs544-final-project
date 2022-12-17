package edu.miu.compro.cs544.CS544FinalProjectGroup8.service;

import edu.miu.compro.cs544.CS544FinalProjectGroup8.domain.RegistrationRequest;

import java.util.Collection;

public interface RegistrationRequestService {
    public RegistrationRequest getRegistrationEventById(long id);
    public Collection<RegistrationRequest> getRegistrationGroups();
    public RegistrationRequest createRegistrationEvent(RegistrationRequest registrationRequestDTO);
    public RegistrationRequest deleteRegistrationEvent(long id);
    public void updateRegistrationGroup(long id, RegistrationRequest registrationRequestDTO);
}
