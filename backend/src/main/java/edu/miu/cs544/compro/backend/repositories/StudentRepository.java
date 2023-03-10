package edu.miu.cs544.compro.backend.repositories;

import edu.miu.cs544.compro.backend.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByStudentId(Integer studentId);
}
