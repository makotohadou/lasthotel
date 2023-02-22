package com.cancun.lasthotel.reservation.misc;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class DateUtil {

    public static List<Date> getDaysBetweenDates(Date startdate, Date enddate)
    {
        List<Date> dates = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startdate);

        while (calendar.getTime().before(enddate))
        {
            Date result = calendar.getTime();
            dates.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }

    public static List<Date> getNextThirtyDays()
    {
        return getDaysBetweenDates(getTomorrow(),getThirtyDaysFromNow());
    }

    //TODO use mexico timezone?
    public static Date convertLocalDateTimeToDate(LocalDateTime input){
        return Date.from(input.toLocalDate().atStartOfDay(ZoneId.of("UTC")).toInstant());
    }

    public static Date getTomorrow(){
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
        return convertLocalDateTimeToDate(tomorrow);
    }

    public static Date getThirtyDaysFromNow(){
        LocalDateTime thirtyDaysFromNow = LocalDateTime.now().plusDays(30);
        return convertLocalDateTimeToDate(thirtyDaysFromNow);
    }


}
