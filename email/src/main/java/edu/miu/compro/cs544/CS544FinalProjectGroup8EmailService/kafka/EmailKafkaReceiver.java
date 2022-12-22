package edu.miu.compro.cs544.CS544FinalProjectGroup8EmailService.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;


@Service
public class EmailKafkaReceiver {

    @Autowired
    private JavaMailSender javaMailSender;

    @KafkaListener(topics={"registrationEmail"})
    public void receive(@Payload EmailTemplate emailTemplate){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailTemplate.getFromAddress());
        message.setTo(emailTemplate.getToAddress());
        message.setSubject(emailTemplate.getTitle());
        message.setText(emailTemplate.getMessage());
        javaMailSender.send(message);
        System.out.println(emailTemplate);
    }
}
