package com.stadistic.infostatisticsms.utils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

public final class StatisticsUtils {
    private StatisticsUtils() {
    }

    public static Boolean isOverEighteen(Timestamp birthDate) {
        Calendar birthCalendar = Calendar.getInstance();
        birthCalendar.setTimeInMillis(birthDate.getTime());

        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.add(Calendar.DAY_OF_YEAR, 1);
        currentCalendar.add(Calendar.YEAR, -18);

        return birthCalendar.before(currentCalendar);
    }


}
