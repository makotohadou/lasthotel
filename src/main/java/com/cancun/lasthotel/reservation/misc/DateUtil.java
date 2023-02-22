package com.cancun.lasthotel.reservation.misc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DateUtil {

    public static List<LocalDate> getDaysBetweenDates(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> dates =  Stream.iterate(startDate, date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(startDate, endDate))
                .collect(Collectors.toList());
        dates.add(endDate);
        return dates;
    }

    public static List<LocalDate> getNextThirtyDays() {
        return getDaysBetweenDates(getTomorrow(), getThirtyDaysFromNow());
    }

    public static LocalDate convertLocalDateTimeToLocalDate(LocalDateTime input) {
        return input.toLocalDate();
    }

    public static LocalDate getTomorrow() {
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
        return convertLocalDateTimeToLocalDate(tomorrow);
    }

    public static LocalDate getThirtyDaysFromNow() {
        LocalDateTime thirtyDaysFromNow = LocalDateTime.now().plusDays(30);
        return convertLocalDateTimeToLocalDate(thirtyDaysFromNow);
    }

}
