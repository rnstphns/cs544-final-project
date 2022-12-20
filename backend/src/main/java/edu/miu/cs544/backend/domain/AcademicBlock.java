package edu.miu.cs544.backend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code; //TODO validate code? 2022-12A-12D
    private String name; //TODO validate name? December 2022
    private String semester; //TODO validate? Spring
    private LocalDate startDate;
    private LocalDate endDate;



}
