package edu.miu.cs544.backend.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.miu.cs544.backend.domain.CourseOffering;
import edu.miu.cs544.backend.domain.RegistrationEvent;
import edu.miu.cs544.backend.domain.RegistrationGroup;
import edu.miu.cs544.backend.domain.RegistrationRequest;
import edu.miu.cs544.backend.domain.Student;
import edu.miu.cs544.backend.exceptions.DatabaseException;
import edu.miu.cs544.backend.exceptions.EventNotOpenException;
import edu.miu.cs544.backend.exceptions.ObjectNotFoundException;
import edu.miu.cs544.backend.repositories.RegistrationRequestRepository;
import edu.miu.cs544.backend.repositories.StudentRepository;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {RegistrationRequestServiceImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class RegistrationRequestServiceImplTest {
    @MockBean
    private CourseOfferingService courseOfferingService;

    @MockBean
    private RegistrationEventService registrationEventService;

    @MockBean
    private RegistrationRequestRepository registrationRequestRepository;

    @Autowired
    private RegistrationRequestServiceImpl registrationRequestServiceImpl;

    @MockBean
    private StudentRepository studentRepository;


    /**
     * Method under test: {@link RegistrationRequestServiceImpl#getRegistrationRequests()}
     */
    @Test
    public void testGetRegistrationRequests() {
        ArrayList<RegistrationRequest> registrationRequestList = new ArrayList<>();
        when(registrationRequestRepository.findAll()).thenReturn(registrationRequestList);
        Collection<RegistrationRequest> actualRegistrationRequests = registrationRequestServiceImpl
                .getRegistrationRequests();
        assertSame(registrationRequestList, actualRegistrationRequests);
        assertTrue(actualRegistrationRequests.isEmpty());
        verify(registrationRequestRepository).findAll();
    }

    /**
     * Method under test: {@link RegistrationRequestServiceImpl#createRegistrationRequest(List, Integer)}
     */
    @Test
    public void testCreateRegistrationRequest() throws DatabaseException, EventNotOpenException, ObjectNotFoundException {
        RegistrationEvent registrationEvent = new RegistrationEvent();
        registrationEvent.setEndDate(LocalDate.ofEpochDay(1L));
        registrationEvent.setId(123L);
        registrationEvent.setRegistrationGroups(new ArrayList<>());
        registrationEvent.setStartDate(LocalDate.ofEpochDay(1L));
        when(registrationEventService.latest()).thenReturn(registrationEvent);
        assertFalse(registrationRequestServiceImpl.createRegistrationRequest(new ArrayList<>(), 1));
        verify(registrationEventService).latest();
    }

    /**
     * Method under test: {@link RegistrationRequestServiceImpl#createRegistrationRequest(List, Integer)}
     */
    @Test
    public void testCreateRegistrationRequest2()
            throws DatabaseException, EventNotOpenException, ObjectNotFoundException {
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
        when(registrationEventService.latest()).thenReturn(registrationEvent);
        assertFalse(registrationRequestServiceImpl.createRegistrationRequest(new ArrayList<>(), 1));
        verify(registrationEventService).latest();
    }




    /**
     * Method under test: {@link RegistrationRequestServiceImpl#createRegistrationRequest(List, Integer)}
     */
    @Test
    public void testCreateRegistrationRequest5()
            throws DatabaseException, EventNotOpenException, ObjectNotFoundException {
        RegistrationEvent registrationEvent = new RegistrationEvent();
        registrationEvent.setEndDate(LocalDate.ofEpochDay(1L));
        registrationEvent.setId(123L);
        registrationEvent.setRegistrationGroups(new ArrayList<>());
        registrationEvent.setStartDate(LocalDate.ofEpochDay(1L));
        when(registrationEventService.latest()).thenReturn(registrationEvent);
        when(registrationRequestRepository.save((RegistrationRequest) any())).thenReturn(new RegistrationRequest());
        Student student = new Student();
        when(studentRepository.findByStudentId((Integer) any())).thenReturn(Optional.of(student));

        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setCourseList(new ArrayList<>());

        ArrayList<RegistrationRequest> registrationRequestList = new ArrayList<>();
        registrationRequestList.add(registrationRequest);
        assertTrue(registrationRequestServiceImpl.createRegistrationRequest(registrationRequestList, 1));
        verify(registrationEventService).latest();
        verify(registrationRequestRepository).save((RegistrationRequest) any());
        verify(studentRepository).findByStudentId((Integer) any());
        assertSame(student, registrationRequestList.get(0).getStudent());
    }

    /**
     * Method under test: {@link RegistrationRequestServiceImpl#createRegistrationRequest(List, Integer)}
     */
    @Test
    public void testCreateRegistrationRequest6()
            throws DatabaseException, EventNotOpenException, ObjectNotFoundException {
        RegistrationEvent registrationEvent = new RegistrationEvent();
        registrationEvent.setEndDate(LocalDate.ofEpochDay(1L));
        registrationEvent.setId(123L);
        registrationEvent.setRegistrationGroups(new ArrayList<>());
        registrationEvent.setStartDate(LocalDate.ofEpochDay(1L));
        when(registrationEventService.latest()).thenReturn(registrationEvent);
        when(registrationRequestRepository.save((RegistrationRequest) any())).thenReturn(new RegistrationRequest());
        when(studentRepository.findByStudentId((Integer) any())).thenReturn(Optional.of(new Student()));
        RegistrationRequest registrationRequest = mock(RegistrationRequest.class);
        when(registrationRequest.getCourseList()).thenReturn(new ArrayList<>());
        doNothing().when(registrationRequest).setCourseList((List<CourseOffering>) any());
        doNothing().when(registrationRequest).setStudent((Student) any());
        registrationRequest.setCourseList(new ArrayList<>());

        ArrayList<RegistrationRequest> registrationRequestList = new ArrayList<>();
        registrationRequestList.add(registrationRequest);
        assertTrue(registrationRequestServiceImpl.createRegistrationRequest(registrationRequestList, 1));
        verify(registrationEventService).latest();
        verify(registrationRequestRepository).save((RegistrationRequest) any());
        verify(studentRepository).findByStudentId((Integer) any());
        verify(registrationRequest).getCourseList();
        verify(registrationRequest).setCourseList((List<CourseOffering>) any());
        verify(registrationRequest).setStudent((Student) any());
    }

    /**
     * Method under test: {@link RegistrationRequestServiceImpl#createRegistrationRequest(List, Integer)}
     */
    @Test
    public void testCreateRegistrationRequest7()
            throws DatabaseException, EventNotOpenException, ObjectNotFoundException {
        RegistrationEvent registrationEvent = new RegistrationEvent();
        registrationEvent.setEndDate(LocalDate.ofEpochDay(1L));
        registrationEvent.setId(123L);
        registrationEvent.setRegistrationGroups(new ArrayList<>());
        registrationEvent.setStartDate(LocalDate.ofEpochDay(1L));
        when(registrationEventService.latest()).thenReturn(registrationEvent);
        when(registrationRequestRepository.save((RegistrationRequest) any())).thenReturn(new RegistrationRequest());
        when(studentRepository.findByStudentId((Integer) any())).thenReturn(Optional.of(new Student()));

        ArrayList<CourseOffering> courseOfferingList = new ArrayList<>();
        courseOfferingList.add(new CourseOffering());
        RegistrationRequest registrationRequest = mock(RegistrationRequest.class);
        when(registrationRequest.getCourseList()).thenReturn(courseOfferingList);
        doNothing().when(registrationRequest).setCourseList((List<CourseOffering>) any());
        doNothing().when(registrationRequest).setStudent((Student) any());
        registrationRequest.setCourseList(new ArrayList<>());

        ArrayList<RegistrationRequest> registrationRequestList = new ArrayList<>();
        registrationRequestList.add(registrationRequest);
        assertTrue(registrationRequestServiceImpl.createRegistrationRequest(registrationRequestList, 1));
        verify(registrationEventService).latest();
        verify(registrationRequestRepository).save((RegistrationRequest) any());
        verify(studentRepository).findByStudentId((Integer) any());
        verify(registrationRequest).getCourseList();
        verify(registrationRequest, atLeast(1)).setCourseList((List<CourseOffering>) any());
        verify(registrationRequest).setStudent((Student) any());
    }

    /**
     * Method under test: {@link RegistrationRequestServiceImpl#createRegistrationRequest(List, Integer)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testCreateRegistrationRequest8()
            throws DatabaseException, EventNotOpenException, ObjectNotFoundException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "edu.miu.cs544.backend.domain.CourseOffering.getCode()" because "course" is null
        //       at edu.miu.cs544.backend.service.RegistrationRequestServiceImpl.createRegistrationRequest(RegistrationRequestServiceImpl.java:68)
        //   See https://diff.blue/R013 to resolve this issue.

        RegistrationEvent registrationEvent = new RegistrationEvent();
        registrationEvent.setEndDate(LocalDate.ofEpochDay(1L));
        registrationEvent.setId(123L);
        registrationEvent.setRegistrationGroups(new ArrayList<>());
        registrationEvent.setStartDate(LocalDate.ofEpochDay(1L));
        when(registrationEventService.latest()).thenReturn(registrationEvent);
        when(registrationRequestRepository.save((RegistrationRequest) any())).thenReturn(new RegistrationRequest());
        when(studentRepository.findByStudentId((Integer) any())).thenReturn(Optional.of(new Student()));

        ArrayList<CourseOffering> courseOfferingList = new ArrayList<>();
        courseOfferingList.add(null);
        RegistrationRequest registrationRequest = mock(RegistrationRequest.class);
        when(registrationRequest.getCourseList()).thenReturn(courseOfferingList);
        doNothing().when(registrationRequest).setCourseList((List<CourseOffering>) any());
        doNothing().when(registrationRequest).setStudent((Student) any());
        registrationRequest.setCourseList(new ArrayList<>());

        ArrayList<RegistrationRequest> registrationRequestList = new ArrayList<>();
        registrationRequestList.add(registrationRequest);
        registrationRequestServiceImpl.createRegistrationRequest(registrationRequestList, 1);
    }

    /**
     * Method under test: {@link RegistrationRequestServiceImpl#deleteRegistrationRequest(Long)}
     */
    @Test
    public void testDeleteRegistrationRequest() {
        doNothing().when(registrationRequestRepository).deleteById((Long) any());
        registrationRequestServiceImpl.deleteRegistrationRequest(123L);
        verify(registrationRequestRepository).deleteById((Long) any());
        assertTrue(registrationRequestServiceImpl.getRegistrationRequests().isEmpty());
    }


}

