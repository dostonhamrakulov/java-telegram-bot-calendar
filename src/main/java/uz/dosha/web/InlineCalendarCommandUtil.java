package uz.dosha.web;

import org.telegram.telegrambots.meta.api.objects.Update;

public class InlineCalendarCommandUtil {

    public static final String CALENDAR_COMMAND_PREFIX = "CAL_CM_";

    public static boolean isInlineCalendarClicked(final Update update) {
        return update.getCallbackQuery() != null
               && update.getCallbackQuery().getData() != null
               && update.getCallbackQuery().getData().startsWith(CALENDAR_COMMAND_PREFIX);
    }
}
