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
    private String code;
    private String name;
    private String semester;
    private LocalDate startDate;
    private LocalDate endDate;

    public AcademicBlock(String code, String name, String semester, LocalDate startDate, LocalDate endDate) {
        this.code = code;
        this.name = name;
        this.semester = semester;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
