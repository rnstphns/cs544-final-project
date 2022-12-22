package edu.miu.cs544.compro.backend.domain;

import jakarta.persistence.*;
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
