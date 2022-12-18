package edu.miu.compro.cs544.CS544FinalProjectGroup8Login.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import java.time.LocalDate;
import java.util.Collection;


@Data
@NoArgsConstructor
public class RegistrationEvent {

    @Future
    private LocalDate startDate;
    @Future
    private LocalDate endDate;
    private Collection<RegistrationGroup> registrationGroups;
}
