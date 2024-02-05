package io.github.dostonhamrakulov;

import java.time.Month;
import java.util.HashMap;
import java.util.Map;

public class TranslationRU {

    /**
     * First letters of week days
     */
    public static final String[] weekDays = new String[]{"П", "В", "С", "Ч", "П", "С", "В"};

    /**
     * Months for Russian language
     * @return
     */
    public static Map<Month, String> getMonths() {
        final Map<Month, String> months = new HashMap<>();
        months.put(Month.JANUARY, "Январь");
        months.put(Month.FEBRUARY, "Февраль");
        months.put(Month.MARCH, "Март");
        months.put(Month.APRIL, "Апрель");
        months.put(Month.MAY, "Май");
        months.put(Month.JUNE, "Июнь");
        months.put(Month.JULY, "Июль");
        months.put(Month.AUGUST, "Август");
        months.put(Month.SEPTEMBER, "Сентябрь");
        months.put(Month.OCTOBER, "Октябрь");
        months.put(Month.NOVEMBER, "Ноябрь");
        months.put(Month.DECEMBER, "Декабрь");

        return months;
    }

}
