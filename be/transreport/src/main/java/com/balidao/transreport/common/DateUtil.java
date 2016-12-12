package com.balidao.transreport.common;

import java.util.Date;

import org.joda.time.LocalDateTime;

public class DateUtil {

    public static Date getDateFromLocalDateTime(LocalDateTime localDateTime) {
        return localDateTime == null ? null : localDateTime.toDate();
    }
    
    public static LocalDateTime getLocalDateTimeFromDate(Date date) {
        return date == null ? null : LocalDateTime.fromDateFields(date);
    }
}
