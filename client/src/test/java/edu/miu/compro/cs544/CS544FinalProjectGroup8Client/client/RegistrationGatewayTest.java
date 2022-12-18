package edu.miu.compro.cs544.CS544FinalProjectGroup8Client.client;

import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationGatewayTest {


    Collection<Student> students = new ArrayList<Student>();
    Address studentAddress = new Address("1000 N 4th St", "Fairfield", "52557", "IA", "USA");
    //for loop generate students
    Faculty professor = new Faculty("Professor Professerson", "prof@miu.edu", studentAddress, "Tenured Professor");
    Collection<Faculty> professors = new ArrayList<>();
    //add professor to list
    AcademicBlock decBlock = new AcademicBlock("2022-12A-12D", "December 2022", "Fall", LocalDate.of(2022, 11, 28), LocalDate.of(2022, 12, 22));
    Course ea = new Course("CS544", "Enterprise Architecture");
    String eaCode = ea.getCode()+decBlock.getCode()+"PP";
    CourseOffering eaDec = new CourseOffering(eaCode, decBlock, ea,  professors, 50, 50);
    RegistrationGroup registrationGroup = new RegistrationGroup();
    //populate registration group
    RegistrationEvent registrationEvent = new RegistrationEvent();
    //populate event
    @BeforeEach
    void setUp() {
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

    }

    @Test
    void getRegistrationEvents() {

    }

    @Test
    void registerStudent() {
    }

    @Test
    void getRegistrationsByStudent() {
    }

    @Test
    void getRegistrationEventById() {
    }

    @Test
    void processRegistrationEvent() {
    }
}