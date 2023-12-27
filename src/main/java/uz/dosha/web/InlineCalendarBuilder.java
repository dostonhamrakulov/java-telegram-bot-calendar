package uz.dosha.web;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class InlineCalendarBuilder {

    private String[] weekDays;

    public InlineCalendarBuilder() {
        this.weekDays = new String[]{"D", "S", "CH", "P", "J", "SH", "Y"};
    }

    public InlineKeyboardMarkup build() {
        final InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        final List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();



        for (String weekDay: this.weekDays) {
            final InlineKeyboardButton in = new InlineKeyboardButton();
            in.setText(weekDay);
            in.setCallbackData(weekDay);
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
            in.setCallbackData(" ");
            inlineKeyboardButtons.add(in);
        }

        int daysOfCurrentMonth = YearMonth.now().lengthOfMonth();

        for (int i = 1; i <= daysOfCurrentMonth; i++) {
            final InlineKeyboardButton in = new InlineKeyboardButton();
            in.setText("" + i);
            in.setCallbackData("" + i);
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
        in.setCallbackData("<<");
        inlineKeyboardButtons.add(in);

        in = new InlineKeyboardButton();
        in.setText(today.getMonth().name().substring(0, 3) + ", " + today.getYear());
        in.setCallbackData(today.getMonth().name());
        inlineKeyboardButtons.add(in);

        in = new InlineKeyboardButton();
        in.setText(">>");
        in.setCallbackData(">>");
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
