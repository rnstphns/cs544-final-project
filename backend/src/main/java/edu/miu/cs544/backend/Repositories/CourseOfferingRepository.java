package edu.miu.cs544.backend.Repositories;

import edu.miu.cs544.backend.domain.CourseOffering;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseOfferingRepository extends JpaRepository<CourseOffering, Long> {
}
