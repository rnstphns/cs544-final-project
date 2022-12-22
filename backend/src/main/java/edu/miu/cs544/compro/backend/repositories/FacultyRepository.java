package edu.miu.cs544.compro.backend.repositories;

import edu.miu.cs544.compro.backend.domain.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
