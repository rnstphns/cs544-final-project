package edu.miu.cs544.backend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.miu.cs544.backend.domain.CourseOffering;
import edu.miu.cs544.backend.domain.RegistrationGroup;
import edu.miu.cs544.backend.domain.Student;
import edu.miu.cs544.backend.exceptions.DatabaseException;
import edu.miu.cs544.backend.repositories.RegistrationGroupRepository;

import java.util.ArrayList;
import java.util.Collection;
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

@ContextConfiguration(classes = {RegistrationGroupServiceImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class RegistrationGroupServiceImplTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @MockBean
    private CourseOfferingService courseOfferingService;

    @MockBean
    private RegistrationGroupRepository registrationGroupRepository;

    @Autowired
    private RegistrationGroupServiceImpl registrationGroupServiceImpl;

    @MockBean
    private StudentService studentService;

    /**
     * Method under test: {@link RegistrationGroupServiceImpl#findAll()}
     */
    @Test
    public void testFindAll() {
        ArrayList<RegistrationGroup> registrationGroupList = new ArrayList<>();
        when(registrationGroupRepository.findAll()).thenReturn(registrationGroupList);
        List<RegistrationGroup> actualFindAllResult = registrationGroupServiceImpl.findAll();
        assertSame(registrationGroupList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(registrationGroupRepository).findAll();
    }

    /**
     * Method under test: {@link RegistrationGroupServiceImpl#findById(Long)}
     */
    @Test
    public void testFindById() {
        RegistrationGroup registrationGroup = new RegistrationGroup();
        registrationGroup.setCourses(new ArrayList<>());
        registrationGroup.setId(123L);
        registrationGroup.setStudents(new ArrayList<>());
        Optional<RegistrationGroup> ofResult = Optional.of(registrationGroup);
        when(registrationGroupRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(registrationGroup, registrationGroupServiceImpl.findById(123L));
        verify(registrationGroupRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link RegistrationGroupServiceImpl#findById(Long)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testFindById2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:143)
        //       at edu.miu.cs544.backend.service.RegistrationGroupServiceImpl.findById(RegistrationGroupServiceImpl.java:39)
        //   See https://diff.blue/R013 to resolve this issue.

        when(registrationGroupRepository.findById((Long) any())).thenReturn(Optional.empty());
        registrationGroupServiceImpl.findById(123L);
    }

    /**
     * Method under test: {@link RegistrationGroupServiceImpl#delete(Long)}
     */
    @Test
    public void testDelete() {
        doNothing().when(registrationGroupRepository).deleteById((Long) any());
        registrationGroupServiceImpl.delete(123L);
        verify(registrationGroupRepository).deleteById((Long) any());
        assertTrue(registrationGroupServiceImpl.findAll().isEmpty());
    }

    /**
     * Method under test: {@link RegistrationGroupServiceImpl#create(RegistrationGroup)}
     */
    @Test
    public void testCreate() throws DatabaseException {
        RegistrationGroup registrationGroup = new RegistrationGroup();
        registrationGroup.setCourses(new ArrayList<>());
        registrationGroup.setId(123L);
        registrationGroup.setStudents(new ArrayList<>());
        when(registrationGroupRepository.save((RegistrationGroup) any())).thenReturn(registrationGroup);

        RegistrationGroup registrationGroup1 = new RegistrationGroup();
        ArrayList<CourseOffering> courseOfferingList = new ArrayList<>();
        registrationGroup1.setCourses(courseOfferingList);
        registrationGroup1.setId(123L);
        registrationGroup1.setStudents(new ArrayList<>());
        assertSame(registrationGroup, registrationGroupServiceImpl.create(registrationGroup1));
        verify(registrationGroupRepository).save((RegistrationGroup) any());
        assertEquals(courseOfferingList, registrationGroup1.getCourses());
        assertEquals(courseOfferingList, registrationGroup1.getStudents());
    }

    /**
     * Method under test: {@link RegistrationGroupServiceImpl#create(RegistrationGroup)}
     */
    @Test
    public void testCreate2() throws DatabaseException {
        when(courseOfferingService.findByCode((String) any())).thenReturn(new CourseOffering());

        RegistrationGroup registrationGroup = new RegistrationGroup();
        registrationGroup.setCourses(new ArrayList<>());
        registrationGroup.setId(123L);
        registrationGroup.setStudents(new ArrayList<>());
        when(registrationGroupRepository.save((RegistrationGroup) any())).thenReturn(registrationGroup);

        ArrayList<CourseOffering> courseOfferingList = new ArrayList<>();
        courseOfferingList.add(new CourseOffering());

        RegistrationGroup registrationGroup1 = new RegistrationGroup();
        registrationGroup1.setCourses(courseOfferingList);
        registrationGroup1.setId(123L);
        ArrayList<Student> studentList = new ArrayList<>();
        registrationGroup1.setStudents(studentList);
        assertSame(registrationGroup, registrationGroupServiceImpl.create(registrationGroup1));
        verify(courseOfferingService).findByCode((String) any());
        verify(registrationGroupRepository).save((RegistrationGroup) any());
        Collection<CourseOffering> courses = registrationGroup1.getCourses();
        assertEquals(courseOfferingList, courses);
        assertEquals(1, courses.size());
        assertEquals(studentList, registrationGroup1.getStudents());
    }

    /**
     * Method under test: {@link RegistrationGroupServiceImpl#create(RegistrationGroup)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testCreate3() throws DatabaseException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "edu.miu.cs544.backend.domain.CourseOffering.getCode()" because "c" is null
        //       at edu.miu.cs544.backend.service.RegistrationGroupServiceImpl.create(RegistrationGroupServiceImpl.java:58)
        //   See https://diff.blue/R013 to resolve this issue.

        when(courseOfferingService.findByCode((String) any())).thenReturn(new CourseOffering());

        RegistrationGroup registrationGroup = new RegistrationGroup();
        registrationGroup.setCourses(new ArrayList<>());
        registrationGroup.setId(123L);
        registrationGroup.setStudents(new ArrayList<>());
        when(registrationGroupRepository.save((RegistrationGroup) any())).thenReturn(registrationGroup);

        ArrayList<CourseOffering> courseOfferingList = new ArrayList<>();
        courseOfferingList.add(null);

        RegistrationGroup registrationGroup1 = new RegistrationGroup();
        registrationGroup1.setCourses(courseOfferingList);
        registrationGroup1.setId(123L);
        registrationGroup1.setStudents(new ArrayList<>());
        registrationGroupServiceImpl.create(registrationGroup1);
    }

    /**
     * Method under test: {@link RegistrationGroupServiceImpl#create(RegistrationGroup)}
     */
    @Test
    public void testCreate4() throws DatabaseException {
        when(courseOfferingService.findByCode((String) any())).thenReturn(new CourseOffering());

        RegistrationGroup registrationGroup = new RegistrationGroup();
        registrationGroup.setCourses(new ArrayList<>());
        registrationGroup.setId(123L);
        registrationGroup.setStudents(new ArrayList<>());
        when(registrationGroupRepository.save((RegistrationGroup) any())).thenReturn(registrationGroup);
        when(studentService.findByStudentId((Integer) any())).thenReturn(new Student());

        ArrayList<CourseOffering> courseOfferingList = new ArrayList<>();
        courseOfferingList.add(new CourseOffering());

        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(new Student());

        RegistrationGroup registrationGroup1 = new RegistrationGroup();
        registrationGroup1.setCourses(courseOfferingList);
        registrationGroup1.setId(123L);
        registrationGroup1.setStudents(studentList);
        assertSame(registrationGroup, registrationGroupServiceImpl.create(registrationGroup1));
        verify(courseOfferingService).findByCode((String) any());
        verify(registrationGroupRepository).save((RegistrationGroup) any());
        verify(studentService).findByStudentId((Integer) any());
        Collection<CourseOffering> courses = registrationGroup1.getCourses();
        assertEquals(courseOfferingList, courses);
        assertEquals(1, courses.size());
        Collection<Student> students = registrationGroup1.getStudents();
        assertEquals(studentList, students);
        assertEquals(1, students.size());
    }

    /**
     * Method under test: {@link RegistrationGroupServiceImpl#create(RegistrationGroup)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testCreate5() throws DatabaseException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "edu.miu.cs544.backend.domain.Student.getStudentId()" because "s" is null
        //       at edu.miu.cs544.backend.service.RegistrationGroupServiceImpl.create(RegistrationGroupServiceImpl.java:52)
        //   See https://diff.blue/R013 to resolve this issue.

        when(courseOfferingService.findByCode((String) any())).thenReturn(new CourseOffering());

        RegistrationGroup registrationGroup = new RegistrationGroup();
        registrationGroup.setCourses(new ArrayList<>());
        registrationGroup.setId(123L);
        registrationGroup.setStudents(new ArrayList<>());
        when(registrationGroupRepository.save((RegistrationGroup) any())).thenReturn(registrationGroup);
        when(studentService.findByStudentId((Integer) any())).thenReturn(new Student());

        ArrayList<CourseOffering> courseOfferingList = new ArrayList<>();
        courseOfferingList.add(new CourseOffering());

        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(null);

        RegistrationGroup registrationGroup1 = new RegistrationGroup();
        registrationGroup1.setCourses(courseOfferingList);
        registrationGroup1.setId(123L);
        registrationGroup1.setStudents(studentList);
        registrationGroupServiceImpl.create(registrationGroup1);
    }

    /**
     * Method under test: {@link RegistrationGroupServiceImpl#create(RegistrationGroup)}
     */
    @Test
    public void testCreate6() throws DatabaseException {
        when(courseOfferingService.findByCode((String) any())).thenReturn(new CourseOffering());

        RegistrationGroup registrationGroup = new RegistrationGroup();
        registrationGroup.setCourses(new ArrayList<>());
        registrationGroup.setId(123L);
        registrationGroup.setStudents(new ArrayList<>());
        when(registrationGroupRepository.save((RegistrationGroup) any())).thenReturn(registrationGroup);
        when(studentService.findByStudentId((Integer) any())).thenThrow(new DatabaseException("An error occurred"));

        ArrayList<CourseOffering> courseOfferingList = new ArrayList<>();
        courseOfferingList.add(new CourseOffering());

        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(new Student());

        RegistrationGroup registrationGroup1 = new RegistrationGroup();
        registrationGroup1.setCourses(courseOfferingList);
        registrationGroup1.setId(123L);
        registrationGroup1.setStudents(studentList);
        thrown.expect(DatabaseException.class);
        registrationGroupServiceImpl.create(registrationGroup1);
        verify(studentService).findByStudentId((Integer) any());
    }

    /**
     * Method under test: {@link RegistrationGroupServiceImpl#update(Long, RegistrationGroup)}
     */
    @Test
    public void testUpdate() {
        RegistrationGroup registrationGroup = new RegistrationGroup();
        registrationGroup.setCourses(new ArrayList<>());
        registrationGroup.setId(123L);
        registrationGroup.setStudents(new ArrayList<>());
        Optional<RegistrationGroup> ofResult = Optional.of(registrationGroup);

        RegistrationGroup registrationGroup1 = new RegistrationGroup();
        registrationGroup1.setCourses(new ArrayList<>());
        registrationGroup1.setId(123L);
        registrationGroup1.setStudents(new ArrayList<>());
        when(registrationGroupRepository.save((RegistrationGroup) any())).thenReturn(registrationGroup1);
        when(registrationGroupRepository.findById((Long) any())).thenReturn(ofResult);

        RegistrationGroup registrationGroup2 = new RegistrationGroup();
        registrationGroup2.setCourses(new ArrayList<>());
        registrationGroup2.setId(123L);
        registrationGroup2.setStudents(new ArrayList<>());
        assertTrue(registrationGroupServiceImpl.update(123L, registrationGroup2));
        verify(registrationGroupRepository).save((RegistrationGroup) any());
        verify(registrationGroupRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link RegistrationGroupServiceImpl#update(Long, RegistrationGroup)}
     */
    @Test
    public void testUpdate2() {
        RegistrationGroup registrationGroup = new RegistrationGroup();
        registrationGroup.setCourses(new ArrayList<>());
        registrationGroup.setId(123L);
        registrationGroup.setStudents(new ArrayList<>());
        when(registrationGroupRepository.save((RegistrationGroup) any())).thenReturn(registrationGroup);
        when(registrationGroupRepository.findById((Long) any())).thenReturn(Optional.empty());

        RegistrationGroup registrationGroup1 = new RegistrationGroup();
        registrationGroup1.setCourses(new ArrayList<>());
        registrationGroup1.setId(123L);
        registrationGroup1.setStudents(new ArrayList<>());
        assertFalse(registrationGroupServiceImpl.update(123L, registrationGroup1));
        verify(registrationGroupRepository).findById((Long) any());
    }
}

