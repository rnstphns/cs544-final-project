package edu.miu.cs544.backend.service;

import edu.miu.cs544.backend.domain.AcademicBlock;
import edu.miu.cs544.backend.domain.Student;
import edu.miu.cs544.backend.exceptions.DatabaseException;
import edu.miu.cs544.backend.repositories.AcademicBlockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AcademicBlockService {
    List<AcademicBlock> findAll();

    AcademicBlock findById(Long id);

    void delete(Long id);

    AcademicBlock create(AcademicBlock ab) throws DatabaseException;

    boolean update(Long id, AcademicBlock ab);
}
