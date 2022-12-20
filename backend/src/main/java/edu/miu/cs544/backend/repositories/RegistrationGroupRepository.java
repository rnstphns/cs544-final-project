package edu.miu.cs544.backend.repositories;

import edu.miu.cs544.backend.domain.RegistrationGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface RegistrationGroupRepository extends JpaRepository<RegistrationGroup, Long> {


}
