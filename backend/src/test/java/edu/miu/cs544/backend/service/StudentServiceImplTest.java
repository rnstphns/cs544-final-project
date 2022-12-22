package edu.miu.cs544.backend.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.miu.cs544.backend.domain.Student;
import edu.miu.cs544.backend.exceptions.DatabaseException;
import edu.miu.cs544.backend.exceptions.ObjectNotFoundException;
import edu.miu.cs544.backend.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Ignore;
import org.junit.Rule;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {StudentServiceImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class StudentServiceImplTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    /**
     * Method under test: {@link StudentServiceImpl#findAll()}
     */
    @Test
    public void testFindAll() {
        ArrayList<Student> studentList = new ArrayList<>();
        when(studentRepository.findAll()).thenReturn(studentList);
        List<Student> actualFindAllResult = studentServiceImpl.findAll();
        assertSame(studentList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(studentRepository).findAll();
    }

    /**
     * Method under test: {@link StudentServiceImpl#findById(Long)}
     */
    @Test
    public void testFindById() throws ObjectNotFoundException {
        Student student = new Student();
        when(studentRepository.findById((Long) any())).thenReturn(Optional.of(student));
        assertSame(student, studentServiceImpl.findById(123L));
        verify(studentRepository).findById((Long) any());
    }


    /**
     * Method under test: {@link StudentServiceImpl#findByStudentId(Integer)}
     */
    @Test
    public void testFindByStudentId() throws DatabaseException {
        Student student = new Student();
        when(studentRepository.findByStudentId((Integer) any())).thenReturn(Optional.of(student));
        assertSame(student, studentServiceImpl.findByStudentId(123));
        verify(studentRepository).findByStudentId((Integer) any());
    }

    /**
     * Method under test: {@link StudentServiceImpl#findByStudentId(Integer)}
     */
    @Test
    public void testFindByStudentId2() throws DatabaseException {
        when(studentRepository.findByStudentId((Integer) any())).thenReturn(Optional.empty());
        thrown.expect(DatabaseException.class);
        studentServiceImpl.findByStudentId(123);
        verify(studentRepository).findByStudentId((Integer) any());
    }

    /**
     * Method under test: {@link StudentServiceImpl#delete(Long)}
     */
    @Test
    public void testDelete() {
        doNothing().when(studentRepository).deleteById((Long) any());
        studentServiceImpl.delete(123L);
        verify(studentRepository).deleteById((Long) any());
        assertTrue(studentServiceImpl.findAll().isEmpty());
    }

    /**
     * Method under test: {@link StudentServiceImpl#create(Student)}
     */
    @Test
    public void testCreate() throws DatabaseException {
        Student student = new Student();
        when(studentRepository.save((Student) any())).thenReturn(student);
        assertSame(student, studentServiceImpl.create(new Student()));
        verify(studentRepository).save((Student) any());
    }

    /**
     * Method under test: {@link StudentServiceImpl#update(Long, Student)}
     */
    @Test
    public void testUpdate() {
        when(studentRepository.save((Student) any())).thenReturn(new Student());
        when(studentRepository.findById((Long) any())).thenReturn(Optional.of(new Student()));
        assertTrue(studentServiceImpl.update(123L, new Student()));
        verify(studentRepository).save((Student) any());
        verify(studentRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link StudentServiceImpl#update(Long, Student)}
     */
    @Test
    public void testUpdate2() {
        when(studentRepository.save((Student) any())).thenReturn(new Student());
        when(studentRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertFalse(studentServiceImpl.update(123L, new Student()));
        verify(studentRepository).findById((Long) any());
    }


}

