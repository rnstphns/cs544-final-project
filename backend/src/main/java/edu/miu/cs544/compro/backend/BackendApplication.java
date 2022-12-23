package edu.miu.cs544.compro.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
//@EnableKafka
public class BackendApplication implements CommandLineRunner {

//    @Autowired
//    private EmailKafkaSender sender;
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Address studentAddress = new Address("1000 N 4th St", "Fairfield", "52557", "IA", "USA");
//        sender.send(new Student());
    }
}
