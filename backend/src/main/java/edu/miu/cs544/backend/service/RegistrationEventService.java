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


    public RegistrationEvent getRegistrationEventById(long id);
    public Collection<RegistrationEvent> getRegistrationEvents();
    public RegistrationEvent createRegistrationEvent(RegistrationEvent registrationEvent);
    public void deleteRegistrationEvent(Long id);
    public RegistrationEvent updateWindow(Long id, LocalDate start, LocalDate end);
    public RegistrationEvent latest();

}
