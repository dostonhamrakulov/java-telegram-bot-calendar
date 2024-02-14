# java-telegram-bot-calendar
Date and time picker and inline calendar for based Java telegram bots. It is very simple to integrate and use in your bot.

Maven Dependency:
```xml
<dependency>
    <groupId>io.github.dostonhamrakulov</groupId>
    <artifactId>java-telegram-bot-calendar</artifactId>
    <version>2.3</version>
</dependency>
```

### Screens:

<p align="center">
	<img width="250px" height="450px" src="https://github.com/dostonhamrakulov/java-telegram-bot-calendar/tree/master/screenshots/screenshot_1.png" />
	<img width="250px" height="450px" src="https://github.com/dostonhamrakulov/java-telegram-bot-calendar/tree/master/screenshots/screenshot_2.png" />
</p>

In your bot:
```java
import io.github.dostonhamrakulov.InlineCalendarBuilder;
import io.github.dostonhamrakulov.InlineCalendarCommandUtil;

...
        
InlineCalendarBuilder inlineCalendarBuilder = new InlineCalendarBuilder(LanguageEnum.DE);

// enable showing full month name
inlineCalendarBuilder.setShowFullMonthName(true);

SendMessage sendMessage = new SendMessage();
sendMessage.setReplyMarkup(inlineCalendarBuilder.build(update));
...

// you can check if calendar/date picker is clicked        
if (InlineCalendarCommandUtil.isInlineCalendarClicked(update)){
    // if ignorable buttons are clicked like empty cells, cells for week days, etc.
    if (InlineCalendarCommandUtil.isCalendarIgnoreButtonClicked(update)) {
        return;
    }

    // return to the next or previous months
    if (InlineCalendarCommandUtil.isCalendarNavigationButtonClicked(update)) {
        sendMessage.setReplyMarkup(inlineCalendarBuilder.build(update));
        // execute
        return;
    }

    LocalDate localDate = InlineCalendarCommandUtil.extractDate(update);
}
```

### Available languages:
 * 🇺🇿 - Uzbek
 * 🇺🇸 - English
 * 🇩🇪 - German
 * 🇷🇺 - Russian

# How to contribute
Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are greatly appreciated.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/yourFeature`)
3. Commit your Changes (`git commit -m 'Add some yourFeature'`)
4. Push to the Branch (`git push origin feature/yourFeature`)
5. Open a Pull Request

# Authors
 * Doston Hamrakulov [@dostonhamrakulov](https://github.com/dostonhamrakulov/)

# License
This project is licensed under the MIT License - see the [LICENSE](https://github.com/dostonhamrakulov/java-telegram-bot-calendar/blob/main/LICENSE) file for details

# How to test
The following sample Java classes can be used in maven project to test this library:
```java
package io.github.dostonhamrakulov;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CalendarBot extends TelegramLongPollingBot {

    private Map<Long, Integer> chatAndMessageIdMap = new HashMap<>();

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    private static final InlineCalendarBuilder inlineCalendarBuilder = new InlineCalendarBuilder(LanguageEnum.DE);


    @Override
    public void onUpdateReceived(final Update update) {

        inlineCalendarBuilder.setShowFullMonthName(true);

        Message message = getMessage(update);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());

        if ("/start".equals(message.getText())) {
            sendMessage.setText("Calendar");
            sendMessage.setReplyMarkup(inlineCalendarBuilder.build(update));
            Message message1 = executeCommand(sendMessage);

            chatAndMessageIdMap.put(message.getChatId(), message1.getMessageId());

            return;
        }


        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(message.getChatId());
        editMessageText.setMessageId(chatAndMessageIdMap.get(message.getChatId()));

        if (InlineCalendarCommandUtil.isInlineCalendarClicked(update)) {
            if (InlineCalendarCommandUtil.isCalendarIgnoreButtonClicked(update)) {
                return;
            }

            if (InlineCalendarCommandUtil.isCalendarNavigationButtonClicked(update)) {
                editMessageText.setText("Selected date: ");
                editMessageText.setReplyMarkup(inlineCalendarBuilder.build(update));
                executeCommand(editMessageText);

                return;
            }

            LocalDate localDate = InlineCalendarCommandUtil.extractDate(update);
            sendMessage.setText("Selected date: " + DateTimeUtil.convertToString(localDate));
            executeCommand(sendMessage);
        }

        sendMessage.setText("Please, send /start command to the bot");
        executeCommand(sendMessage);
    }

    private Message executeCommand(SendMessage sendMessage) {
        try {
            return execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void executeCommand(EditMessageText editMessageText) {
        try {
            execute(editMessageText);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public Message getMessage(final Update update) {
        if (update.getMessage() != null) {
            return update.getMessage();
        }

        return update.getCallbackQuery().getMessage();
    }
}
```

and main class:
```java
package io.github.dostonhamrakulov;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new CalendarBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
```
