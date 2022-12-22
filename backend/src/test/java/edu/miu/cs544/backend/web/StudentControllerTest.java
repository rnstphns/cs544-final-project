package edu.miu.cs544.backend.web;

import static org.mockito.Mockito.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.cs544.backend.domain.Address;
import edu.miu.cs544.backend.domain.Student;
import edu.miu.cs544.backend.service.RegistrationEventService;
import edu.miu.cs544.backend.service.StudentService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {StudentController.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class StudentControllerTest {
    @MockBean
    private RegistrationEventService registrationEventService;

    @Autowired
    private StudentController studentController;

    @MockBean
    private StudentService studentService;

    /**
     * Method under test: {@link StudentController#getAllCourseOfferings()}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testGetAllCourseOfferings2() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        (new StudentController()).getAllCourseOfferings();
    }

    /**
     * Method under test: {@link StudentController#addstudent(Student)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testAddstudent2() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        StudentController studentController = new StudentController();
        studentController.addstudent(new Student());
    }

    /**
     * Method under test: {@link StudentController#addstudent(Student)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testAddstudent3() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        (new StudentController()).addstudent(mock(Student.class));
    }

    /**
     * Method under test: {@link StudentController#deletestudent(Long)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testDeletestudent2() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        (new StudentController()).deletestudent(123L);
    }

    /**
     * Method under test: {@link StudentController#updateStudent(Student, Long)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testUpdateStudent2() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        StudentController studentController = new StudentController();
        studentController.updateStudent(new Student(), 123L);
    }

    /**
     * Method under test: {@link StudentController#updateStudent(Student, Long)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testUpdateStudent3() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        (new StudentController()).updateStudent(mock(Student.class), 123L);
    }

    /**
     * Method under test: {@link StudentController#getallstudents()}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testGetallstudents2() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        (new StudentController()).getallstudents();
    }

    /**
     * Method under test: {@link StudentController#addstudent(Student)}
     */
    @Test
    public void testAddstudent() throws Exception {
        // Arrange
        // TODO: Populate arranged inputs
        Object[] uriVariables = new Object[]{};
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/students/addstudent", uriVariables)
                .contentType(MediaType.APPLICATION_JSON);

        Student student = new Student();
        Address address = new Address("Street", "Oxford", "OX1 1PT", "MD", "us-east-2");

        student.setAddress(address);
        student.setEmail("jane.doe@example.org");
        student.setId(123L);
        student.setName("Name");
        student.setStudentId(123);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(objectMapper.writeValueAsString(student));
        Object[] controllers = new Object[]{studentController};
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(controllers).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link StudentController#deletestudent(Long)}
     */
    @Test
    public void testDeletestudent() throws Exception {
        // Arrange
        // TODO: Populate arranged inputs
        Object[] uriVariables = new Object[]{123L};
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/students/deletebyid/{id}",
                uriVariables);
        Object[] controllers = new Object[]{studentController};
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(controllers).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link StudentController#getAllCourseOfferings()}
     */
    @Test
    public void testGetAllCourseOfferings() throws Exception {
        // Arrange
        // TODO: Populate arranged inputs
        Object[] uriVariables = new Object[]{};
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students/registration-events/latest",
                uriVariables);
        Object[] controllers = new Object[]{studentController};
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(controllers).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link StudentController#getallstudents()}
     */
    @Test
    public void testGetallstudents() throws Exception {
        // Arrange
        // TODO: Populate arranged inputs
        Object[] uriVariables = new Object[]{};
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students/studentlist", uriVariables);
        Object[] controllers = new Object[]{studentController};
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(controllers).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link StudentController#updateStudent(Student, Long)}
     */
    @Test
    public void testUpdateStudent() throws Exception {
        // Arrange
        // TODO: Populate arranged inputs
        Object[] uriVariables = new Object[]{123L};
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .put("/students/update/{id}", uriVariables)
                .contentType(MediaType.APPLICATION_JSON);

        Student student = new Student();
        Address address = new Address("Street", "Oxford", "OX1 1PT", "MD", "us-east-2");

        student.setAddress(address);
        student.setEmail("jane.doe@example.org");
        student.setId(123L);
        student.setName("Name");
        student.setStudentId(123);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(student));
        Object[] controllers = new Object[]{studentController};
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(controllers).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        // TODO: Add assertions on result
    }
}

