package edu.miu.compro.cs544.CS544FinalProjectGroup8.repositories;

import edu.miu.compro.cs544.CS544FinalProjectGroup8.domain.RegistrationGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface RegistrationGroupRepository extends JpaRepository<RegistrationGroup, Long> {

    public Collection<RegistrationGroup> findByStudent(String studentId);



}
