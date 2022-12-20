package edu.miu.cs544.backend.kafka;
import edu.miu.cs544.backend.domain.Student;

public class EmailBuilder {

    public EmailTemplate generateRegistrationSuccessEmail(Student student){
        String registrationMessage = student.getName()+" , your MIU course registration has been processed.";
        return new EmailTemplate("compro-group-8@miu.edu", student.getEmail(), "Registration Confirmed", registrationMessage);
    }
}
