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
    @ManyToMany(cascade = CascadeType.ALL)
    private Collection<Student> students;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Collection<CourseOffering> courses;
}
