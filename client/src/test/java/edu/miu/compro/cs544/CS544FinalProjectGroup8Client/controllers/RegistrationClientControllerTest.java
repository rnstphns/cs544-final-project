package edu.miu.compro.cs544.CS544FinalProjectGroup8Client.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.client.RegistrationGateway;
import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.client.Registrations;
import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model.*;
import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model.AcademicBlock;
import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model.Course;
import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model.CourseOffering;
import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model.RegistrationEvent;
import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model.RegistrationEvents;
import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model.RegistrationGroup;
import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model.RegistrationRequest;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {RegistrationClientController.class})
@RunWith(SpringRunner.class)
@WebMvcTest(RegistrationClientController.class)
public class RegistrationClientControllerTest {

    @Autowired
    private RegistrationClientController registrationClientController;

    @Autowired
    MockMvc mock;

    @MockBean
    RegistrationGateway registrationGateway;

    ArrayList<Student> students = new ArrayList<Student>();
    Address studentAddress = new Address("1000 N 4th St", "Fairfield", "52557", "IA", "USA");
    //for loop generate students
    Faculty professor = new Faculty("Professor Professerson", "prof@miu.edu", studentAddress, "Tenured Professor");
    Collection<Faculty> professors = new ArrayList<>();
    //add professor to list
    AcademicBlock decBlock = new AcademicBlock(111L, "2022-12A-12D", "December 2022", "Fall", LocalDate.of(2022, 11, 28), LocalDate.of(2022, 12, 22));
    Course ea = new Course("CS544", "Enterprise Architecture");
    String eaCode = ea.getCode() + decBlock.getCode() + "PP";
    CourseOffering eaDec = new CourseOffering(eaCode, decBlock, ea, professors, 50, 50);
    RegistrationGroup registrationGroup = new RegistrationGroup();
    //populate registration group
    RegistrationEvent registrationEvent = new RegistrationEvent();
    //populate event
    RegistrationEvents events = new RegistrationEvents();

    /**
     * Method under test: {@link RegistrationClientController#createRegistrationEvent(RegistrationEvent)}
     */
    @org.junit.Test
    @Ignore
    public void testCreateRegistrationEvent() throws Exception {
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/registration-events/create")
                .contentType(MediaType.APPLICATION_JSON);
        LocalDate endDate = LocalDate.of(2023,1,31);

        RegistrationEvent registrationEvent = new RegistrationEvent();
        registrationEvent.setEndDate(endDate);
        registrationEvent.setRegistrationGroups(new ArrayList<>());
        registrationEvent.setStartDate(LocalDate.of(2023,1,31));
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content((new ObjectMapper()).writeValueAsString(registrationEvent));
        MockMvcBuilders.standaloneSetup(registrationClientController).build().perform(requestBuilder);
    }

