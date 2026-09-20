package com.momo.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public final class Dates {
    private static final DateTimeFormatter USER_FORMAT = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
    private static final DateTimeFormatter ISO_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE;

    private Dates() {
    }

    public static LocalDate parseUserDate(String value) {
        return LocalDate.parse(value, USER_FORMAT);
    }

    public static String formatUserDate(LocalDate date) {
        return USER_FORMAT.format(date);
    }

    public static LocalDate parseIso(String value) {
        return LocalDate.parse(value, ISO_FORMAT);
    }

    public static String formatIso(LocalDate date) {
        return ISO_FORMAT.format(date);
    }
}
