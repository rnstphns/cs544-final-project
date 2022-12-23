package edu.miu.cs544.compro.backend.service;

import static org.junit.Assert.assertEquals;
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
import edu.miu.cs544.compro.backend.domain.RegistrationEvent;
import edu.miu.cs544.compro.backend.domain.RegistrationGroup;
import edu.miu.cs544.compro.backend.domain.RegistrationRequest;
import edu.miu.cs544.compro.backend.domain.Student;
import edu.miu.cs544.compro.backend.exceptions.DatabaseException;
import edu.miu.cs544.compro.backend.exceptions.ObjectNotFoundException;
import edu.miu.cs544.compro.backend.kafka.EmailKafkaSender;
import edu.miu.cs544.compro.backend.repositories.RegistrationEventRepository;

import java.time.LocalDate;
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
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {RegistrationEventServiceImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class RegistrationEventServiceImplTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @MockBean
    private CourseOfferingService courseOfferingService;

    @MockBean
    private EmailKafkaSender emailKafkaSender;

    @MockBean
    private RegistrationEventRepository registrationEventRepository;

    @Autowired
    private RegistrationEventServiceImpl registrationEventServiceImpl;

    @MockBean
    private RegistrationGroupService registrationGroupService;

    @MockBean
    private RegistrationRequestService registrationRequestService;

    @MockBean
    private RegistrationService registrationService;

    /**
     * Method under test: {@link RegistrationEventServiceImpl#getRegistrationEventById(long)}
     */
    @Test
    public void testGetRegistrationEventById() throws ObjectNotFoundException {
        RegistrationEvent registrationEvent = new RegistrationEvent();
        registrationEvent.setEndDate(LocalDate.ofEpochDay(1L));
        registrationEvent.setId(123L);
        registrationEvent.setRegistrationGroups(new ArrayList<>());
        registrationEvent.setStartDate(LocalDate.ofEpochDay(1L));
        Optional<RegistrationEvent> ofResult = Optional.of(registrationEvent);
        when(registrationEventRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(registrationEvent, registrationEventServiceImpl.getRegistrationEventById(123L));
        verify(registrationEventRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link RegistrationEventServiceImpl#getRegistrationEventById(long)}
     */
    @Test
    public void testGetRegistrationEventById2() throws ObjectNotFoundException {
        when(registrationEventRepository.findById((Long) any())).thenReturn(Optional.empty());
        thrown.expect(ObjectNotFoundException.class);
        registrationEventServiceImpl.getRegistrationEventById(123L);
        verify(registrationEventRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link RegistrationEventServiceImpl#getRegistrationEventById(long)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testGetRegistrationEventById3() throws ObjectNotFoundException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IndexOutOfBoundsException
        //       at edu.miu.cs544.compro.backend.service.RegistrationEventServiceImpl.getRegistrationEventById(RegistrationEventServiceImpl.java:44)
        //   See https://diff.blue/R013 to resolve this issue.

        when(registrationEventRepository.findById((Long) any())).thenThrow(new IndexOutOfBoundsException());
        registrationEventServiceImpl.getRegistrationEventById(123L);
    }

    /**
     * Method under test: {@link RegistrationEventServiceImpl#getRegistrationEvents()}
     */
    @Test
    public void testGetRegistrationEvents() {
        ArrayList<RegistrationEvent> registrationEventList = new ArrayList<>();
        when(registrationEventRepository.findAll()).thenReturn(registrationEventList);
        Collection<RegistrationEvent> actualRegistrationEvents = registrationEventServiceImpl.getRegistrationEvents();
        assertSame(registrationEventList, actualRegistrationEvents);
        assertTrue(actualRegistrationEvents.isEmpty());
        verify(registrationEventRepository).findAll();
    }

    /**
     * Method under test: {@link RegistrationEventServiceImpl#getRegistrationEvents()}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testGetRegistrationEvents2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IndexOutOfBoundsException
        //       at edu.miu.cs544.compro.backend.service.RegistrationEventServiceImpl.getRegistrationEvents(RegistrationEventServiceImpl.java:52)
        //   See https://diff.blue/R013 to resolve this issue.

        when(registrationEventRepository.findAll()).thenThrow(new IndexOutOfBoundsException());
        registrationEventServiceImpl.getRegistrationEvents();
    }

    /**
     * Method under test: {@link RegistrationEventServiceImpl#createRegistrationEvent(RegistrationEvent)}
     */
    @Test
    public void testCreateRegistrationEvent() throws DatabaseException {
        RegistrationEvent registrationEvent = new RegistrationEvent();
        registrationEvent.setEndDate(LocalDate.ofEpochDay(1L));
        registrationEvent.setId(123L);
        registrationEvent.setRegistrationGroups(new ArrayList<>());
        registrationEvent.setStartDate(LocalDate.ofEpochDay(1L));
        when(registrationEventRepository.save((RegistrationEvent) any())).thenReturn(registrationEvent);

        RegistrationEvent registrationEvent1 = new RegistrationEvent();
        registrationEvent1.setEndDate(LocalDate.ofEpochDay(1L));
        registrationEvent1.setId(123L);
        registrationEvent1.setRegistrationGroups(new ArrayList<>());
        registrationEvent1.setStartDate(LocalDate.ofEpochDay(1L));
        assertSame(registrationEvent, registrationEventServiceImpl.createRegistrationEvent(registrationEvent1));
        verify(registrationEventRepository).save((RegistrationEvent) any());
    }

    /**
     * Method under test: {@link RegistrationEventServiceImpl#createRegistrationEvent(RegistrationEvent)}
     */
    @Test
    public void testCreateRegistrationEvent2() throws DatabaseException {
        RegistrationEvent registrationEvent = new RegistrationEvent();
        registrationEvent.setEndDate(LocalDate.ofEpochDay(1L));
        registrationEvent.setId(123L);
        registrationEvent.setRegistrationGroups(new ArrayList<>());
        registrationEvent.setStartDate(LocalDate.ofEpochDay(1L));
        when(registrationEventRepository.save((RegistrationEvent) any())).thenReturn(registrationEvent);

        RegistrationGroup registrationGroup = new RegistrationGroup();
        registrationGroup.setCourses(new ArrayList<>());
        registrationGroup.setId(123L);
        registrationGroup.setStudents(new ArrayList<>());
        when(registrationGroupService.create((RegistrationGroup) any())).thenReturn(registrationGroup);

        RegistrationGroup registrationGroup1 = new RegistrationGroup();
        registrationGroup1.setCourses(new ArrayList<>());
        registrationGroup1.setId(123L);
        registrationGroup1.setStudents(new ArrayList<>());

        ArrayList<RegistrationGroup> registrationGroupList = new ArrayList<>();
        registrationGroupList.add(registrationGroup1);

        RegistrationEvent registrationEvent1 = new RegistrationEvent();
        registrationEvent1.setEndDate(LocalDate.ofEpochDay(1L));
        registrationEvent1.setId(123L);
        registrationEvent1.setRegistrationGroups(registrationGroupList);
        registrationEvent1.setStartDate(LocalDate.ofEpochDay(1L));
        assertSame(registrationEvent, registrationEventServiceImpl.createRegistrationEvent(registrationEvent1));
        verify(registrationEventRepository).save((RegistrationEvent) any());
        verify(registrationGroupService).create((RegistrationGroup) any());
    }

    /**
     * Method under test: {@link RegistrationEventServiceImpl#createRegistrationEvent(RegistrationEvent)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testCreateRegistrationEvent3() throws DatabaseException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IndexOutOfBoundsException
        //       at edu.miu.cs544.compro.backend.service.RegistrationEventServiceImpl.createRegistrationEvent(RegistrationEventServiceImpl.java:59)
        //   See https://diff.blue/R013 to resolve this issue.

        RegistrationEvent registrationEvent = new RegistrationEvent();
        registrationEvent.setEndDate(LocalDate.ofEpochDay(1L));
        registrationEvent.setId(123L);
        registrationEvent.setRegistrationGroups(new ArrayList<>());
        registrationEvent.setStartDate(LocalDate.ofEpochDay(1L));
        when(registrationEventRepository.save((RegistrationEvent) any())).thenReturn(registrationEvent);
        when(registrationGroupService.create((RegistrationGroup) any())).thenThrow(new IndexOutOfBoundsException());

        RegistrationGroup registrationGroup = new RegistrationGroup();
        registrationGroup.setCourses(new ArrayList<>());
        registrationGroup.setId(123L);
        registrationGroup.setStudents(new ArrayList<>());

        ArrayList<RegistrationGroup> registrationGroupList = new ArrayList<>();
        registrationGroupList.add(registrationGroup);

        RegistrationEvent registrationEvent1 = new RegistrationEvent();
        registrationEvent1.setEndDate(LocalDate.ofEpochDay(1L));
        registrationEvent1.setId(123L);
        registrationEvent1.setRegistrationGroups(registrationGroupList);
        registrationEvent1.setStartDate(LocalDate.ofEpochDay(1L));
        registrationEventServiceImpl.createRegistrationEvent(registrationEvent1);
    }

    /**
     * Method under test: {@link RegistrationEventServiceImpl#deleteRegistrationEvent(Long)}
     */
    @Test
    public void testDeleteRegistrationEvent() {
        doNothing().when(registrationEventRepository).deleteById((Long) any());
        registrationEventServiceImpl.deleteRegistrationEvent(123L);
        verify(registrationEventRepository).deleteById((Long) any());
        assertTrue(registrationEventServiceImpl.getRegistrationEvents().isEmpty());
    }

    /**
     * Method under test: {@link RegistrationEventServiceImpl#deleteRegistrationEvent(Long)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testDeleteRegistrationEvent2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IndexOutOfBoundsException
        //       at edu.miu.cs544.compro.backend.service.RegistrationEventServiceImpl.deleteRegistrationEvent(RegistrationEventServiceImpl.java:66)
        //   See https://diff.blue/R013 to resolve this issue.

        doThrow(new IndexOutOfBoundsException()).when(registrationEventRepository).deleteById((Long) any());
        registrationEventServiceImpl.deleteRegistrationEvent(123L);
    }

    /**
     * Method under test: {@link RegistrationEventServiceImpl#updateWindow(Long, LocalDate, LocalDate)}
     */
    @Test
    public void testUpdateWindow() {
        RegistrationEvent registrationEvent = new RegistrationEvent();
        registrationEvent.setEndDate(LocalDate.ofEpochDay(1L));
        registrationEvent.setId(123L);
        registrationEvent.setRegistrationGroups(new ArrayList<>());
        registrationEvent.setStartDate(LocalDate.ofEpochDay(1L));
        Optional<RegistrationEvent> ofResult = Optional.of(registrationEvent);

        RegistrationEvent registrationEvent1 = new RegistrationEvent();
        registrationEvent1.setEndDate(LocalDate.ofEpochDay(1L));
        registrationEvent1.setId(123L);
        registrationEvent1.setRegistrationGroups(new ArrayList<>());
        registrationEvent1.setStartDate(LocalDate.ofEpochDay(1L));
        when(registrationEventRepository.save((RegistrationEvent) any())).thenReturn(registrationEvent1);
        when(registrationEventRepository.findById((Long) any())).thenReturn(ofResult);
        RegistrationEvent actualUpdateWindowResult = registrationEventServiceImpl.updateWindow(123L,
                LocalDate.ofEpochDay(1L), LocalDate.ofEpochDay(1L));
        assertSame(registrationEvent, actualUpdateWindowResult);
        assertEquals("1970-01-02", actualUpdateWindowResult.getEndDate().toString());
        assertEquals("1970-01-02", actualUpdateWindowResult.getStartDate().toString());
        verify(registrationEventRepository).save((RegistrationEvent) any());
        verify(registrationEventRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link RegistrationEventServiceImpl#updateWindow(Long, LocalDate, LocalDate)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testUpdateWindow2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IndexOutOfBoundsException
        //       at edu.miu.cs544.compro.backend.service.RegistrationEventServiceImpl.updateWindow(RegistrationEventServiceImpl.java:76)
        //   See https://diff.blue/R013 to resolve this issue.

        RegistrationEvent registrationEvent = new RegistrationEvent();
        registrationEvent.setEndDate(LocalDate.ofEpochDay(1L));
        registrationEvent.setId(123L);
        registrationEvent.setRegistrationGroups(new ArrayList<>());
        registrationEvent.setStartDate(LocalDate.ofEpochDay(1L));
        Optional<RegistrationEvent> ofResult = Optional.of(registrationEvent);
        when(registrationEventRepository.save((RegistrationEvent) any())).thenThrow(new IndexOutOfBoundsException());
        when(registrationEventRepository.findById((Long) any())).thenReturn(ofResult);
        registrationEventServiceImpl.updateWindow(123L, LocalDate.ofEpochDay(1L), LocalDate.ofEpochDay(1L));
    }

    /**
     * Method under test: {@link RegistrationEventServiceImpl#updateWindow(Long, LocalDate, LocalDate)}
     */
    @Test
    public void testUpdateWindow3() {
        RegistrationEvent registrationEvent = new RegistrationEvent();
        registrationEvent.setEndDate(LocalDate.ofEpochDay(1L));
        registrationEvent.setId(123L);
        registrationEvent.setRegistrationGroups(new ArrayList<>());
        registrationEvent.setStartDate(LocalDate.ofEpochDay(1L));
        when(registrationEventRepository.save((RegistrationEvent) any())).thenReturn(registrationEvent);
        when(registrationEventRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertNull(registrationEventServiceImpl.updateWindow(123L, LocalDate.ofEpochDay(1L), LocalDate.ofEpochDay(1L)));
        verify(registrationEventRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link RegistrationEventServiceImpl#latest(Integer)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testLatest() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Iterable.iterator()" because "iterable" is null
        //       at edu.miu.cs544.compro.backend.service.RegistrationEventServiceImpl.latest(RegistrationEventServiceImpl.java:93)
        //   See https://diff.blue/R013 to resolve this issue.

        when(registrationEventRepository.findAll((Sort) any())).thenReturn(new ArrayList<>());
        registrationEventServiceImpl.latest(123);
    }

    /**
     * Method under test: {@link RegistrationEventServiceImpl#latest(Integer)}
     */
    @Test
    public void testLatest2() {
        RegistrationEvent registrationEvent = new RegistrationEvent();
        registrationEvent.setEndDate(LocalDate.ofEpochDay(1L));
        registrationEvent.setId(123L);
        ArrayList<RegistrationGroup> registrationGroupList = new ArrayList<>();
        registrationEvent.setRegistrationGroups(registrationGroupList);
        registrationEvent.setStartDate(LocalDate.ofEpochDay(1L));

        ArrayList<RegistrationEvent> registrationEventList = new ArrayList<>();
        registrationEventList.add(registrationEvent);
        when(registrationEventRepository.findAll((Sort) any())).thenReturn(registrationEventList);
        RegistrationEvent actualLatestResult = registrationEventServiceImpl.latest(123);
        assertSame(registrationEvent, actualLatestResult);
        assertEquals(registrationGroupList, actualLatestResult.getRegistrationGroups());
        verify(registrationEventRepository).findAll((Sort) any());
    }

    /**
     * Method under test: {@link RegistrationEventServiceImpl#latest(Integer)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testLatest3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IndexOutOfBoundsException
        //       at edu.miu.cs544.compro.backend.service.RegistrationEventServiceImpl.latest(RegistrationEventServiceImpl.java:84)
        //   See https://diff.blue/R013 to resolve this issue.

        when(registrationEventRepository.findAll((Sort) any())).thenThrow(new IndexOutOfBoundsException());
        registrationEventServiceImpl.latest(123);
    }

    /**
     * Method under test: {@link RegistrationEventServiceImpl#latest(Integer)}
     */
    @Test
    public void testLatest4() {
        RegistrationGroup registrationGroup = new RegistrationGroup();
        ArrayList<CourseOffering> courseOfferingList = new ArrayList<>();
        registrationGroup.setCourses(courseOfferingList);
        registrationGroup.setId(123L);
        registrationGroup.setStudents(new ArrayList<>());

        ArrayList<RegistrationGroup> registrationGroupList = new ArrayList<>();
        registrationGroupList.add(registrationGroup);

        RegistrationEvent registrationEvent = new RegistrationEvent();
        registrationEvent.setEndDate(LocalDate.ofEpochDay(1L));
        registrationEvent.setId(123L);
        registrationEvent.setRegistrationGroups(registrationGroupList);
        registrationEvent.setStartDate(LocalDate.ofEpochDay(1L));

        ArrayList<RegistrationEvent> registrationEventList = new ArrayList<>();
        registrationEventList.add(registrationEvent);
        when(registrationEventRepository.findAll((Sort) any())).thenReturn(registrationEventList);
        RegistrationEvent actualLatestResult = registrationEventServiceImpl.latest(123);
        assertSame(registrationEvent, actualLatestResult);
        assertEquals(courseOfferingList, actualLatestResult.getRegistrationGroups());
        verify(registrationEventRepository).findAll((Sort) any());
    }

    /**
     * Method under test: {@link RegistrationEventServiceImpl#latest(Integer)}
     */
    @Test
    public void testLatest5() {
        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(new Student());

        RegistrationGroup registrationGroup = new RegistrationGroup();
        ArrayList<CourseOffering> courseOfferingList = new ArrayList<>();
        registrationGroup.setCourses(courseOfferingList);
        registrationGroup.setId(123L);
        registrationGroup.setStudents(studentList);

        ArrayList<RegistrationGroup> registrationGroupList = new ArrayList<>();
        registrationGroupList.add(registrationGroup);

        RegistrationEvent registrationEvent = new RegistrationEvent();
        registrationEvent.setEndDate(LocalDate.ofEpochDay(1L));
        registrationEvent.setId(123L);
        registrationEvent.setRegistrationGroups(registrationGroupList);
        registrationEvent.setStartDate(LocalDate.ofEpochDay(1L));

        ArrayList<RegistrationEvent> registrationEventList = new ArrayList<>();
        registrationEventList.add(registrationEvent);
        when(registrationEventRepository.findAll((Sort) any())).thenReturn(registrationEventList);
        RegistrationEvent actualLatestResult = registrationEventServiceImpl.latest(123);
        assertSame(registrationEvent, actualLatestResult);
        assertEquals(courseOfferingList, actualLatestResult.getRegistrationGroups());
        verify(registrationEventRepository).findAll((Sort) any());
    }

    /**
     * Method under test: {@link RegistrationEventServiceImpl#latest(Integer)}
     */
    @Test
    public void testLatest6() {
        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(new Student(123));

        RegistrationGroup registrationGroup = new RegistrationGroup();
        registrationGroup.setCourses(new ArrayList<>());
        registrationGroup.setId(123L);
        registrationGroup.setStudents(studentList);

        ArrayList<RegistrationGroup> registrationGroupList = new ArrayList<>();
        registrationGroupList.add(registrationGroup);

        RegistrationEvent registrationEvent = new RegistrationEvent();
        registrationEvent.setEndDate(LocalDate.ofEpochDay(1L));
        registrationEvent.setId(123L);
        registrationEvent.setRegistrationGroups(registrationGroupList);
        registrationEvent.setStartDate(LocalDate.ofEpochDay(1L));

        ArrayList<RegistrationEvent> registrationEventList = new ArrayList<>();
        registrationEventList.add(registrationEvent);
        when(registrationEventRepository.findAll((Sort) any())).thenReturn(registrationEventList);
        RegistrationEvent actualLatestResult = registrationEventServiceImpl.latest(123);
        assertSame(registrationEvent, actualLatestResult);
        Collection<RegistrationGroup> registrationGroups = actualLatestResult.getRegistrationGroups();
        assertEquals(registrationGroupList, registrationGroups);
        assertEquals(1, registrationGroups.size());
        RegistrationGroup getResult = ((List<RegistrationGroup>) registrationGroups).get(0);
        Collection<CourseOffering> expectedStudents = getResult.getCourses();
        assertEquals(expectedStudents, getResult.getStudents());
        verify(registrationEventRepository).findAll((Sort) any());
    }

    /**
     * Method under test: {@link RegistrationEventServiceImpl#latest(Integer)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testLatest7() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "edu.miu.cs544.compro.backend.domain.Student.getStudentId()" because "s" is null
        //       at edu.miu.cs544.compro.backend.service.RegistrationEventServiceImpl.latest(RegistrationEventServiceImpl.java:96)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(null);

        RegistrationGroup registrationGroup = new RegistrationGroup();
        registrationGroup.setCourses(new ArrayList<>());
        registrationGroup.setId(123L);
        registrationGroup.setStudents(studentList);

        ArrayList<RegistrationGroup> registrationGroupList = new ArrayList<>();
        registrationGroupList.add(registrationGroup);

        RegistrationEvent registrationEvent = new RegistrationEvent();
        registrationEvent.setEndDate(LocalDate.ofEpochDay(1L));
        registrationEvent.setId(123L);
        registrationEvent.setRegistrationGroups(registrationGroupList);
        registrationEvent.setStartDate(LocalDate.ofEpochDay(1L));

        ArrayList<RegistrationEvent> registrationEventList = new ArrayList<>();
        registrationEventList.add(registrationEvent);
        when(registrationEventRepository.findAll((Sort) any())).thenReturn(registrationEventList);
        registrationEventServiceImpl.latest(123);
    }

    /**
     * Method under test: {@link RegistrationEventServiceImpl#processEvent(Long)}
     */
    @Test
    public void testProcessEvent() {
        RegistrationEvent registrationEvent = new RegistrationEvent();
        registrationEvent.setEndDate(LocalDate.ofEpochDay(1L));
        registrationEvent.setId(123L);
        registrationEvent.setRegistrationGroups(new ArrayList<>());
        registrationEvent.setStartDate(LocalDate.ofEpochDay(1L));
        Optional<RegistrationEvent> ofResult = Optional.of(registrationEvent);
        when(registrationEventRepository.findById((Long) any())).thenReturn(ofResult);
        assertTrue(registrationEventServiceImpl.processEvent(123L));
        verify(registrationEventRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link RegistrationEventServiceImpl#processEvent(Long)}
     */
    @Test
    public void testProcessEvent2() {
        RegistrationGroup registrationGroup = new RegistrationGroup();
        registrationGroup.setCourses(new ArrayList<>());
        registrationGroup.setId(123L);
        registrationGroup.setStudents(new ArrayList<>());

        ArrayList<RegistrationGroup> registrationGroupList = new ArrayList<>();
        registrationGroupList.add(registrationGroup);

        RegistrationEvent registrationEvent = new RegistrationEvent();
        registrationEvent.setEndDate(LocalDate.ofEpochDay(1L));
        registrationEvent.setId(123L);
        registrationEvent.setRegistrationGroups(registrationGroupList);
        registrationEvent.setStartDate(LocalDate.ofEpochDay(1L));
        Optional<RegistrationEvent> ofResult = Optional.of(registrationEvent);
        when(registrationEventRepository.findById((Long) any())).thenReturn(ofResult);
        assertTrue(registrationEventServiceImpl.processEvent(123L));
        verify(registrationEventRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link RegistrationEventServiceImpl#processEvent(Long)}
     */
    @Test
    public void testProcessEvent3() {
        when(registrationEventRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertFalse(registrationEventServiceImpl.processEvent(123L));
        verify(registrationEventRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link RegistrationEventServiceImpl#processEvent(Long)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testProcessEvent4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IndexOutOfBoundsException
        //       at edu.miu.cs544.compro.backend.service.RegistrationEventServiceImpl.getRegistrationEventById(RegistrationEventServiceImpl.java:44)
        //       at edu.miu.cs544.compro.backend.service.RegistrationEventServiceImpl.processEvent(RegistrationEventServiceImpl.java:110)
        //   See https://diff.blue/R013 to resolve this issue.

        when(registrationEventRepository.findById((Long) any())).thenThrow(new IndexOutOfBoundsException());
        registrationEventServiceImpl.processEvent(123L);
    }

    /**
     * Method under test: {@link RegistrationEventServiceImpl#processEvent(Long)}
     */
    @Test
    public void testProcessEvent5() {
        ArrayList<CourseOffering> courseOfferingList = new ArrayList<>();
        courseOfferingList.add(new CourseOffering());

        RegistrationGroup registrationGroup = new RegistrationGroup();
        registrationGroup.setCourses(courseOfferingList);
        registrationGroup.setId(123L);
        registrationGroup.setStudents(new ArrayList<>());

        ArrayList<RegistrationGroup> registrationGroupList = new ArrayList<>();
        registrationGroupList.add(registrationGroup);

        RegistrationEvent registrationEvent = new RegistrationEvent();
        registrationEvent.setEndDate(LocalDate.ofEpochDay(1L));
        registrationEvent.setId(123L);
        registrationEvent.setRegistrationGroups(registrationGroupList);
        registrationEvent.setStartDate(LocalDate.ofEpochDay(1L));
        Optional<RegistrationEvent> ofResult = Optional.of(registrationEvent);
        when(registrationEventRepository.findById((Long) any())).thenReturn(ofResult);
        when(registrationRequestService.getRegistrationRequests()).thenReturn(new ArrayList<>());
        assertTrue(registrationEventServiceImpl.processEvent(123L));
        verify(registrationEventRepository).findById((Long) any());
        verify(registrationRequestService).getRegistrationRequests();
    }

    /**
     * Method under test: {@link RegistrationEventServiceImpl#processEvent(Long)}
     */
    @Test
    public void testProcessEvent6() {
        doNothing().when(emailKafkaSender).send((Student) any());

        ArrayList<CourseOffering> courseOfferingList = new ArrayList<>();
        courseOfferingList.add(new CourseOffering());

        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(new Student());

        RegistrationGroup registrationGroup = new RegistrationGroup();
        registrationGroup.setCourses(courseOfferingList);
        registrationGroup.setId(123L);
        registrationGroup.setStudents(studentList);

        ArrayList<RegistrationGroup> registrationGroupList = new ArrayList<>();
        registrationGroupList.add(registrationGroup);

        RegistrationEvent registrationEvent = new RegistrationEvent();
        registrationEvent.setEndDate(LocalDate.ofEpochDay(1L));
        registrationEvent.setId(123L);
        registrationEvent.setRegistrationGroups(registrationGroupList);
        registrationEvent.setStartDate(LocalDate.ofEpochDay(1L));
        Optional<RegistrationEvent> ofResult = Optional.of(registrationEvent);
        when(registrationEventRepository.findById((Long) any())).thenReturn(ofResult);
        when(registrationRequestService.getRegistrationRequests()).thenReturn(new ArrayList<>());
        assertTrue(registrationEventServiceImpl.processEvent(123L));
        verify(emailKafkaSender).send((Student) any());
        verify(registrationEventRepository).findById((Long) any());
        verify(registrationRequestService).getRegistrationRequests();
    }

    /**
     * Method under test: {@link RegistrationEventServiceImpl#processEvent(Long)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testProcessEvent7() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Integer.intValue()" because the return value of "edu.miu.cs544.compro.backend.domain.CourseOffering.getAvailableSeats()" is null
        //       at edu.miu.cs544.compro.backend.service.RegistrationEventServiceImpl.processEvent(RegistrationEventServiceImpl.java:118)
        //   See https://diff.blue/R013 to resolve this issue.

        doNothing().when(emailKafkaSender).send((Student) any());

        ArrayList<CourseOffering> courseOfferingList = new ArrayList<>();
        courseOfferingList.add(new CourseOffering());

        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(new Student());

        RegistrationGroup registrationGroup = new RegistrationGroup();
        registrationGroup.setCourses(courseOfferingList);
        registrationGroup.setId(123L);
        registrationGroup.setStudents(studentList);

        ArrayList<RegistrationGroup> registrationGroupList = new ArrayList<>();
        registrationGroupList.add(registrationGroup);

        RegistrationEvent registrationEvent = new RegistrationEvent();
        registrationEvent.setEndDate(LocalDate.ofEpochDay(1L));
        registrationEvent.setId(123L);
        registrationEvent.setRegistrationGroups(registrationGroupList);
        registrationEvent.setStartDate(LocalDate.ofEpochDay(1L));
        Optional<RegistrationEvent> ofResult = Optional.of(registrationEvent);
        when(registrationEventRepository.findById((Long) any())).thenReturn(ofResult);

        ArrayList<RegistrationRequest> registrationRequestList = new ArrayList<>();
        registrationRequestList.add(new RegistrationRequest());
        when(registrationRequestService.getRegistrationRequests()).thenReturn(registrationRequestList);
        registrationEventServiceImpl.processEvent(123L);
    }

    /**
     * Method under test: {@link RegistrationEventServiceImpl#processEvent(Long)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testProcessEvent8() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IndexOutOfBoundsException
        //       at edu.miu.cs544.compro.backend.service.RegistrationEventServiceImpl.processEvent(RegistrationEventServiceImpl.java:129)
        //   See https://diff.blue/R013 to resolve this issue.

        doThrow(new IndexOutOfBoundsException()).when(emailKafkaSender).send((Student) any());

        ArrayList<CourseOffering> courseOfferingList = new ArrayList<>();
        courseOfferingList.add(new CourseOffering());

        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(new Student());

        RegistrationGroup registrationGroup = new RegistrationGroup();
        registrationGroup.setCourses(courseOfferingList);
        registrationGroup.setId(123L);
        registrationGroup.setStudents(studentList);

        ArrayList<RegistrationGroup> registrationGroupList = new ArrayList<>();
        registrationGroupList.add(registrationGroup);

        RegistrationEvent registrationEvent = new RegistrationEvent();
        registrationEvent.setEndDate(LocalDate.ofEpochDay(1L));
        registrationEvent.setId(123L);
        registrationEvent.setRegistrationGroups(registrationGroupList);
        registrationEvent.setStartDate(LocalDate.ofEpochDay(1L));
        Optional<RegistrationEvent> ofResult = Optional.of(registrationEvent);
        when(registrationEventRepository.findById((Long) any())).thenReturn(ofResult);
        when(registrationRequestService.getRegistrationRequests()).thenReturn(new ArrayList<>());
        registrationEventServiceImpl.processEvent(123L);
    }
}

