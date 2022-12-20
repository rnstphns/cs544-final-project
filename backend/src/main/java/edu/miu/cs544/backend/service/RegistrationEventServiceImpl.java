package edu.miu.cs544.backend.service;


import edu.miu.cs544.backend.exceptions.DatabaseException;
import edu.miu.cs544.backend.repositories.RegistrationEventRepository;
import edu.miu.cs544.backend.domain.RegistrationEvent;
import edu.miu.cs544.backend.domain.RegistrationGroup;
import edu.miu.cs544.backend.domain.Student;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class RegistrationEventServiceImpl implements RegistrationEventService {
    @Autowired
    private RegistrationGroupService registrationGroupService;
    @Autowired
    private RegistrationEventRepository registrationEventRepository;

    //TODO email student when their registration is saved
    //TODO fix update methods

    @Override
    public RegistrationEvent getRegistrationEventById(long id) {
        return registrationEventRepository.findById(id).get();
    }

    @Override
    public Collection<RegistrationEvent> getRegistrationEvents() {
        return registrationEventRepository.findAll();
    }

    @Override
    public RegistrationEvent createRegistrationEvent(RegistrationEvent registrationEvent) throws DatabaseException{
        Collection<RegistrationGroup> groups = registrationEvent.getRegistrationGroups();
        for (RegistrationGroup g : groups) {
                registrationGroupService.create(g);
        }
        return registrationEventRepository.save(registrationEvent);
    }

    @Override
    public void deleteRegistrationEvent(Long id) {
        registrationEventRepository.deleteById(id);
    }

    @Override
    public RegistrationEvent updateWindow(Long id, LocalDate start, LocalDate end) {
        Optional<RegistrationEvent> registrationEventOptional = registrationEventRepository.findById(id);
        if(registrationEventOptional.isPresent()) {
            RegistrationEvent registrationEvent = registrationEventOptional.get();
            registrationEvent.setStartDate(start);
            registrationEvent.setEndDate(end);
            registrationEventRepository.save(registrationEvent);
            return registrationEvent;
        }
        else return null;
    }
    @Override
    public RegistrationEvent latest() {
        LocalDate localDateNow = LocalDate.now();
        List<RegistrationEvent> registrationEventList = registrationEventRepository.findAll(Sort.by(Sort.Direction.DESC, "endDate"));
        RegistrationEvent returnEvent = new RegistrationEvent();
        try {
            returnEvent = registrationEventList.get(0);
        } catch (IndexOutOfBoundsException e) {
            log.error("No events in the database");
        }
        Collection<RegistrationGroup> groups = returnEvent.getRegistrationGroups();
        for (RegistrationGroup g : groups) {
            g.setStudents(new ArrayList<Student>());
        }
        returnEvent.setRegistrationGroups(groups);
        return returnEvent;
    }
}
