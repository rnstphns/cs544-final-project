package edu.miu.compro.cs544.CS544FinalProjectGroup8.repositories;

import edu.miu.compro.cs544.CS544FinalProjectGroup8.domain.RegistrationEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface RegistrationEventRepository extends JpaRepository<RegistrationEvent, Long> {

    public RegistrationEvent findByStartDateLike(LocalDate startDate);
}
