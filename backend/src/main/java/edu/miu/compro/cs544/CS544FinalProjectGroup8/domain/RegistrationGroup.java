package edu.miu.compro.cs544.CS544FinalProjectGroup8.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
public class RegistrationGroup {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToMany
    private Collection<Student> students;
    @ManyToMany
    private Collection<CourseOffering> courses;
}
