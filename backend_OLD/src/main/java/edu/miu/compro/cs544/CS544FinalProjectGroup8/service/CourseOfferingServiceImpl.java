package edu.miu.compro.cs544.CS544FinalProjectGroup8.service;

import edu.miu.compro.cs544.CS544FinalProjectGroup8.domain.CourseOffering;
import edu.miu.compro.cs544.CS544FinalProjectGroup8.repositories.CourseOfferingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseOfferingServiceImpl implements CourseOfferingService {
    @Autowired
    private CourseOfferingRepository courseOfferingRepository;

    @Override
    public List<CourseOffering> courseOffering() {
        return courseOfferingRepository.findAll();
    }
}
