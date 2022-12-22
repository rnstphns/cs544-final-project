package edu.miu.cs544.compro.backend.kafka;
import edu.miu.cs544.compro.backend.domain.Student;
import org.springframework.stereotype.Component;

@Component
public class EmailBuilder {

    public EmailTemplate generateRegistrationSuccessEmail(Student student){
        String registrationMessage = student.getName()+" , your MIU course registration has been processed.";
        return new EmailTemplate("compro-group-8@miu.edu", student.getEmail(), "Registration Confirmed", registrationMessage);
    }
}
