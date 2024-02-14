package com.conference.calender.utility;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Utility {

    public static boolean checkDateRange(LocalDateTime date){
        LocalDateTime blockedDateRangeStart = LocalDateTime.of(date.toLocalDate(), LocalTime.of(12,01,00));
        LocalDateTime blockedDateRangeEnd = LocalDateTime.of(date.toLocalDate(), LocalTime.of(12,59,00));

        if(date.isBefore(blockedDateRangeStart) || date.isAfter(blockedDateRangeEnd)){
            return true;
        }

        return false;
    }
}
