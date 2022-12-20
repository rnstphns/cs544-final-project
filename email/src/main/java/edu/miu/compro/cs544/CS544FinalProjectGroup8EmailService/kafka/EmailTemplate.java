package edu.miu.compro.cs544.CS544FinalProjectGroup8EmailService.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailTemplate {

   private String fromAddress;
   private String toAddress;
   private String title;
   private String message;
   
}
