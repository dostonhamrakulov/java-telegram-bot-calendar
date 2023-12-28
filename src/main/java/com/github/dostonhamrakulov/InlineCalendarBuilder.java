package com.github.dostonhamrakulov;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.dostonhamrakulov.InlineCalendarCommandUtil.CALENDAR_COMMAND_DATE;
import static com.github.dostonhamrakulov.InlineCalendarCommandUtil.CALENDAR_COMMAND_IGNORE;
import static com.github.dostonhamrakulov.InlineCalendarCommandUtil.CALENDAR_COMMAND_NAVIGATION;
import static com.github.dostonhamrakulov.InlineCalendarCommandUtil.CALENDAR_COMMAND_PREFIX;

/**
 * Inline calendar builder
 * @author Doston Hamrakulov
 */
public class InlineCalendarBuilder {

    /**
     * Week days: D, T, W, etc.
     */
    private String[] weekDays;

    /**
     * Translated month names
     */
    private Map<Month, String> months;

    /**
     * Flag to show the full month name
     */
    private boolean showFullMonthName;

    public InlineCalendarBuilder() {
        this.weekDays = new String[]{"D", "S", "CH", "P", "J", "SH", "Y"};
        this.months = new HashMap<>();
        this.months.put(Month.JANUARY, "Yanvar");
        this.months.put(Month.FEBRUARY, "Fevral");
        this.months.put(Month.MARCH, "Mart");
        this.months.put(Month.APRIL, "April");
        this.months.put(Month.MAY, "May");
        this.months.put(Month.JUNE, "Iyun");
        this.months.put(Month.JULY, "Iyul");
        this.months.put(Month.AUGUST, "Avgust");
        this.months.put(Month.SEPTEMBER, "Sentyabr");
        this.months.put(Month.OCTOBER, "Oktyabr");
        this.months.put(Month.NOVEMBER, "Noyabr");
        this.months.put(Month.DECEMBER, "Dekabr");
        this.showFullMonthName = false;
    }

    public synchronized InlineKeyboardMarkup build(final Update update) {

        LocalDate dateForCalendar = InlineCalendarCommandUtil.extractNavigationDate(update);

        if (dateForCalendar == null) {
            dateForCalendar = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1);
        }

        final InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        final List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();

        // adding weeks in one row
        for (final String weekDay: getWeekDays()) {
            final InlineKeyboardButton in = new InlineKeyboardButton();
            in.setText(weekDay);
            in.setCallbackData(CALENDAR_COMMAND_PREFIX + CALENDAR_COMMAND_IGNORE);
            inlineKeyboardButtons.add(in);
        }

        rows.add(inlineKeyboardButtons);

        inlineKeyboardButtons = new ArrayList<>();

        int weekDaysCounter = LocalDate.of(dateForCalendar.getYear(), dateForCalendar.getMonth(), 1).getDayOfWeek().getValue() - 1;

        // adding empty buttons
        for (int i = 0; i < weekDaysCounter; i++) {
            final InlineKeyboardButton in = new InlineKeyboardButton();
            in.setText(" ");
            in.setCallbackData(CALENDAR_COMMAND_PREFIX + CALENDAR_COMMAND_IGNORE);
            inlineKeyboardButtons.add(in);
        }

        final int daysOfCurrentMonth = YearMonth.of(dateForCalendar.getYear(), dateForCalendar.getMonth()).lengthOfMonth();
        int remainingEmptyDays = 0;

        for (int i = 1; i <= daysOfCurrentMonth; i++) {
            final InlineKeyboardButton in = new InlineKeyboardButton();
            in.setText("" + i);
            in.setCallbackData(CALENDAR_COMMAND_PREFIX + CALENDAR_COMMAND_DATE + DateTimeUtil.convertToString(LocalDate.of(dateForCalendar.getYear(), dateForCalendar.getMonth(), i)));
            inlineKeyboardButtons.add(in);
            weekDaysCounter += 1;

            if (weekDaysCounter == 7) {
                rows.add(inlineKeyboardButtons);

                inlineKeyboardButtons = new ArrayList<>();
                weekDaysCounter = 0;
            } else if (i == daysOfCurrentMonth) {
                remainingEmptyDays = 7 - weekDaysCounter;
            }
        }

        // adding empty buttons
        for (int i = 0; i < remainingEmptyDays; i++) {
            final InlineKeyboardButton in = new InlineKeyboardButton();
            in.setText(" ");
            in.setCallbackData(CALENDAR_COMMAND_PREFIX + CALENDAR_COMMAND_IGNORE);
            inlineKeyboardButtons.add(in);
        }

        if (remainingEmptyDays > 0) {
            rows.add(inlineKeyboardButtons);
        }

        inlineKeyboardButtons = new ArrayList<>();

        InlineKeyboardButton in = new InlineKeyboardButton();
        in.setText("<<");
        in.setCallbackData(CALENDAR_COMMAND_PREFIX + CALENDAR_COMMAND_NAVIGATION + DateTimeUtil.convertToString(dateForCalendar.minusMonths(1)));
        inlineKeyboardButtons.add(in);

        in = new InlineKeyboardButton();

        if (showFullMonthName) {
            in.setText(this.months.get(dateForCalendar.getMonth()) + ", " + dateForCalendar.getYear());
        } else {
            in.setText(this.months.get(dateForCalendar.getMonth()).substring(0, 3) + ", " + dateForCalendar.getYear());
        }

        in.setCallbackData(CALENDAR_COMMAND_PREFIX + CALENDAR_COMMAND_IGNORE + dateForCalendar.getMonth().name());
        inlineKeyboardButtons.add(in);

        in = new InlineKeyboardButton();
        in.setText(">>");
        in.setCallbackData(CALENDAR_COMMAND_PREFIX + CALENDAR_COMMAND_NAVIGATION + DateTimeUtil.convertToString(dateForCalendar.plusMonths(1)));
        inlineKeyboardButtons.add(in);
        rows.add(inlineKeyboardButtons);
        inlineKeyboardMarkup.setKeyboard(rows);

        return inlineKeyboardMarkup;
    }

    public String[] getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(final String[] weekDays) {
        this.weekDays = weekDays;
    }

    public Map<Month, String> getMonths() {
        return months;
    }

    public void setMonths(final Map<Month, String> months) {
        this.months = months;
    }

    public void setShowFullMonthName(boolean showFullMonthName) {
        this.showFullMonthName = showFullMonthName;
    }
}
