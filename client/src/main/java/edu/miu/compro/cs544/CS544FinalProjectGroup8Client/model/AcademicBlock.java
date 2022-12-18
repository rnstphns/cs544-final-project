package edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcademicBlock {


    private String code;//2022-12A-12D
    private String name; // December 2022
    private String semester; //season
    private LocalDate startDate;
    private LocalDate endDate;

}
