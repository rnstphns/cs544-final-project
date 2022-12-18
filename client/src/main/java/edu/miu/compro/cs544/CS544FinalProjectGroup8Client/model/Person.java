package edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.validation.constraints.Email;


@Data
@NoArgsConstructor
public abstract class Person {

    private String name;
    @Email
    private String email;
    @Embedded
    private Address address;
}
