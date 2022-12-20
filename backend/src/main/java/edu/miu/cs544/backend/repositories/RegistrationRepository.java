package edu.miu.cs544.backend.repositories;

import edu.miu.cs544.backend.domain.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

   //TODO query?
}

