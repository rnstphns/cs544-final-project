package edu.miu.cs544.compro.backend.service;

import edu.miu.cs544.compro.backend.domain.AcademicBlock;
import edu.miu.cs544.compro.backend.exceptions.DatabaseException;
import edu.miu.cs544.compro.backend.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AcademicBlockService {
    List<AcademicBlock> findAll();

    AcademicBlock findById(Long id) throws ObjectNotFoundException;

    void delete(Long id);

    AcademicBlock create(AcademicBlock ab) throws DatabaseException;

    boolean update(Long id, AcademicBlock ab);
}
