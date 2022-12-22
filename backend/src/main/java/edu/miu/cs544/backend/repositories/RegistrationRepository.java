package edu.miu.cs544.backend.repositories;

import edu.miu.cs544.backend.domain.Registration;
import edu.miu.cs544.backend.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    public Collection<Registration> findTop12ByStudent(Student student);
}

