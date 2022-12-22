package edu.miu.cs544.backend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.miu.cs544.backend.domain.AcademicBlock;
import edu.miu.cs544.backend.domain.Course;
import edu.miu.cs544.backend.domain.CourseOffering;
import edu.miu.cs544.backend.domain.Faculty;
import edu.miu.cs544.backend.exceptions.DatabaseException;
import edu.miu.cs544.backend.repositories.AcademicBlockRepository;
import edu.miu.cs544.backend.repositories.CourseOfferingRepository;
import edu.miu.cs544.backend.repositories.CourseRepository;
import edu.miu.cs544.backend.repositories.FacultyRepository;
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

@ContextConfiguration(classes = {CourseOfferingServiceImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class CourseOfferingServiceImplTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @MockBean
    private AcademicBlockRepository academicBlockRepository;

    @MockBean
    private CourseOfferingRepository courseOfferingRepository;

    @Autowired
    private CourseOfferingServiceImpl courseOfferingServiceImpl;

    @MockBean
    private CourseRepository courseRepository;

    @MockBean
    private FacultyRepository facultyRepository;

    @MockBean
    private StudentRepository studentRepository;

    /**
     * Method under test: {@link CourseOfferingServiceImpl#findAll()}
     */
    @Test
    public void testFindAll() {
        ArrayList<CourseOffering> courseOfferingList = new ArrayList<>();
        when(courseOfferingRepository.findAll()).thenReturn(courseOfferingList);
        List<CourseOffering> actualFindAllResult = courseOfferingServiceImpl.findAll();
        assertSame(courseOfferingList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(courseOfferingRepository).findAll();
    }

    /**
     * Method under test: {@link CourseOfferingServiceImpl#findById(Long)}
     */
    @Test
    public void testFindById() {
        CourseOffering courseOffering = new CourseOffering();
        when(courseOfferingRepository.findById((Long) any())).thenReturn(Optional.of(courseOffering));
        assertSame(courseOffering, courseOfferingServiceImpl.findById(123L));
        verify(courseOfferingRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CourseOfferingServiceImpl#findById(Long)}
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
        //       at edu.miu.cs544.backend.service.CourseOfferingServiceImpl.findById(CourseOfferingServiceImpl.java:42)
        //   See https://diff.blue/R013 to resolve this issue.

        when(courseOfferingRepository.findById((Long) any())).thenReturn(Optional.empty());
        courseOfferingServiceImpl.findById(123L);
    }

    /**
     * Method under test: {@link CourseOfferingServiceImpl#findByCode(String)}
     */
    @Test
    public void testFindByCode() throws DatabaseException {
        CourseOffering courseOffering = new CourseOffering();
        when(courseOfferingRepository.findByCode((String) any())).thenReturn(Optional.of(courseOffering));
        assertSame(courseOffering, courseOfferingServiceImpl.findByCode("Code"));
        verify(courseOfferingRepository).findByCode((String) any());
    }

    /**
     * Method under test: {@link CourseOfferingServiceImpl#findByCode(String)}
     */
    @Test
    public void testFindByCode2() throws DatabaseException {
        when(courseOfferingRepository.findByCode((String) any())).thenReturn(Optional.empty());
        thrown.expect(DatabaseException.class);
        courseOfferingServiceImpl.findByCode("Code");
        verify(courseOfferingRepository).findByCode((String) any());
    }

    /**
     * Method under test: {@link CourseOfferingServiceImpl#delete(Long)}
     */
    @Test
    public void testDelete() {
        doNothing().when(courseOfferingRepository).deleteById((Long) any());
        courseOfferingServiceImpl.delete(123L);
        verify(courseOfferingRepository).deleteById((Long) any());
        assertTrue(courseOfferingServiceImpl.findAll().isEmpty());
    }

    /**
     * Method under test: {@link CourseOfferingServiceImpl#create(CourseOffering)}
     */
    @Test
    public void testCreate() {
        when(academicBlockRepository.save((AcademicBlock) any())).thenReturn(new AcademicBlock());
        when(courseOfferingRepository.findByCode((String) any())).thenReturn(Optional.of(new CourseOffering()));
        courseOfferingServiceImpl.create(new CourseOffering());
        verify(courseOfferingRepository).findByCode((String) any());
        assertTrue(courseOfferingServiceImpl.findAll().isEmpty());
    }

    /**
     * Method under test: {@link CourseOfferingServiceImpl#create(CourseOffering)}
     */
    @Test
    public void testCreate2() {
        when(academicBlockRepository.save((AcademicBlock) any())).thenReturn(new AcademicBlock());
        when(courseOfferingRepository.findByCode((String) any())).thenReturn(Optional.empty());
        when(courseRepository.save((Course) any())).thenReturn(new Course());
        courseOfferingServiceImpl.create(new CourseOffering());
        verify(academicBlockRepository).save((AcademicBlock) any());
        verify(courseOfferingRepository).findByCode((String) any());
        verify(courseRepository).save((Course) any());
        assertTrue(courseOfferingServiceImpl.findAll().isEmpty());
    }

    /**
     * Method under test: {@link CourseOfferingServiceImpl#create(CourseOffering)}
     */
    @Test
    public void testCreate3() {
        when(academicBlockRepository.save((AcademicBlock) any())).thenReturn(new AcademicBlock());
        when(courseOfferingRepository.findByCode((String) any())).thenReturn(Optional.of(new CourseOffering()));
        when(courseRepository.save((Course) any())).thenReturn(new Course());
        courseOfferingServiceImpl.create(null);
        assertTrue(courseOfferingServiceImpl.findAll().isEmpty());
    }

    /**
     * Method under test: {@link CourseOfferingServiceImpl#create(CourseOffering)}
     */
    @Test
    public void testCreate4() {
        when(academicBlockRepository.save((AcademicBlock) any())).thenReturn(new AcademicBlock());
        when(courseOfferingRepository.save((CourseOffering) any())).thenReturn(new CourseOffering());
        when(courseOfferingRepository.findByCode((String) any())).thenReturn(Optional.empty());
        when(courseRepository.save((Course) any())).thenReturn(new Course());

        CourseOffering courseOffering = new CourseOffering();
        courseOffering.setFaculty(new ArrayList<>());
        courseOfferingServiceImpl.create(courseOffering);
        verify(academicBlockRepository).save((AcademicBlock) any());
        verify(courseOfferingRepository).save((CourseOffering) any());
        verify(courseOfferingRepository).findByCode((String) any());
        verify(courseRepository).save((Course) any());
        assertTrue(courseOffering.getFaculty().isEmpty());
        assertTrue(courseOfferingServiceImpl.findAll().isEmpty());
    }

    /**
     * Method under test: {@link CourseOfferingServiceImpl#create(CourseOffering)}
     */
    @Test
    public void testCreate5() {
        when(academicBlockRepository.save((AcademicBlock) any())).thenReturn(new AcademicBlock());
        when(courseOfferingRepository.save((CourseOffering) any())).thenReturn(new CourseOffering());
        when(courseOfferingRepository.findByCode((String) any())).thenReturn(Optional.empty());
        when(courseRepository.save((Course) any())).thenReturn(new Course());
        when(facultyRepository.save((Faculty) any())).thenReturn(new Faculty("Dr"));

        ArrayList<Faculty> facultyList = new ArrayList<>();
        facultyList.add(new Faculty("Dr"));

        CourseOffering courseOffering = new CourseOffering();
        courseOffering.setFaculty(facultyList);
        courseOfferingServiceImpl.create(courseOffering);
        verify(academicBlockRepository).save((AcademicBlock) any());
        verify(courseOfferingRepository).save((CourseOffering) any());
        verify(courseOfferingRepository).findByCode((String) any());
        verify(courseRepository).save((Course) any());
        verify(facultyRepository).save((Faculty) any());
        assertEquals(1, courseOffering.getFaculty().size());
        assertTrue(courseOfferingServiceImpl.findAll().isEmpty());
    }

    /**
     * Method under test: {@link CourseOfferingServiceImpl#update(Long, CourseOffering)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testUpdate() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Iterable.iterator()" because "iterable" is null
        //       at edu.miu.cs544.backend.service.CourseOfferingServiceImpl.update(CourseOfferingServiceImpl.java:92)
        //   See https://diff.blue/R013 to resolve this issue.

        when(academicBlockRepository.save((AcademicBlock) any())).thenReturn(new AcademicBlock());
        when(courseOfferingRepository.findById((Long) any())).thenReturn(Optional.of(new CourseOffering()));
        when(courseRepository.save((Course) any())).thenReturn(new Course());
        courseOfferingServiceImpl.update(123L, new CourseOffering());
    }

    /**
     * Method under test: {@link CourseOfferingServiceImpl#update(Long, CourseOffering)}
     */
    @Test
    public void testUpdate2() {
        when(academicBlockRepository.save((AcademicBlock) any())).thenReturn(new AcademicBlock());
        when(courseOfferingRepository.findById((Long) any())).thenReturn(Optional.empty());
        when(courseRepository.save((Course) any())).thenReturn(new Course());
        assertFalse(courseOfferingServiceImpl.update(123L, new CourseOffering()));
        verify(courseOfferingRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CourseOfferingServiceImpl#update(Long, CourseOffering)}
     */
    @Test
    public void testUpdate3() {
        when(academicBlockRepository.save((AcademicBlock) any())).thenReturn(new AcademicBlock());
        when(courseOfferingRepository.save((CourseOffering) any())).thenReturn(new CourseOffering());
        when(courseOfferingRepository.findById((Long) any())).thenReturn(Optional.of(new CourseOffering()));
        when(courseRepository.save((Course) any())).thenReturn(new Course());

        CourseOffering courseOffering = new CourseOffering();
        courseOffering.setFaculty(new ArrayList<>());
        assertTrue(courseOfferingServiceImpl.update(123L, courseOffering));
        verify(academicBlockRepository).save((AcademicBlock) any());
        verify(courseOfferingRepository).save((CourseOffering) any());
        verify(courseOfferingRepository).findById((Long) any());
        verify(courseRepository).save((Course) any());
    }

    /**
     * Method under test: {@link CourseOfferingServiceImpl#update(Long, CourseOffering)}
     */
    @Test
    public void testUpdate4() {
        when(academicBlockRepository.save((AcademicBlock) any())).thenReturn(new AcademicBlock());
        when(courseOfferingRepository.save((CourseOffering) any())).thenReturn(new CourseOffering());
        when(courseOfferingRepository.findById((Long) any())).thenReturn(Optional.of(new CourseOffering()));
        when(courseRepository.save((Course) any())).thenReturn(new Course());
        when(facultyRepository.save((Faculty) any())).thenReturn(new Faculty("Dr"));

        ArrayList<Faculty> facultyList = new ArrayList<>();
        facultyList.add(new Faculty("Dr"));

        CourseOffering courseOffering = new CourseOffering();
        courseOffering.setFaculty(facultyList);
        assertTrue(courseOfferingServiceImpl.update(123L, courseOffering));
        verify(academicBlockRepository).save((AcademicBlock) any());
        verify(courseOfferingRepository).save((CourseOffering) any());
        verify(courseOfferingRepository).findById((Long) any());
        verify(courseRepository).save((Course) any());
        verify(facultyRepository).save((Faculty) any());
    }
}

