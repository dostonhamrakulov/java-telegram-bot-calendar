package uz.dosha.web;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link DateTimeUtil}
 */
public class DateTimeUtilTest {

    /**
     * Testing {@link DateTimeUtil#convertToString(LocalDate)}
     */
    @Test
    void testConvertToString() {
        assertEquals("25-09-2023", DateTimeUtil.convertToString(LocalDate.of(2023, Month.SEPTEMBER, 25)));
        assertEquals("01-04-2023", DateTimeUtil.convertToString(LocalDate.of(2023, Month.APRIL, 1)));
        assertEquals("15-09-2023", DateTimeUtil.convertToString(LocalDate.of(2023, Month.SEPTEMBER, 15)));
    }

    /**
     * Testing {@link DateTimeUtil#convertToDate(String)}
     */
    @Test
    void testConvertToDate() {
        assertEquals(DateTimeUtil.convertToString(LocalDate.of(2023, Month.SEPTEMBER, 25)), "25-09-2023");
        assertEquals(DateTimeUtil.convertToString(LocalDate.of(2023, Month.APRIL, 1)), "01-04-2023");
        assertEquals(DateTimeUtil.convertToString(LocalDate.of(2023, Month.SEPTEMBER, 15)), "15-09-2023");
    }
}
