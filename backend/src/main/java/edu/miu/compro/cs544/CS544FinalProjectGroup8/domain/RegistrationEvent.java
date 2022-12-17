package edu.miu.compro.cs544.CS544FinalProjectGroup8.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Future;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
public class RegistrationEvent {
    @Id
    @GeneratedValue
    private Long id;
    @Future
    private LocalDate startDate;
    @Future
    private LocalDate endDate;
    @OneToMany//TODO extras maybe?
    private Collection<RegistrationGroup> registrationGroups;
}
