package com.github.dostonhamrakulov;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    private static final String DAY_MONTH_YEAR_PATTERN = "dd-MM-yyyy";

    private DateTimeUtil() {
    }

    public static String convertToString(final LocalDate localDate) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DAY_MONTH_YEAR_PATTERN);
        return localDate.format(formatter);
    }

    public static LocalDate convertToDate(final String dateAsText) {
        return LocalDate.parse(dateAsText, DateTimeFormatter.ofPattern(DAY_MONTH_YEAR_PATTERN));
    }
}
