package edu.miu.compro.cs544.CS544FinalProjectGroup8.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
public class RegistrationEvent {
    @Id
    @GeneratedValue
    private long id;
    @Future
    private LocalDate startDate;
    @Future
    private LocalDate endDate;
    @OneToMany(cascade= CascadeType.ALL)
    private Collection<RegistrationGroup> registrationGroups;
}
