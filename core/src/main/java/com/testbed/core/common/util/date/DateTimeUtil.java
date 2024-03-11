package com.testbed.core.common.util.date;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeUtil {

    public static final int SEC = 60;
    public static final int MIN = 60;
    public static final int HOUR = 24;
    public static final int DAY = 30;
    public static final int MONTH = 12;

    public static LocalDateTime getNowDateTimeSecond() {
        LocalDateTime now = LocalDateTime.now();
        return LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), now.getHour(), now.getMinute(), now.getSecond());
    }

    public static LocalDateTime getDateMinTime(String date) {
        return LocalDateTime.of(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd")), LocalTime.MIN);
    }

    public static LocalDateTime getDateMaxTime(String date) {
        return LocalDateTime.of(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd")), LocalTime.MAX);
    }

    public static LocalDate convertStringToDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    public static LocalDateTime convertStringToDateTime(String date) {
//        DateTimeFormatter val = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        //LocalDateTime val2 = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//        System.out.println(val);
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    public static String txtDate(Date tempDate) {
        long curTime = System.currentTimeMillis();

        long regTime = tempDate.getTime();

        long diffTime = (curTime - regTime) / 1000;

        String msg = null;

        if (diffTime < SEC){
            msg = diffTime + "초전";
        } else if ((diffTime /= SEC) < MIN) {
            msg = diffTime + "분 전";
        } else if ((diffTime /= MIN) < HOUR) {
            msg = (diffTime) + "시간 전";
        } else if ((diffTime /= HOUR) < DAY) {
            msg = (diffTime) + "일 전";
        } else if ((diffTime /= DAY) < MONTH) {
            msg = (diffTime) + "개월 전";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat( "yyyy");
            String curYear = sdf.format(curTime);
            String passYear = sdf.format(tempDate);
            int diffYear = Integer.parseInt(curYear) - Integer.parseInt(passYear);
            msg = diffYear + "년 전";
        }

        return msg;
    }
}
