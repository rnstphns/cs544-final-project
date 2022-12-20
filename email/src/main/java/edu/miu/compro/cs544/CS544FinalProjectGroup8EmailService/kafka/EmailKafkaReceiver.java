package edu.miu.compro.cs544.CS544FinalProjectGroup8EmailService.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;


@Service
public class EmailKafkaReceiver {

    @KafkaListener(topics={"registrationEmail"})
    public void receive(@Payload EmailTemplate emailTemplate){
        //TODO send mail
        System.out.println(emailTemplate);
    }
}
