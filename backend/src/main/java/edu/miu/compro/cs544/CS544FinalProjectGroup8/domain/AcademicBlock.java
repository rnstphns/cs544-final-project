package edu.miu.compro.cs544.CS544FinalProjectGroup8.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class AcademicBlock {

    @Id
    @GeneratedValue
    private Long id;
    private String code; //TODO validate code? 2022-12A-12D
    private String name; //TODO validate name? December 2022
    private String semester; //TODO validate? Spring
    private LocalDate startDate;
    private LocalDate endDate;

}
