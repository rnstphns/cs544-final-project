package edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
public class Student extends Person {

    private Integer studentId;

    public Student(String name, @Email String email, Address address, Integer studentId) {
        super(name, email, address);
        this.studentId = studentId;
    }
}
