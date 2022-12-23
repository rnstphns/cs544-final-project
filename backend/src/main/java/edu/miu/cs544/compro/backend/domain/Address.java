package edu.miu.cs544.compro.backend.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {


    private String street;
    private String city;
    private String postCode;
    private String stateProvince;
    private String countryRegion;

}
