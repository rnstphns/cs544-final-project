package edu.miu.cs544.backend.Repositories;

import edu.miu.cs544.backend.domain.RegistrationEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationEventRepository extends JpaRepository<RegistrationEvent, Long> {

}
