package edu.miu.cs544.compro.backend.kafka;

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
