package edu.miu.compro.cs544.CS544FinalProjectGroup8.repositories;

import edu.miu.compro.cs544.CS544FinalProjectGroup8.domain.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    public Collection<Registration> findByStudentId(Integer studentId);
}

