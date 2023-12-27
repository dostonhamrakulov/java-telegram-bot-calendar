package uz.dosha.web;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static uz.dosha.web.InlineCalendarCommandUtil.CALENDAR_COMMAND_PREFIX;

/**
 * Inline calendar builder
 * @author Doston Hamrakulov
 */
public class InlineCalendarBuilder {

    /**
     * Week days: D, T, W, etc.
     */
    private String[] weekDays;

    public InlineCalendarBuilder() {
        this.weekDays = new String[]{"D", "S", "CH", "P", "J", "SH", "Y"};
    }

    public InlineKeyboardMarkup build() {
        final InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        final List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();

        // adding weeks in one row
        for (String weekDay: this.weekDays) {
            final InlineKeyboardButton in = new InlineKeyboardButton();
            in.setText(weekDay);
            in.setCallbackData(CALENDAR_COMMAND_PREFIX + weekDay);
            inlineKeyboardButtons.add(in);
        }

        rows.add(inlineKeyboardButtons);

        inlineKeyboardButtons = new ArrayList<>();

        LocalDate today = LocalDate.now();
        int weekDaysCounter = LocalDate.of(today.getYear(), today.getMonth(), 1).getDayOfWeek().getValue() - 1;

        // adding empty buttons
        for (int i = 0; i < weekDaysCounter; i++) {
            final InlineKeyboardButton in = new InlineKeyboardButton();
            in.setText(" ");
            in.setCallbackData(CALENDAR_COMMAND_PREFIX);
            inlineKeyboardButtons.add(in);
        }

        int daysOfCurrentMonth = YearMonth.now().lengthOfMonth();

        for (int i = 1; i <= daysOfCurrentMonth; i++) {
            final InlineKeyboardButton in = new InlineKeyboardButton();
            in.setText("" + i);
            in.setCallbackData(CALENDAR_COMMAND_PREFIX + i);
            inlineKeyboardButtons.add(in);
            weekDaysCounter += 1;

            if (weekDaysCounter == 7) {
                rows.add(inlineKeyboardButtons);

                inlineKeyboardButtons = new ArrayList<>();
                weekDaysCounter = 0;
            }
        }

        inlineKeyboardButtons = new ArrayList<>();

        InlineKeyboardButton in = new InlineKeyboardButton();
        in.setText("<<");
        in.setCallbackData(CALENDAR_COMMAND_PREFIX + "<<");
        inlineKeyboardButtons.add(in);

        in = new InlineKeyboardButton();
        in.setText(today.getMonth().name().substring(0, 3) + ", " + today.getYear());
        in.setCallbackData(CALENDAR_COMMAND_PREFIX + today.getMonth().name());
        inlineKeyboardButtons.add(in);

        in = new InlineKeyboardButton();
        in.setText(">>");
        in.setCallbackData(CALENDAR_COMMAND_PREFIX + ">>");
        inlineKeyboardButtons.add(in);
        rows.add(inlineKeyboardButtons);
        inlineKeyboardMarkup.setKeyboard(rows);

        return inlineKeyboardMarkup;
    }

    public String[] getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(String[] weekDays) {
        this.weekDays = weekDays;
    }
}
