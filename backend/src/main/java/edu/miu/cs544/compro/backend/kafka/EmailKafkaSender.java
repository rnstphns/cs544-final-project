package edu.miu.cs544.compro.backend.kafka;

import edu.miu.cs544.compro.backend.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
@Service
public class EmailKafkaSender {

    @Autowired
    private KafkaTemplate<String, EmailTemplate> kafkaTemplate;

    @Autowired
    private EmailBuilder emailBuilder;

    public void send(Student student){
        EmailTemplate email = emailBuilder.generateRegistrationSuccessEmail(student);
        kafkaTemplate.send("registrationEmail", email);
    }
}
