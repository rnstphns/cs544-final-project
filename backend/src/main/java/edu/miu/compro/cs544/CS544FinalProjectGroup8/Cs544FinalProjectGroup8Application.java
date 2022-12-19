package edu.miu.compro.cs544.CS544FinalProjectGroup8;

import edu.miu.compro.cs544.CS544FinalProjectGroup8.controller.RegistrationEventController;
import edu.miu.compro.cs544.CS544FinalProjectGroup8.controller.StudentController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.servlet.http.HttpServlet;

@Slf4j
@SpringBootApplication
//		(scanBasePackages = {"edu.miu.compro.cs544.CS544FinalProjectGroup8",
//		"edu.miu.compro.cs544.CS544FinalProjectGroup8.controller"})
//@EnableKafka
@ComponentScan(basePackages = "domain")
@EnableJpaRepositories(basePackages = "repositories")
public class Cs544FinalProjectGroup8Application implements CommandLineRunner {


	public static void main(String[] args) {

		SpringApplication.run(Cs544FinalProjectGroup8Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}

	//TODO before Monday for backend:
	//TODO REST controller
	//TODO kafka send message to email service -- on final registration send mail to each student
	//TODO logging slf4j on controller methods?
	//TODO tests
}
