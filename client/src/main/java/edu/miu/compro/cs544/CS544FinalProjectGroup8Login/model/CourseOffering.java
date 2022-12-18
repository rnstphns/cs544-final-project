package edu.miu.compro.cs544.CS544FinalProjectGroup8Login.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;


@Data
@NoArgsConstructor
public class CourseOffering {

    private String code; //S544-2022-12A-12D-XX
    private AcademicBlock academicBlock;
    private Course course;
    private Collection<Faculty> faculty;
    private Integer availableSeats;
    private Integer capacity;
    private RegistrationRequest registrationRequest;
}
