package io.github.dostonhamrakulov;

import java.time.Month;
import java.util.HashMap;
import java.util.Map;

public class TranslationDE {

    /**
     * First letters of week days
     */
    public static final String[] weekDays = new String[] {"M", "D", "M", "D", "F", "S", "S"};

    /**
     * Months for German language
     *
     * @return
     */
    public static Map<Month, String> getMonths() {
        final Map<Month, String> months = new HashMap<>();
        months.put(Month.JANUARY, "Januar");
        months.put(Month.FEBRUARY, "Februar");
        months.put(Month.MARCH, "März");
        months.put(Month.APRIL, "April");
        months.put(Month.MAY, "Mai");
        months.put(Month.JUNE, "Juni");
        months.put(Month.JULY, "Juli");
        months.put(Month.AUGUST, "August");
        months.put(Month.SEPTEMBER, "September");
        months.put(Month.OCTOBER, "Oktober");
        months.put(Month.NOVEMBER, "November");
        months.put(Month.DECEMBER, "Dezember");

        return months;
    }

}
