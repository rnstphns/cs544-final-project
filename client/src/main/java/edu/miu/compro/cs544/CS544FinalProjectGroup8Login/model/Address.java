package edu.miu.compro.cs544.CS544FinalProjectGroup8Login.model;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Address {


    private String street;
    private String city;
    private String postCode;
    private String stateProvince;
    private String countryRegion;

}
