package edu.miu.compro.cs544.CS544FinalProjectGroup8.service;

import edu.miu.compro.cs544.CS544FinalProjectGroup8.domain.CourseOffering;
import edu.miu.compro.cs544.CS544FinalProjectGroup8.domain.Student;
import lombok.Data;


import java.util.Collection;

@Data
public class RegistrationGroup {

    private Collection<Student> students;
    private Collection<CourseOffering> courses;
}
