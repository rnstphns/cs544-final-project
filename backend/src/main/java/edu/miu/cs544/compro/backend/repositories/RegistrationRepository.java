package edu.miu.cs544.compro.backend.repositories;

import edu.miu.cs544.compro.backend.domain.Registration;
import edu.miu.cs544.compro.backend.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    public Collection<Registration> findTop12ByStudent(Student student);
}

