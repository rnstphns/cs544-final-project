package edu.miu.compro.cs544.CS544FinalProjectGroup8.service;

import edu.miu.compro.cs544.CS544FinalProjectGroup8.domain.CourseOffering;
import edu.miu.compro.cs544.CS544FinalProjectGroup8.domain.Student;
import lombok.Data;


import java.util.List;

@Data
public class RegistrationDTO {

    private Student student;
    private List<CourseOffering> courseList;
}
