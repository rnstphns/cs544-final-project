package edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;


@Data
@NoArgsConstructor
public class Faculty extends Person {

    private String title;

    public Faculty(String name, @Email String email, Address address, String title) {
        super(name, email, address);
        this.title = title;
    }
}
