package edu.miu.compro.cs544.CS544FinalProjectGroup8.service;

import edu.miu.compro.cs544.CS544FinalProjectGroup8.domain.RegistrationGroup;
import lombok.Data;


import java.time.LocalDate;
import java.util.Collection;
@Data
public class RegistrationEvent {

    private LocalDate startDate;
    private LocalDate endDate;
    private Collection<RegistrationGroup> registrationGroups;
}
