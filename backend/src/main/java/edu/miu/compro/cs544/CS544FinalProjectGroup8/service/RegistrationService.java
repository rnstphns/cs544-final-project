package edu.miu.compro.cs544.CS544FinalProjectGroup8.service;

import edu.miu.compro.cs544.CS544FinalProjectGroup8.domain.CourseOffering;
import edu.miu.compro.cs544.CS544FinalProjectGroup8.domain.Registration;
import edu.miu.compro.cs544.CS544FinalProjectGroup8.domain.Student;

import java.util.Collection;

public interface RegistrationService {

    public Registration getRegistrationEventById(long id);
    public Collection<Registration> getRegistrationGroups();
    public Registration createRegistrationEvent(Registration registrationDTO);
    public Registration deleteRegistrationEvent(long id);
    public void updateRegistrationGroup(long id, Registration registrationDTO);
}