package com.nftgram.batch.common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

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
}
