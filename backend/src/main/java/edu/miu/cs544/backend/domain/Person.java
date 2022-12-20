package edu.miu.cs544.backend.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person {

    @Id
    @GeneratedValue(strategy= GenerationType.TABLE)
    private Long id;
    private String name;
    @Email
    @Column(name = "email", length = 30, nullable = false, unique = true)
    private String email;
    @Embedded
    private Address address;
}
