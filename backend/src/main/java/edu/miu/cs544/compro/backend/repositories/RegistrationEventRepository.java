package edu.miu.cs544.compro.backend.repositories;

import edu.miu.cs544.compro.backend.domain.RegistrationEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationEventRepository extends JpaRepository<RegistrationEvent, Long> {

    @Override
    Page<RegistrationEvent> findAll(Pageable pageable);
}
