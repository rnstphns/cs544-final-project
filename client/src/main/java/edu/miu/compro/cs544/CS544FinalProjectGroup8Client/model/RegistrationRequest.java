package edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class RegistrationRequest {


    private Student student;
    private List<CourseOffering> courseList;
}
