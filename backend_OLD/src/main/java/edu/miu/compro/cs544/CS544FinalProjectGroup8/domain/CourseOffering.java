package edu.miu.compro.cs544.CS544FinalProjectGroup8.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
public class CourseOffering {

    @Id
    @GeneratedValue
    private Long id;
    private String code;
    @OneToOne
    private AcademicBlock academicBlock;
    @OneToOne
    private Course course;
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<Faculty> faculty;
    private Integer availableSeats;
    private Integer capacity;
    @ManyToOne(cascade = CascadeType.ALL)
    private RegistrationRequest registrationRequest;

    public CourseOffering(String code, AcademicBlock academicBlock, Course course, Collection<Faculty> faculty, Integer availableSeats, Integer capacity) {
        this.code = code;
        this.academicBlock = academicBlock;
        this.course = course;
        this.faculty = faculty;
        this.availableSeats = availableSeats;
        this.capacity = capacity;
    }
}
