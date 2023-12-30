package io.github.dostonhamrakulov;

import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDate;

public class InlineCalendarCommandUtil {

    public static final String CALENDAR_COMMAND_PREFIX = "CAL_CM:";
    public static final String CALENDAR_COMMAND_DATE = "DATE:";
    public static final String CALENDAR_COMMAND_IGNORE = "IGNORE";
    public static final String CALENDAR_COMMAND_NAVIGATION = "NAVIGATE:";

    public static boolean isInlineCalendarClicked(final Update update) {
        return update.getCallbackQuery() != null
               && update.getCallbackQuery().getData() != null
               && update.getCallbackQuery().getData().startsWith(CALENDAR_COMMAND_PREFIX);
    }

    public static boolean isDateSelected(final Update update) {
        return update.getCallbackQuery() != null
               && update.getCallbackQuery().getData() != null
               && update.getCallbackQuery().getData().startsWith(CALENDAR_COMMAND_PREFIX + CALENDAR_COMMAND_DATE);
    }

    public static boolean isCalendarIgnoreButtonClicked(final Update update) {
        return update.getCallbackQuery() != null
               && update.getCallbackQuery().getData() != null
               && update.getCallbackQuery().getData().startsWith(CALENDAR_COMMAND_PREFIX + CALENDAR_COMMAND_IGNORE);
    }

    public static boolean isCalendarNavigationButtonClicked(final Update update) {
        return update.getCallbackQuery() != null
               && update.getCallbackQuery().getData() != null
               && update.getCallbackQuery().getData().startsWith(CALENDAR_COMMAND_PREFIX + CALENDAR_COMMAND_NAVIGATION);
    }

    public static LocalDate extractDate(final Update update) {
        return DateTimeUtil.convertToDate(update.getCallbackQuery().getData().replace(CALENDAR_COMMAND_PREFIX + CALENDAR_COMMAND_DATE, ""));
    }

    public static LocalDate extractNavigationDate(final Update update) {
        if (!isCalendarNavigationButtonClicked(update)) {
            return null;
        }

        return DateTimeUtil.convertToDate(update.getCallbackQuery().getData().replace(CALENDAR_COMMAND_PREFIX + CALENDAR_COMMAND_NAVIGATION, ""));
    }
}
