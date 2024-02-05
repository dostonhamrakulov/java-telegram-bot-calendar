package io.github.dostonhamrakulov;

import java.time.Month;
import java.util.HashMap;
import java.util.Map;

public class TranslationEN {

    /**
     * First letters of week days
     */
    public static final String[] weekDays = new String[] {"M", "T", "W", "TH", "F", "S", "S"};

    /**
     * Months for English language
     * @return
     */
    public static Map<Month, String> getMonths() {
        final Map<Month, String> months = new HashMap<>();
        months.put(Month.JANUARY, "January");
        months.put(Month.FEBRUARY, "February");
        months.put(Month.MARCH, "March");
        months.put(Month.APRIL, "April");
        months.put(Month.MAY, "May");
        months.put(Month.JUNE, "June");
        months.put(Month.JULY, "July");
        months.put(Month.AUGUST, "August");
        months.put(Month.SEPTEMBER, "September");
        months.put(Month.OCTOBER, "October");
        months.put(Month.NOVEMBER, "November");
        months.put(Month.DECEMBER, "December");

        return months;
    }

}
