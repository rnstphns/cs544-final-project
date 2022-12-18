package edu.miu.compro.cs544.CS544FinalProjectGroup8.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
public class Student extends Person {

    private Integer studentId;

    public Student(String name, String email, Address address, Integer studentId) {
        this.name=  name;
        this.email= email;
        this.address =  address;
        this.studentId = studentId;
    }
}

