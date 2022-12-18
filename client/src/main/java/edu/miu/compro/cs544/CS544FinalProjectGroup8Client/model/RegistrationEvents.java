package edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
@Data
public class RegistrationEvents {

    private Collection<RegistrationEvent> registrationEventCollection = new ArrayList<>();

}
