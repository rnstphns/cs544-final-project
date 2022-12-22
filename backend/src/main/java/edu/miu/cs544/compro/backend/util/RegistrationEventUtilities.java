package edu.miu.cs544.compro.backend.util;

import edu.miu.cs544.compro.backend.domain.RegistrationEvent;

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
