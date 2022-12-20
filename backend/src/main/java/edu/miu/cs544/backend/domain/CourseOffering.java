package edu.miu.cs544.backend.domain;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class CourseOffering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30, nullable = false, unique = true)
    private String code; //TODO validate code? S544-2022-12A-12D-XX
    @OneToOne
    @PrimaryKeyJoinColumn
    private AcademicBlock academicBlock;
    @OneToOne
    private Course course;
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<Faculty> faculty;
    private Integer availableSeats;
    private Integer capacity;
    @ManyToOne(cascade = CascadeType.ALL)
    private RegistrationRequest registrationRequest;
}
