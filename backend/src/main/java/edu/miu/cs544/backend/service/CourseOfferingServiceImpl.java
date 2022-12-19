package edu.miu.cs544.backend.service;


import edu.miu.cs544.backend.Repositories.*;
import edu.miu.cs544.backend.domain.AcademicBlock;
import edu.miu.cs544.backend.domain.Course;
import edu.miu.cs544.backend.domain.CourseOffering;
import edu.miu.cs544.backend.domain.Faculty;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

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
    public CourseOffering createCourseOffering(CourseOffering courseOffering) {
        AcademicBlock block = courseOffering.getAcademicBlock();
        academicBlockRepository.save(block);
        Course course = courseOffering.getCourse();
        courseRepository.save(course);
        Collection<Faculty> faculty = courseOffering.getFaculty();
        for(Faculty f: faculty) {
            facultyRepository.save(f);
        }
        return courseOfferingRepository.save(courseOffering);
    }

    @Override
    public boolean updateCourseOffering(Long id, CourseOffering courseOffering) {
        return false;
    }


}
