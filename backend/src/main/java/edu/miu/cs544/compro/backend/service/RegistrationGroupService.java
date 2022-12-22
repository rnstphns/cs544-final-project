package edu.miu.cs544.compro.backend.service;

import edu.miu.cs544.compro.backend.domain.RegistrationGroup;
import edu.miu.cs544.compro.backend.exceptions.DatabaseException;
import edu.miu.cs544.compro.backend.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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
