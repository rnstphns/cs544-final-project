package edu.miu.compro.cs544.CS544FinalProjectGroup8Client.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.client.StudentGateway;
import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.client.Students;
import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model.Address;
import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model.Student;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {StudentController.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class StudentControllerTest {
    @Autowired
    private StudentController studentController;

    @MockBean
    private StudentGateway studentGateway;

    /**
     * Method under test: {@link StudentController#addstudent(Student)}
     */
    @Test
    public void testAddstudent() throws Exception {
        when((ResponseEntity<Object>) studentGateway.addstudent((Student) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));

        Student student = new Student();
        student.setAddress(new Address("Street", "Oxford", "OX1 1PT", "MD", "us-east-2"));
        student.setEmail("jane.doe@example.org");
        student.setName("Name");
        student.setStudentId(123);
        String content = (new ObjectMapper()).writeValueAsString(student);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/students/addstudent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(studentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    /**
     * Method under test: {@link StudentController#deletestudent(Long)}
     */
    @Test
    public void testDeletestudent() throws Exception {
        when(studentGateway.deletestudent((Long) any())).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/students/deletebyid/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(studentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    /**
     * Method under test: {@link StudentController#updateStudent(Student, Long)}
     */
    @Test
    public void testUpdateStudent() throws Exception {
        when((ResponseEntity<Object>) studentGateway.updateStudent((Student) any(), (Long) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));

        Student student = new Student();
        student.setAddress(new Address("Street", "Oxford", "OX1 1PT", "MD", "us-east-2"));
        student.setEmail("jane.doe@example.org");
        student.setName("Name");
        student.setStudentId(123);
        String content = (new ObjectMapper()).writeValueAsString(student);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/students/update/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(studentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    /**
     * Method under test: {@link StudentController#getallstudents()}
     */
    @Test
    public void testGetallstudents() throws Exception {
        Students students = new Students();
        students.setStudents(new ArrayList<>());
        when(studentGateway.getallstudents()).thenReturn(students);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students/studentlist");
        MockMvcBuilders.standaloneSetup(studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"students\":[]}"));
    }

    /**
     * Method under test: {@link StudentController#getallstudents()}
     */
    @Test
    public void testGetallstudents2() throws Exception {
        Students students = new Students();
        students.setStudents(new ArrayList<>());
        when(studentGateway.getallstudents()).thenReturn(students);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/students/studentlist");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(studentController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"students\":[]}"));
    }
}

