package edu.miu.cs544.backend.Repositories;

import edu.miu.cs544.backend.domain.RegistrationGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface RegistrationGroupRepository extends JpaRepository<RegistrationGroup, Long> {
    //TODO implement this or service level filter
    @Query(value = "select * from registration_group_courses inner join registration_group on registration_group_id=id where student_id = :studentId", nativeQuery = true)
    public Collection<RegistrationGroup> findRegistrationGroupsWithStudent(@Param(value= "studentId") String studentId);


////    When students read RegistrationEvent, system filters the list of registration groups to the one that
////    student is a member of. Furthermore, the list of students will be masked for student view. So,
////    students see RegistrationEvent -> RegistrationGroups -> AcademicBlocks -> CourseOfferings
}
