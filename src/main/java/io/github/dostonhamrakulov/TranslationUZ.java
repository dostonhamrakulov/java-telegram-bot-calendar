package io.github.dostonhamrakulov;

import java.time.Month;
import java.util.HashMap;
import java.util.Map;

public class TranslationUZ {

    public static final String[] weekDays = new String[] {"D", "S", "CH", "P", "J", "SH", "Y"};

    /**
     * Months for UZBEK language
     *
     * @return
     */
    public static Map<Month, String> getMonths() {
        final Map<Month, String> months = new HashMap<>();
        months.put(Month.JANUARY, "Yanvar");
        months.put(Month.FEBRUARY, "Fevral");
        months.put(Month.MARCH, "Mart");
        months.put(Month.APRIL, "April");
        months.put(Month.MAY, "May");
        months.put(Month.JUNE, "Iyun");
        months.put(Month.JULY, "Iyul");
        months.put(Month.AUGUST, "Avgust");
        months.put(Month.SEPTEMBER, "Sentyabr");
        months.put(Month.OCTOBER, "Oktyabr");
        months.put(Month.NOVEMBER, "Noyabr");
        months.put(Month.DECEMBER, "Dekabr");

        return months;
    }

}
