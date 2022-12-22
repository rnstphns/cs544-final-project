package edu.miu.cs544.compro.backend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.miu.cs544.compro.backend.domain.RegistrationEvent;
import edu.miu.cs544.compro.backend.domain.RegistrationGroup;
import edu.miu.cs544.compro.backend.domain.Student;
import edu.miu.cs544.compro.backend.exceptions.DatabaseException;
import edu.miu.cs544.compro.backend.exceptions.ObjectNotFoundException;
import edu.miu.cs544.compro.backend.repositories.RegistrationEventRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.Ignore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {RegistrationEventServiceImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class RegistrationEventServiceImplTest {
    @MockBean
    private RegistrationEventRepository registrationEventRepository;

    @Autowired
    private RegistrationEventServiceImpl registrationEventServiceImpl;

    @MockBean
    private RegistrationGroupService registrationGroupService;

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
        //       at edu.miu.cs544.backend.service.RegistrationEventServiceImpl.getRegistrationEvents(RegistrationEventServiceImpl.java:40)
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
        //       at edu.miu.cs544.backend.service.RegistrationEventServiceImpl.createRegistrationEvent(RegistrationEventServiceImpl.java:47)
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
        //       at edu.miu.cs544.backend.service.RegistrationEventServiceImpl.deleteRegistrationEvent(RegistrationEventServiceImpl.java:54)
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
        //       at edu.miu.cs544.backend.service.RegistrationEventServiceImpl.updateWindow(RegistrationEventServiceImpl.java:64)
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
     * Method under test: {@link RegistrationEventServiceImpl#latest()}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testLatest() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Iterable.iterator()" because "iterable" is null
        //       at edu.miu.cs544.backend.service.RegistrationEventServiceImpl.latest(RegistrationEventServiceImpl.java:80)
        //   See https://diff.blue/R013 to resolve this issue.

        when(registrationEventRepository.findAll((Sort) any())).thenReturn(new ArrayList<>());
        registrationEventServiceImpl.latest();
    }

    /**
     * Method under test: {@link RegistrationEventServiceImpl#latest()}
     */
    @Test
    public void testLatest2() {
        RegistrationEvent registrationEvent = new RegistrationEvent();
        registrationEvent.setEndDate(LocalDate.ofEpochDay(1L));
        registrationEvent.setId(123L);
        registrationEvent.setRegistrationGroups(new ArrayList<>());
        registrationEvent.setStartDate(LocalDate.ofEpochDay(1L));

        ArrayList<RegistrationEvent> registrationEventList = new ArrayList<>();
        registrationEventList.add(registrationEvent);
        when(registrationEventRepository.findAll((Sort) any())).thenReturn(registrationEventList);
        RegistrationEvent actualLatestResult = registrationEventServiceImpl.latest();
        assertSame(registrationEvent, actualLatestResult);
        assertTrue(actualLatestResult.getRegistrationGroups().isEmpty());
        verify(registrationEventRepository).findAll((Sort) any());
    }

    /**
     * Method under test: {@link RegistrationEventServiceImpl#latest()}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testLatest3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IndexOutOfBoundsException
        //       at edu.miu.cs544.backend.service.RegistrationEventServiceImpl.latest(RegistrationEventServiceImpl.java:72)
        //   See https://diff.blue/R013 to resolve this issue.

        when(registrationEventRepository.findAll((Sort) any())).thenThrow(new IndexOutOfBoundsException());
        registrationEventServiceImpl.latest();
    }

    /**
     * Method under test: {@link RegistrationEventServiceImpl#latest()}
     */
    @Test
    public void testLatest4() {
        RegistrationGroup registrationGroup = new RegistrationGroup();
        registrationGroup.setCourses(new ArrayList<>());
        registrationGroup.setId(123L);
        ArrayList<Student> studentList = new ArrayList<>();
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
        RegistrationEvent actualLatestResult = registrationEventServiceImpl.latest();
        assertSame(registrationEvent, actualLatestResult);
        Collection<RegistrationGroup> registrationGroups = actualLatestResult.getRegistrationGroups();
        assertEquals(1, registrationGroups.size());
        assertEquals(studentList, ((List<RegistrationGroup>) registrationGroups).get(0).getStudents());
        verify(registrationEventRepository).findAll((Sort) any());
    }
}

