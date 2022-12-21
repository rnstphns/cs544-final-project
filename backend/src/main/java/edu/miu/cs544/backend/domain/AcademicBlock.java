package edu.miu.cs544.backend.domain;

import jakarta.persistence.*;
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
    private String code; //TODO validate code? 2022-12A-12D
    private String name; //TODO validate name? December 2022
    private String semester; //TODO validate? Spring
    private LocalDate startDate;
    private LocalDate endDate;



}
