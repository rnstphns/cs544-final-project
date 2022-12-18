package edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    private String code; //TODO validate code?
    private String description;
}
