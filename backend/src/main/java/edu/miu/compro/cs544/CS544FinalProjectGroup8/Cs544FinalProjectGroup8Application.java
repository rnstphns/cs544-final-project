package edu.miu.compro.cs544.CS544FinalProjectGroup8;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Cs544FinalProjectGroup8Application implements CommandLineRunner {

	@Value("server.port")
	private String serverPort;

	@Value("spring.kafka.bootstrap-servers")
	private String kafkaServer;

	public static void main(String[] args) {
		SpringApplication.run(Cs544FinalProjectGroup8Application.class, args);	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Server running at: "+serverPort);
		log.info("Listening on Kafka server: "+kafkaServer);
		//create students, course offerings, faculty, etc
	}

	//TODO before Monday for backend:
	//TODO REST controller
	//TODO kafka send message to email service -- on final registration send mail to each student
	//TODO logging slf4j on controller methods?
	//TODO tests
}
