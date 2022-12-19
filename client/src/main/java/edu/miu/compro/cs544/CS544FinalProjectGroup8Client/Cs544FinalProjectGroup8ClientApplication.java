package edu.miu.compro.cs544.CS544FinalProjectGroup8Client;

import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.client.RegistrationGateway;
import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.controllers.RegistrationClientController;
import edu.miu.compro.cs544.CS544FinalProjectGroup8Client.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@SpringBootApplication
public class Cs544FinalProjectGroup8ClientApplication implements CommandLineRunner {

	@Autowired
	private RegistrationClientController controller;

	@Autowired
	private RegistrationGateway gateway;

	public static void main(String[] args) {
		SpringApplication.run(Cs544FinalProjectGroup8ClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ArrayList<Student> students = new ArrayList<Student>();
		Address studentAddress = new Address("testing 2 events", "Fairfield", "52557", "IA", "USA");
		for (int i = 1; i <= 10; i++) {
			Student s = new Student("Student"+i, "student"+i+"@miu.edu", studentAddress, i);
			students.add(s);
		}
		Faculty professor = new Faculty("Professor 2", "prof@miu.edu", studentAddress, "Tenured Professor");
		Collection<Faculty> professors = new ArrayList<>();
		professors.add(professor);
		AcademicBlock decBlock = new AcademicBlock("2022-12A-12D", "December 2022", "Fall", LocalDate.of(2022, 11, 28), LocalDate.of(2022, 12, 22));
		Course ea = new Course("CS544", "Enterprise Architecture");
		String eaCode = ea.getCode()+decBlock.getCode()+"PP";
		CourseOffering eaDec = new CourseOffering(eaCode, decBlock, ea,  professors, 50, 50);
//		gateway.createCourseOffering(eaDec);
		RegistrationGroup registrationGroup = new RegistrationGroup();
		Collection<CourseOffering> courses = new ArrayList<>();
		courses.add(eaDec);
		registrationGroup.setStudents(students);
		registrationGroup.setCourses(courses);
//		gateway.createRegistrationGroup(registrationGroup);
		RegistrationEvent registrationEvent = new RegistrationEvent();
		Collection<RegistrationGroup> registrationGroups = new ArrayList<>();
		registrationGroups.add(registrationGroup);
		registrationEvent.setRegistrationGroups(registrationGroups);
		registrationEvent.setStartDate(LocalDate.of(2023,2,1));
		registrationEvent.setEndDate(LocalDate.of(2023,2,28));
		ResponseEntity<?> re = gateway.createRegistrationEvent(registrationEvent);
		System.out.println(re);
	}

	//TODO before Monday
	//TODO rest client, tests
	//TODO rest controller, tests
}
