package edu.miu.cs544.backend.util;

import edu.miu.cs544.backend.domain.RegistrationEvent;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.chrono.ChronoLocalDate;

public class RegistrationEventUtilities {
//    public static boolean isOpen(RegistrationEvent currentEvent){
//        LocalDate now = LocalDate.now();
//        if(now.isAfter(ChronoLocalDate.from(currentEvent.getStartDate().atStartOfDay()).minus(Period.ofDays(1))) &&
//                now.isBefore(ChronoLocalDate.from(currentEvent.getEndDate().atTime(LocalTime.parse("11:59pm")))))
//            return true;
//        else return false;
//    }
    //TODO turned off real isOpen check for testing purposes
    public static boolean isOpen(RegistrationEvent currentEvent){
        return true;
    }
}
