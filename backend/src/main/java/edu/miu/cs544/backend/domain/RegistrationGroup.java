package edu.miu.cs544.backend.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
public class RegistrationGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(cascade =CascadeType.PERSIST)
    private Collection<Student> students;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Collection<CourseOffering> courses;
}
