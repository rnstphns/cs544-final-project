package edu.miu.compro.cs544.CS544FinalProjectGroup8.service;

import edu.miu.compro.cs544.CS544FinalProjectGroup8.domain.RegistrationEvent;
import edu.miu.compro.cs544.CS544FinalProjectGroup8.repositories.RegistrationEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class RegistrationEventServiceImpl implements RegistrationEventService {

    @Autowired
    private RegistrationEventRepository repository;

    @Override
    public RegistrationEvent getRegistrationEventById(long id) {
        return repository.findById(id).get();
    }

    @Override
    public Collection<RegistrationEvent> getRegistrationEvents() {
        return repository.findAll();
    }

    @Override
    public RegistrationEvent createRegistrationEvent(RegistrationEvent registrationEvent) {
        return repository.save(registrationEvent);
    }

    @Override
    public boolean deleteRegistrationEvent(long id) {
        Optional<RegistrationEvent> registrationEvent = repository.findById(id);
        if(registrationEvent.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        else return false;
    }

    @Override
    public boolean updateWindow(long id, LocalDate start, LocalDate end) {
        Optional<RegistrationEvent> registrationEventOptional = repository.findById(id);
        if(registrationEventOptional.isPresent()) {
            RegistrationEvent registrationEvent = registrationEventOptional.get();
            registrationEvent.setStartDate(start);
            registrationEvent.setEndDate(end);
            repository.save(registrationEvent);
            return true;
        }
        else return false;
    }
}
