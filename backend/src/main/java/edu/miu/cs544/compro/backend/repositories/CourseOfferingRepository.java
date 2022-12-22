package edu.miu.cs544.compro.backend.repositories;

import edu.miu.cs544.compro.backend.domain.CourseOffering;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseOfferingRepository extends JpaRepository<CourseOffering, Long> {
    Optional<CourseOffering> findByCode(String code);
}
