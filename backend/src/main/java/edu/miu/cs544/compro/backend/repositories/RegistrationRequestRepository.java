package edu.miu.cs544.compro.backend.repositories;

import edu.miu.cs544.compro.backend.domain.RegistrationRequest;
import edu.miu.cs544.compro.backend.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest, Long> {

    Optional<RegistrationRequest> findByStudent(Student s);
}
