package edu.miu.compro.cs544.CS544FinalProjectGroup8.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
public class Faculty extends Person {

    private String title;

    public Faculty(String name, String email, Address address, String title) {
        this.name=  name;
        this.email= email;
        this.address =  address;
        this.title = title;
    }
}
