package edu.miu.cs544.backend.repositories;

import edu.miu.cs544.backend.domain.AcademicBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicBlockRepository extends JpaRepository<AcademicBlock, Long> {
}
