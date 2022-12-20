package edu.miu.cs544.backend.service;


import edu.miu.cs544.backend.repositories.*;
import edu.miu.cs544.backend.domain.AcademicBlock;
import edu.miu.cs544.backend.domain.Course;
import edu.miu.cs544.backend.domain.CourseOffering;
import edu.miu.cs544.backend.domain.Faculty;
import edu.miu.cs544.backend.exceptions.DatabaseException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CourseOfferingServiceImpl implements CourseOfferingService {
    @Autowired
    private CourseOfferingRepository courseOfferingRepository;

    @Autowired
    private AcademicBlockRepository academicBlockRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public List<CourseOffering> findAll() {
        return courseOfferingRepository.findAll();
    }

    @Override
    public CourseOffering findById(Long id) {
        return courseOfferingRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        courseOfferingRepository.deleteById(id);
    }

    @Override
    public CourseOffering create(CourseOffering courseOffering) throws DatabaseException {
        try{
            AcademicBlock block = courseOffering.getAcademicBlock();
            academicBlockRepository.save(block);
            Course course = courseOffering.getCourse();
            courseRepository.save(course);
            Collection<Faculty> faculty = courseOffering.getFaculty();
            for(Faculty f: faculty) {
                facultyRepository.save(f);
            }
            return courseOfferingRepository.save(courseOffering);
        }catch(Exception e){
            throw new DatabaseException(e.getMessage());
        }

    }

    @Override
    public boolean update(Long id, CourseOffering courseOffering) {
        Optional<CourseOffering> offering = courseOfferingRepository.findById(id);
        if(offering.isPresent()){
            AcademicBlock block = courseOffering.getAcademicBlock();
            academicBlockRepository.save(block);
            Course course = courseOffering.getCourse();
            courseRepository.save(course);
            Collection<Faculty> faculty = courseOffering.getFaculty();
            for(Faculty f: faculty) {
                facultyRepository.save(f);
            }
            courseOfferingRepository.save(offering.get());
            return true;
        }
        else return false;
    }


}
