package edu.miu.cs544.backend;

import edu.miu.cs544.backend.repositories.RegistrationGroupRepository;
import edu.miu.cs544.backend.service.RegistrationEventService;
import edu.miu.cs544.backend.service.RegistrationGroupService;
import edu.miu.cs544.backend.service.RegistrationGroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class BackendApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
