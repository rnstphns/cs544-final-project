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
    private String code; //TODO validate code? S544-2022-12A-12D-XX
    @OneToOne
    private AcademicBlock academicBlock;
    @OneToOne
    private Course course;
    @OneToMany
    private Collection<Faculty> faculty;
    private Integer availableSeats;
    private Integer capacity;
    @ManyToOne //TODO extras maybe?
    private RegistrationRequest registrationRequest;
}
