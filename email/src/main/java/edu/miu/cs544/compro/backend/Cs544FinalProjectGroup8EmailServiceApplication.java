package edu.miu.cs544.compro.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class Cs544FinalProjectGroup8EmailServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Cs544FinalProjectGroup8EmailServiceApplication.class, args);
	}

}
