package edu.miu.compro.cs544.CS544FinalProjectGroup8.repositories;

import edu.miu.compro.cs544.CS544FinalProjectGroup8.domain.RegistrationGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationGroupRepository extends JpaRepository<RegistrationGroup, Long> {
    //TODO  "When students read RegistrationEvent, system filters the list of registration groups to the one that
    //student is a member of. Furthermore, the list of students will be masked for student view. "

}
