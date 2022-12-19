package edu.miu.cs544.backend.Repositories;

import edu.miu.cs544.backend.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
