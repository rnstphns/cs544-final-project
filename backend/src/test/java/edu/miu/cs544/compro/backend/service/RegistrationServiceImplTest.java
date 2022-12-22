package edu.miu.cs544.compro.backend.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.miu.cs544.compro.backend.domain.CourseOffering;
import edu.miu.cs544.compro.backend.domain.Registration;
import edu.miu.cs544.compro.backend.domain.Student;
import edu.miu.cs544.compro.backend.exceptions.DatabaseException;
import edu.miu.cs544.compro.backend.exceptions.ObjectNotFoundException;
import edu.miu.cs544.compro.backend.repositories.RegistrationRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.junit.Rule;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {RegistrationServiceImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class RegistrationServiceImplTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @MockBean
    private CourseOfferingService courseOfferingService;

    @MockBean
    private RegistrationRepository registrationRepository;

    @Autowired
    private RegistrationServiceImpl registrationServiceImpl;

    /**
     * Method under test: {@link RegistrationServiceImpl#getRegistrationById(Long)}
     */
    @Test
    public void testGetRegistrationById() throws ObjectNotFoundException {
        Registration registration = new Registration();
        registration.setCourseList(new ArrayList<>());
        registration.setId(123L);
        registration.setStudent(new Student());
        Optional<Registration> ofResult = Optional.of(registration);
        when(registrationRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(registration, registrationServiceImpl.getRegistrationById(123L));
        verify(registrationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link RegistrationServiceImpl#getRegistrationsByStudentId(Integer)}
     */
    @Test
    public void testGetRegistrationsByStudentId() {
        assertNull(registrationServiceImpl.getRegistrationsByStudentId(123));
    }

    /**
     * Method under test: {@link RegistrationServiceImpl#getRegistrations()}
     */
    @Test
    public void testGetRegistrations() {
        ArrayList<Registration> registrationList = new ArrayList<>();
        when(registrationRepository.findAll()).thenReturn(registrationList);
        Collection<Registration> actualRegistrations = registrationServiceImpl.getRegistrations();
        assertSame(registrationList, actualRegistrations);
        assertTrue(actualRegistrations.isEmpty());
        verify(registrationRepository).findAll();
    }

    /**
     * Method under test: {@link RegistrationServiceImpl#createRegistration(Registration)}
     */
    @Test
    public void testCreateRegistration() throws DatabaseException {
        Registration registration = new Registration();
        registration.setCourseList(new ArrayList<>());
        registration.setId(123L);
        registration.setStudent(new Student());
        when(registrationRepository.save((Registration) any())).thenReturn(registration);

        Registration registration1 = new Registration();
        registration1.setCourseList(new ArrayList<>());
        registration1.setId(123L);
        registration1.setStudent(new Student());
        assertSame(registration, registrationServiceImpl.createRegistration(registration1));
        verify(registrationRepository).save((Registration) any());
    }

    /**
     * Method under test: {@link RegistrationServiceImpl#createRegistration(Registration)}
     */
    @Test
    public void testCreateRegistration2() throws DatabaseException {
        doNothing().when(courseOfferingService).create((CourseOffering) any());

        Registration registration = new Registration();
        registration.setCourseList(new ArrayList<>());
        registration.setId(123L);
        registration.setStudent(new Student());
        when(registrationRepository.save((Registration) any())).thenReturn(registration);

        ArrayList<CourseOffering> courseOfferingList = new ArrayList<>();
        courseOfferingList.add(new CourseOffering());

        Registration registration1 = new Registration();
        registration1.setCourseList(courseOfferingList);
        registration1.setId(123L);
        registration1.setStudent(new Student());
        assertSame(registration, registrationServiceImpl.createRegistration(registration1));
        verify(courseOfferingService).create((CourseOffering) any());
        verify(registrationRepository).save((Registration) any());
    }

    /**
     * Method under test: {@link RegistrationServiceImpl#createRegistration(Registration)}
     */
    @Test
    public void testCreateRegistration3() throws DatabaseException {
        doThrow(new DatabaseException("An error occurred")).when(courseOfferingService).create((CourseOffering) any());

        Registration registration = new Registration();
        registration.setCourseList(new ArrayList<>());
        registration.setId(123L);
        registration.setStudent(new Student());
        when(registrationRepository.save((Registration) any())).thenReturn(registration);

        ArrayList<CourseOffering> courseOfferingList = new ArrayList<>();
        courseOfferingList.add(new CourseOffering());

        Registration registration1 = new Registration();
        registration1.setCourseList(courseOfferingList);
        registration1.setId(123L);
        registration1.setStudent(new Student());
        thrown.expect(DatabaseException.class);
        registrationServiceImpl.createRegistration(registration1);
        verify(courseOfferingService).create((CourseOffering) any());
    }

    /**
     * Method under test: {@link RegistrationServiceImpl#deleteRegistration(Long)}
     */
    @Test
    public void testDeleteRegistration() {
        doNothing().when(registrationRepository).deleteById((Long) any());
        registrationServiceImpl.deleteRegistration(123L);
        verify(registrationRepository).deleteById((Long) any());
        assertTrue(registrationServiceImpl.getRegistrations().isEmpty());
    }

    /**
     * Method under test: {@link RegistrationServiceImpl#updateRegistration(Long, Registration)}
     */
    @Test
    public void testUpdateRegistration() {
        Registration registration = new Registration();
        registration.setCourseList(new ArrayList<>());
        registration.setId(123L);
        registration.setStudent(new Student());
        Optional<Registration> ofResult = Optional.of(registration);

        Registration registration1 = new Registration();
        registration1.setCourseList(new ArrayList<>());
        registration1.setId(123L);
        registration1.setStudent(new Student());
        when(registrationRepository.save((Registration) any())).thenReturn(registration1);
        when(registrationRepository.findById((Long) any())).thenReturn(ofResult);

        Registration registration2 = new Registration();
        registration2.setCourseList(new ArrayList<>());
        registration2.setId(123L);
        registration2.setStudent(new Student());
        assertTrue(registrationServiceImpl.updateRegistration(123L, registration2));
        verify(registrationRepository).save((Registration) any());
        verify(registrationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link RegistrationServiceImpl#updateRegistration(Long, Registration)}
     */
    @Test
    public void testUpdateRegistration2() {
        Registration registration = new Registration();
        registration.setCourseList(new ArrayList<>());
        registration.setId(123L);
        registration.setStudent(new Student());
        when(registrationRepository.save((Registration) any())).thenReturn(registration);
        when(registrationRepository.findById((Long) any())).thenReturn(Optional.empty());

        Registration registration1 = new Registration();
        registration1.setCourseList(new ArrayList<>());
        registration1.setId(123L);
        registration1.setStudent(new Student());
        assertFalse(registrationServiceImpl.updateRegistration(123L, registration1));
        verify(registrationRepository).findById((Long) any());
    }
}

