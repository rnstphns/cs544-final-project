package edu.miu.cs544.backend.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Student student;
    @ManyToMany(cascade=CascadeType.PERSIST)
    @JoinTable(name="request_course_offering_join_table")
    private List<CourseOffering> courseList;

}
