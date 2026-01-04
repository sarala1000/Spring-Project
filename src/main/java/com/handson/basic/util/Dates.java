package com.handson.basic.util;

import org.springframework.lang.Nullable;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

public class Dates {
    public static TimeZone TIME_ZONE = TimeZone.getTimeZone("Asia/Jerusalem");
    public static ZoneId TIME_ZONE_ID = ZoneId.of("Asia/Jerusalem");

    public Dates() {
    }

    public static String dateToStr(@Nullable LocalDate date) {
        return date == null ? null : date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static Date atUtc(LocalDateTime date) {
        return atUtc(date, TIME_ZONE);
    }

    public static Date atUtc(LocalDateTime date, TimeZone zone) {
        if (date == null) return null;
        ZoneId zoneId = zone.toZoneId();
        ZonedDateTime zonedDateTime = date.atZone(zoneId);
        return Date.from(zonedDateTime.toInstant());
    }

    public static Date atUtc(@Nullable LocalDate date) {
        return atUtc(date, TIME_ZONE);
    }

    public static Date atUtc(@Nullable LocalDate date, TimeZone zone) {
        return date == null ? null : atUtc(date.atStartOfDay(), zone);
    }

    public static LocalDateTime atLocalTime(Date date) {
        return atLocalTime(date, TIME_ZONE);
    }

    public static LocalDateTime atLocalTime(Date date, TimeZone zone) {
        if (date == null) return null;
        ZoneId zoneId = zone.toZoneId();
        return date.toInstant().atZone(zoneId).toLocalDateTime();
    }

    public static Date nowUTC() {
        return Date.from(Instant.now());
    }

    public static String getFullDateTime() {
        return Instant.now().toString();
    }

    public static boolean equals(@Nullable Date date1, @Nullable Date date2) {
        if (date1 != null && date2 != null) {
            return date1.getTime() == date2.getTime();
        } else {
            return Objects.equals(date1, date2);
        }
    }
}