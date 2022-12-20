package edu.miu.cs544.backend.service;


import edu.miu.cs544.backend.domain.CourseOffering;
import edu.miu.cs544.backend.exceptions.DatabaseException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CourseOfferingService {
    List<CourseOffering> findAll ();
    CourseOffering findById(Long id);
    void delete(Long id);
    void create(CourseOffering courseOffering) throws DatabaseException;
    boolean update(Long id, CourseOffering courseOffering);
}
