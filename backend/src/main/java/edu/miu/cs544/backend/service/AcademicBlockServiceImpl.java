package edu.miu.cs544.backend.service;

import edu.miu.cs544.backend.domain.AcademicBlock;
import edu.miu.cs544.backend.exceptions.DatabaseException;
import edu.miu.cs544.backend.exceptions.ObjectNotFoundException;
import edu.miu.cs544.backend.repositories.AcademicBlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AcademicBlockServiceImpl implements AcademicBlockService{

    @Autowired
    private AcademicBlockRepository repository;
    @Override
    public List<AcademicBlock> findAll() {
        return repository.findAll();
    }

    @Override
    public AcademicBlock findById(Long id) throws ObjectNotFoundException {
        Optional<AcademicBlock> found = repository.findById(id);
        if(found.isPresent())
            return found.get();
        else
            throw new ObjectNotFoundException("AcademicBlock is not in database");

    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public AcademicBlock create(AcademicBlock ab) throws DatabaseException {
        try{
            return repository.save(ab);
        }catch(Exception e){
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public boolean update(Long id, AcademicBlock ab) {
        return false;
    }
}
