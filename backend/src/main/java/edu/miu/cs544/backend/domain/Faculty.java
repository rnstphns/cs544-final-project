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
public class Faculty extends Person{
    @Column(name = "title", length = 30, nullable = false)
    private String title;

}
