package edu.miu.cs544.backend.service;

import edu.miu.cs544.backend.domain.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();

    Student findById(Long id);

    void delete(Long id);

    Student create(Student student);

    boolean update(Long id, Student student);
}
