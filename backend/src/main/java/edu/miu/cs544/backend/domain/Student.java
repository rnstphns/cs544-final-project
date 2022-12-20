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

public class Student extends Person{
    @Column(name = "studentId", length = 10, nullable = false, unique = true)
    private Integer studentId;


}
