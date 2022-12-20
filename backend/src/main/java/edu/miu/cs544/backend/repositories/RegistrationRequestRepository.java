package edu.miu.cs544.backend.repositories;

import edu.miu.cs544.backend.domain.RegistrationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest, Long> {
}
