package edu.miu.cs544.compro.backend.service;


import edu.miu.cs544.compro.backend.domain.RegistrationEvent;
import edu.miu.cs544.compro.backend.exceptions.DatabaseException;
import edu.miu.cs544.compro.backend.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
@Service
public interface RegistrationEventService {


    public RegistrationEvent getRegistrationEventById(long id) throws ObjectNotFoundException;
    public Collection<RegistrationEvent> getRegistrationEvents();
    public RegistrationEvent createRegistrationEvent(RegistrationEvent registrationEvent) throws DatabaseException;
    public void deleteRegistrationEvent(Long id);
    public RegistrationEvent updateWindow(Long id, LocalDate start, LocalDate end);
    public RegistrationEvent latest();

    public boolean processEvent(Long id);

}
