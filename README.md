# java-telegram-bot-calendar
Date and time picker and inline calendar for based Java telegram bots. It is very simple to integrate and use in your bot.

Maven Dependency:
```xml
<dependency>
    <groupId>io.github.dostonhamrakulov</groupId>
    <artifactId>java-telegram-bot-calendar</artifactId>
    <version>1.0</version>
</dependency>
```

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
