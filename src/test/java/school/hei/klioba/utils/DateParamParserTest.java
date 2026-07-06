package school.hei.klioba.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import org.junit.jupiter.api.Test;

class DateParamParserTest {

  private static final ZoneId ZONE = ZoneId.of("UTC+3");

  @Test
  void parseDate_null_returnsNull() {
    assertNull(DateParamParser.parseDate(null));
  }

  @Test
  void parseDate_validDate_returnsInstant() {
    var result = DateParamParser.parseDate("2024-01-15");
    var expected = LocalDate.parse("2024-01-15").atStartOfDay(ZONE).toInstant();
    assertEquals(expected, result);
  }

  @Test
  void parseDate_emptyString_throwsException() {
    assertThrows(DateTimeException.class, () -> DateParamParser.parseDate(""));
  }

  @Test
  void parseDate_invalidFormat_throwsException() {
    assertThrows(DateTimeException.class, () -> DateParamParser.parseDate("2024/01/15"));
  }

  @Test
  void parseDate_invalidDate_throwsException() {
    assertThrows(DateTimeException.class, () -> DateParamParser.parseDate("2024-13-01"));
  }

  @Test
  void parseDateEnd_null_returnsNull() {
    assertNull(DateParamParser.parseDateEnd(null));
  }

  @Test
  void parseDateEnd_validDate_returnsNextDayInstant() {
    var result = DateParamParser.parseDateEnd("2024-01-15");
    var expected = LocalDate.parse("2024-01-15").plusDays(1).atStartOfDay(ZONE).toInstant();
    assertEquals(expected, result);
  }

  @Test
  void parseDateEnd_dateAtMonthEnd_returnsFirstOfNextMonth() {
    var result = DateParamParser.parseDateEnd("2024-01-31");
    var expected = LocalDate.parse("2024-01-31").plusDays(1).atStartOfDay(ZONE).toInstant();
    assertEquals(expected, result);
  }

  @Test
  void parseDateEnd_emptyString_throwsException() {
    assertThrows(DateTimeException.class, () -> DateParamParser.parseDateEnd(""));
  }

  @Test
  void parseDate_returnsStartOfDayInUtcPlus3() {
    var result = DateParamParser.parseDate("2024-06-15");
    var expected = Instant.parse("2024-06-14T21:00:00Z");
    assertEquals(expected, result);
  }

  @Test
  void parseDateEnd_returnsMidnightInUtcPlus3() {
    var result = DateParamParser.parseDateEnd("2024-06-15");
    var expected = Instant.parse("2024-06-15T21:00:00Z");
    assertEquals(expected, result);
  }
}
