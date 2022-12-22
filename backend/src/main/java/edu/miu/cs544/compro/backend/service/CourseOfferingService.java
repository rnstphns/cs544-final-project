package edu.miu.cs544.compro.backend.service;


import edu.miu.cs544.compro.backend.domain.CourseOffering;
import edu.miu.cs544.compro.backend.exceptions.DatabaseException;
import edu.miu.cs544.compro.backend.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CourseOfferingService {
    List<CourseOffering> findAll ();
    CourseOffering findById(Long id) throws ObjectNotFoundException;

    CourseOffering findByCode(String code) throws DatabaseException;
    void delete(Long id);
    void create(CourseOffering courseOffering) throws DatabaseException;


    boolean update(Long id, CourseOffering courseOffering);
}
