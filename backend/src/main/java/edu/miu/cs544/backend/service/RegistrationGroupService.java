package edu.miu.cs544.backend.service;

import edu.miu.cs544.backend.domain.RegistrationGroup;
import edu.miu.cs544.backend.exceptions.DatabaseException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public interface RegistrationGroupService {

    Collection<RegistrationGroup> findByStudent(Integer studentId);

    List<RegistrationGroup> findAll();

    RegistrationGroup findById(Long id);

    void delete(Long id);

    RegistrationGroup create(RegistrationGroup r) throws DatabaseException;

    boolean update(Long id, RegistrationGroup r);
}
