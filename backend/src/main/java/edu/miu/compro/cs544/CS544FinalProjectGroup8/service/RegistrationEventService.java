package edu.miu.compro.cs544.CS544FinalProjectGroup8.service;

import java.time.LocalDate;
import java.util.Collection;
import edu.miu.compro.cs544.CS544FinalProjectGroup8.domain.RegistrationEvent;

public interface RegistrationEventService {

    public RegistrationEvent getRegistrationEventById(long id);
    public Collection<RegistrationEvent> getRegistrationEvents();
    public RegistrationEvent createRegistrationEvent(RegistrationEvent registrationEvent);
    public boolean deleteRegistrationEvent(long id);
    public boolean updateWindow(long id,LocalDate start, LocalDate end);

}
