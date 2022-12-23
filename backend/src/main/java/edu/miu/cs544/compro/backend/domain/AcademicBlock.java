package edu.miu.cs544.compro.backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcademicBlock {

    @Id
    private Long id;
    @Column(length = 30, nullable = false, unique = true)
    private String code;
    private String name;
    private String semester;
    private LocalDate startDate;
    private LocalDate endDate;



}
