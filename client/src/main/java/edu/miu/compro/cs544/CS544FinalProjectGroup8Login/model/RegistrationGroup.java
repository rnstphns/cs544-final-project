package edu.miu.compro.cs544.CS544FinalProjectGroup8Login.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;


@Data
@NoArgsConstructor
public class RegistrationGroup {


    private Collection<Student> students;
    private Collection<CourseOffering> courses;
}
