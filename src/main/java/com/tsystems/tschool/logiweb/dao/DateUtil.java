package com.tsystems.tschool.logiweb.dao;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static Date getFirstDayOfCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Date result = calendar.getTime();
        calendar.setTime(new Date());

        return result;
    }

    public static Date getFirstDayOfNextMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());  //today

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.MONTH, 1);

        Date result = calendar.getTime();
        calendar.setTime(new Date());

        return result;
    }

    public static float diffInHours(Date begin, Date finish) {
        long resultMills = begin.getTime() - finish.getTime();
        return (float) resultMills / 1000 / 60 / 60;
    }

    public static float getHoursUntilEndOfMonth() {
        return diffInHours(new Date(), getFirstDayOfNextMonth());
    }
}
