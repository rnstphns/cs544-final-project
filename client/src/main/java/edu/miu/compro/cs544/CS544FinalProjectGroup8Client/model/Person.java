package edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.validation.constraints.Email;


@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Person {

    private String name;
    @Email
    private String email;
    private Address address;
}