    /**
     * Method under test: {@link RegistrationClientController#getLatestRegistrationEvent()}
     */
    @org.junit.Test
    public void testGetLatestRegistrationEvent() throws Exception {
        RegistrationEvents registrationEvents = new RegistrationEvents();
        registrationEvents.setRegistrationEventCollection(new ArrayList<>());
        when(registrationGateway.getLatestRegistrationEvent()).thenReturn(registrationEvents);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/registration-events/latest");
        MockMvcBuilders.standaloneSetup(registrationClientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"registrationEventCollection\":[]}"));
    }

    /**
     * Method under test: {@link RegistrationClientController#getLatestRegistrationEvent()}
     */
    @org.junit.Test
    public void testGetLatestRegistrationEvent2() throws Exception {
        RegistrationEvents registrationEvents = new RegistrationEvents();
        registrationEvents.setRegistrationEventCollection(new ArrayList<>());
        when(registrationGateway.getLatestRegistrationEvent()).thenReturn(registrationEvents);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/registration-events/latest");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(registrationClientController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"registrationEventCollection\":[]}"));
    }

    /**
     * Method under test: {@link RegistrationClientController#getRegistrationsByStudent(Integer)}
     */
    @org.junit.Test
    public void testGetRegistrationsByStudent() throws Exception {
        Registrations registrations = new Registrations();
        registrations.setRegistrations(new ArrayList<>());
        when(registrationGateway.getRegistrationsByStudent((Integer) any())).thenReturn(registrations);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/registration-events/request/{studentId}", 123);
        MockMvcBuilders.standaloneSetup(registrationClientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"registrations\":[]}"));
    }

    /**
     * Method under test: {@link RegistrationClientController#getRegistrationsByStudent(Integer)}
     */
    @org.junit.Test
    public void testGetRegistrationsByStudent2() throws Exception {
        Registrations registrations = new Registrations();
        registrations.setRegistrations(new ArrayList<>());
        when(registrationGateway.getRegistrationsByStudent((Integer) any())).thenReturn(registrations);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/registration-events/request/{studentId}",
                123);
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(registrationClientController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"registrations\":[]}"));
    }

    /**
     * Method under test: {@link RegistrationClientController#getRegistrationEventById(Long)}
     */
    @org.junit.Test
    public void testGetRegistrationEventById() throws Exception {
        RegistrationEvent registrationEvent = new RegistrationEvent();
        registrationEvent.setEndDate(LocalDate.ofEpochDay(1L));
        registrationEvent.setRegistrationGroups(new ArrayList<>());
        registrationEvent.setStartDate(LocalDate.ofEpochDay(1L));
        when(registrationGateway.getRegistrationEventById((Long) any())).thenReturn(registrationEvent);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/registration-events/{id}", 123L);
        MockMvcBuilders.standaloneSetup(registrationClientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"startDate\":[1970,1,2],\"endDate\":[1970,1,2],\"registrationGroups\":[]}"));
    }

    /**
     * Method under test: {@link RegistrationClientController#getRegistrationEvents()}
     */
    @org.junit.Test
    public void testGetRegistrationEvents() throws Exception {
        when(registrationGateway.getRegistrationEvents()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/registration-events/get");
        MockMvcBuilders.standaloneSetup(registrationClientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link RegistrationClientController#getRegistrationEvents()}
     */
    @org.junit.Test
    public void testGetRegistrationEvents2() throws Exception {
        when(registrationGateway.getRegistrationEvents()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/registration-events/get");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(registrationClientController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link RegistrationClientController#createRegistrationEvent(RegistrationEvent)}
     */
    @org.junit.Test
    public void testCreateRegistrationEvent2() throws Exception {
        when((ResponseEntity<Object>) registrationGateway.createRegistrationEvent((RegistrationEvent) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));

        RegistrationEvent registrationEvent = new RegistrationEvent();
        registrationEvent.setEndDate(null);
        registrationEvent.setRegistrationGroups(new ArrayList<>());
        registrationEvent.setStartDate(null);
        String content = (new ObjectMapper()).writeValueAsString(registrationEvent);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/registration-events/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(registrationClientController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    /**
     * Method under test: {@link RegistrationClientController#processRegistrationEvent(Long)}
     */
    @org.junit.Test
    public void testProcessRegistrationEvent() throws Exception {
        RegistrationEvent registrationEvent = new RegistrationEvent();
        registrationEvent.setEndDate(LocalDate.ofEpochDay(1L));
        registrationEvent.setRegistrationGroups(new ArrayList<>());
        registrationEvent.setStartDate(LocalDate.ofEpochDay(1L));
        when(registrationGateway.processRegistrationEvent((Long) any())).thenReturn(registrationEvent);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/registration-events/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(registrationClientController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(102));
    }


    /**
     * Method under test: {@link RegistrationClientController#createCourseOffering(CourseOffering)}
     */
    @org.junit.Test
    public void testCreateCourseOffering() throws Exception {
        when((ResponseEntity<Object>) registrationGateway.createCourseOffering((CourseOffering) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));

        CourseOffering courseOffering = new CourseOffering();
        courseOffering.setAcademicBlock(new AcademicBlock());
        courseOffering.setAvailableSeats(1);
        courseOffering.setCapacity(3);
        courseOffering.setCode("Code");
        courseOffering.setCourse(new Course("Code", "The characteristics of someone or something"));
        courseOffering.setFaculty(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(courseOffering);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/registration-events/course-offering")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(registrationClientController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    /**
     * Method under test: {@link RegistrationClientController#createRegistrationGroup(RegistrationGroup)}
     */
    @org.junit.Test
    public void testCreateRegistrationGroup() throws Exception {
        when((ResponseEntity<Object>) registrationGateway.createRegistrationGroup((RegistrationGroup) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));

        RegistrationGroup registrationGroup = new RegistrationGroup();
        registrationGroup.setCourses(new ArrayList<>());
        registrationGroup.setStudents(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(registrationGroup);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/registration-events/registration-group")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(registrationClientController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }


    /**
     * Method under test: {@link RegistrationClientController#registerStudent(Integer, List)}
     */
    @org.junit.Test
    public void testRegisterStudent() throws Exception {
        when((ResponseEntity<Object>) registrationGateway.sendRegistrationRequest((List<RegistrationRequest>) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .post("/registration-events/request/{studentId}", 123)
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ArrayList<>()));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(registrationClientController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }
}