package edu.miu.cs544.compro.backend.service;

import edu.miu.cs544.compro.backend.domain.Student;
import edu.miu.cs544.compro.backend.exceptions.DatabaseException;
import edu.miu.cs544.compro.backend.exceptions.ObjectNotFoundException;

import java.util.List;

public interface StudentService {
    List<Student> findAll();

    Student findById(Long id) throws ObjectNotFoundException;

    Student findByStudentId(Integer studentId) throws DatabaseException;

    void delete(Long id);

    Student create(Student student) throws DatabaseException;

    boolean update(Long id, Student student);
}
