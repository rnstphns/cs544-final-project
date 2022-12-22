package edu.miu.cs544.backend.service;

import edu.miu.cs544.backend.domain.RegistrationGroup;
import edu.miu.cs544.backend.exceptions.DatabaseException;
import edu.miu.cs544.backend.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public interface RegistrationGroupService {

    List<RegistrationGroup> findAll();

    RegistrationGroup findById(Long id) throws ObjectNotFoundException;

    void delete(Long id);

    RegistrationGroup create(RegistrationGroup r) throws DatabaseException;

    boolean update(Long id, RegistrationGroup r);
}
