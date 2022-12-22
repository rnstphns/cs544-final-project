package edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;


@Data
@NoArgsConstructor
public class RegistrationEvent implements Serializable {

    @FutureOrPresent
    private LocalDate startDate;
    @Future
    private LocalDate endDate;
    private Collection<RegistrationGroup> registrationGroups;
}
