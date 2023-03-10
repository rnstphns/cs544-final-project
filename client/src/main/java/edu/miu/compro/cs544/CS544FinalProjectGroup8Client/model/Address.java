package edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
