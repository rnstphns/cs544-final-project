package edu.miu.compro.cs544.CS544FinalProjectGroup8.service;

import java.util.Collection;

public interface RegistrationGroupService {

    public RegistrationGroup getRegistrationEventById(long id);
    public Collection<RegistrationGroup> getRegistrationGroups();
    public RegistrationGroup createRegistrationEvent(RegistrationGroup registrationGroupDTO);
    public RegistrationGroup deleteRegistrationEvent(long id);
    public void updateRegistrationGroup(long id, RegistrationGroup registrationGroupDTO);
}
