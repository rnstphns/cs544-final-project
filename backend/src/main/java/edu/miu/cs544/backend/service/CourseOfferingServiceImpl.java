package edu.miu.cs544.backend.service;


import edu.miu.cs544.backend.domain.*;
import edu.miu.cs544.backend.exceptions.DatabaseException;
import edu.miu.cs544.backend.repositories.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
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
    public CourseOffering findByCode(String code) throws DatabaseException {
        Optional<CourseOffering> found = courseOfferingRepository.findByCode(code);
        if(found.isPresent()){
            return found.get();
        }else{
            throw new DatabaseException("Course Offering "+code+" is not in the database!");
        }
    }

    @Override
    public void delete(Long id) {
        courseOfferingRepository.deleteById(id);
    }

    @Override
    public void create(CourseOffering courseOffering){
        try {
            Optional<CourseOffering> exists = courseOfferingRepository.findByCode(courseOffering.getCode());
            if (exists.isPresent()) {
                log.info("Request caught to create duplicate CourseOffering");
            } else {
                AcademicBlock block = courseOffering.getAcademicBlock();
                academicBlockRepository.save(block);
                Course course = courseOffering.getCourse();
                courseRepository.save(course);
                Collection<Faculty> faculty = courseOffering.getFaculty();
                for (Faculty f : faculty) {
                    facultyRepository.save(f);
                }
                courseOfferingRepository.save(courseOffering);
            }
        }catch(Exception e){
                log.error("Exception caught in create CourseOffering"+e);
        }
    }
    @Override
    public boolean updateRegistrationRequest(Long id, RegistrationRequest request){
        Optional<CourseOffering> found = courseOfferingRepository.findById(id);
        if(found.isPresent()){
            List<RegistrationRequest> requests = found.get().getRegistrationRequests();
            requests.add(request);
            found.get().setRegistrationRequests(requests);
            courseOfferingRepository.save(found.get());
            return true;
        }else
            return false;
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
        else
            return false;
    }


}
