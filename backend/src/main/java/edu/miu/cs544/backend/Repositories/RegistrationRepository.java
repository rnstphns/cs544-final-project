package edu.miu.cs544.backend.Repositories;

import edu.miu.cs544.backend.domain.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    public Collection<Registration> findByStudentId(Integer studentId);
}

