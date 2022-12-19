package edu.miu.cs544.backend.service;


import edu.miu.cs544.backend.domain.CourseOffering;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CourseOfferingService {
    List<CourseOffering> findAll ();
    CourseOffering findById(Long id);
    void delete(Long id);
    CourseOffering create(CourseOffering courseOffering);
    boolean update(Long id, CourseOffering courseOffering);
}
