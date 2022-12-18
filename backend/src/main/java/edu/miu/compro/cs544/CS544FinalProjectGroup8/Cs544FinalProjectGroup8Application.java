package edu.miu.compro.cs544.CS544FinalProjectGroup8;

import edu.miu.compro.cs544.CS544FinalProjectGroup8.controller.RegistrationEventController;
import edu.miu.compro.cs544.CS544FinalProjectGroup8.controller.StudentController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@SpringBootApplication
//@EnableKafka
@ComponentScan(basePackages = "edu.miu.compro.cs544.CS544FinalProjectGroup8")
public class Cs544FinalProjectGroup8Application {

	@Value("server.port")
	private String serverPort;

	@Value("spring.kafka.bootstrap-servers")
	private String kafkaServer;


	public static void main(String[] args) {
		log.info("Server running at: "+8081);
		log.info("Listening on Kafka server: "+"localhost:9092");
		SpringApplication.run(Cs544FinalProjectGroup8Application.class, args);
	}

	//TODO before Monday for backend:
	//TODO REST controller
	//TODO kafka send message to email service -- on final registration send mail to each student
	//TODO logging slf4j on controller methods?
	//TODO tests
}
