package edu.miu.compro.cs544.CS544FinalProjectGroup8Client.controllers;

import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.client.RegistrationGateway;
import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@WebMvcTest(RegistrationClientController.class)
class RegistrationClientControllerTest {

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
    AcademicBlock decBlock = new AcademicBlock(111L,"2022-12A-12D", "December 2022", "Fall", LocalDate.of(2022, 11, 28), LocalDate.of(2022, 12, 22));
    Course ea = new Course("CS544", "Enterprise Architecture");
    String eaCode = ea.getCode()+decBlock.getCode()+"PP";
    CourseOffering eaDec = new CourseOffering(eaCode, decBlock, ea,  professors, 50, 50);
    RegistrationGroup registrationGroup = new RegistrationGroup();
    //populate registration group
    RegistrationEvent registrationEvent = new RegistrationEvent();
    //populate event
    RegistrationEvents events = new RegistrationEvents();
    @BeforeEach
    void setUp(){
        //Set up test RegistrationEvent Object
        //for loop generate students
        for (int i = 1; i <= 10; i++) {
            Student s = new Student( "Student"+i, "student"+i+"@miu.edu", studentAddress, i);
            students.add(s);
        }
        //add professor to list
        professors.add(professor);
        //populate registration group
        Collection<CourseOffering> courses = new ArrayList<>();
        courses.add(eaDec);
        registrationGroup.setStudents(students);
        registrationGroup.setCourses(courses);
        //populate event
        Collection<RegistrationGroup> registrationGroups = new ArrayList<>();
        registrationGroups.add(registrationGroup);
        registrationEvent.setRegistrationGroups(registrationGroups);
        registrationEvent.setStartDate(LocalDate.of(2023,1,1));
        registrationEvent.setEndDate(LocalDate.of(2023,1,31));
        //populate events object
        ArrayList<RegistrationEvent> list = new ArrayList<>();
        list.add(registrationEvent);
        events.setRegistrationEventCollection(list);
    }
    @Test
    void getRegistrationEvents() throws Exception{
//        Mockito.when(registrationGateway.getRegistrationEvents())
//                .thenReturn(new ResponseEntity<List<RegistrationEvent>>(events.getRegistrationEventCollection(), HttpStatus.OK).getBody());
        mock.perform(MockMvcRequestBuilders.get("/registration-events/latest"))
                .andExpect(status().isOk());
        //TODO check object json
    }

    @Test
    void registerStudent() throws Exception{

    }

    @Test
    void getRegistrationsByStudent() throws Exception{

    }

    @Test
    void getRegistrationEventById() throws Exception{
    }

    @Test
    void processRegistrationEvent() throws Exception{
    }
}