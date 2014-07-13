package org.assertj.db.type;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Time;
import java.text.ParseException;

import org.assertj.db.common.AbstractTest;
import org.junit.Test;

/**
 * Tests on the time value.
 * 
 * @author Régis Pouiller
 * 
 */
public class TimeValue_Test extends AbstractTest {

  /**
   * This method tests the constructor with a {@code String} containing nanoseconds.
   */
  @Test
  public void test_contructor_with_string_containing_nanoseconds() throws ParseException {
    TimeValue timeValue = new TimeValue("09:01:06.000000003");
    assertThat(timeValue.getHour()).isEqualTo(9);
    assertThat(timeValue.getMinutes()).isEqualTo(1);
    assertThat(timeValue.getSeconds()).isEqualTo(6);
    assertThat(timeValue.getNanoSeconds()).isEqualTo(3);
  }

  /**
   * This method tests the constructor with a {@code String} containing seconds.
   */
  @Test
  public void test_contructor_with_string_containing_seconds() throws ParseException {
    TimeValue timeValue = new TimeValue("09:01:06");
    assertThat(timeValue.getHour()).isEqualTo(9);
    assertThat(timeValue.getMinutes()).isEqualTo(1);
    assertThat(timeValue.getSeconds()).isEqualTo(6);
    assertThat(timeValue.getNanoSeconds()).isEqualTo(0);
  }

  /**
   * This method tests the constructor with a {@code String} containing minutes.
   */
  @Test
  public void test_contructor_with_string_containing_minutes() throws ParseException {
    TimeValue timeValue = new TimeValue("09:01");
    assertThat(timeValue.getHour()).isEqualTo(9);
    assertThat(timeValue.getMinutes()).isEqualTo(1);
    assertThat(timeValue.getSeconds()).isEqualTo(0);
    assertThat(timeValue.getNanoSeconds()).isEqualTo(0);
  }

  /**
   * This method should throw a {@code NullPointerException} because passing a {@code null} parameter to constructor
   * with a {@code String}.
   */
  @Test(expected = NullPointerException.class)
  public void should_constructor_with_string_fail_if_date_is_null() throws ParseException {
    new TimeValue((String) null);
  }

  /**
   * This method should throw a {@code ParseException} because passing a parameter to constructor with a bad length.
   */
  @Test(expected = ParseException.class)
  public void should_constructor_with_string_fail_if_date_have_bad_length() throws ParseException {
    new TimeValue("12345678901");
  }

  /**
   * This method should throw a {@code ParseException} because passing a parameter to constructor with a bad character on year.
   */
  @Test(expected = ParseException.class)
  public void should_constructor_with_string_fail_if_date_have_bad_character_on_year() throws ParseException {
    new TimeValue("a9:01");
  }

  /**
   * This method should throw a {@code ParseException} because passing a parameter to constructor with a bad character on separator.
   */
  @Test(expected = ParseException.class)
  public void should_constructor_with_string_fail_if_date_have_bad_character_on_separator() throws ParseException {
    new TimeValue("09a01");
  }

  /**
   * This method tests the constructor with a {@code Time} containing seconds.
   */
  @Test
  public void test_contructor_with_time_containing_seconds() throws ParseException {
    TimeValue timeValue = new TimeValue(Time.valueOf("09:01:06"));
    assertThat(timeValue.getHour()).isEqualTo(9);
    assertThat(timeValue.getMinutes()).isEqualTo(1);
    assertThat(timeValue.getSeconds()).isEqualTo(6);
    assertThat(timeValue.getNanoSeconds()).isEqualTo(0);
  }

  /**
   * This method should throw a {@code NullPointerException} because passing a {@code null} parameter to constructor
   * with a {@code Time}.
   */
  @Test(expected = NullPointerException.class)
  public void should_constructor_with_time_fail_if_date_is_null() throws ParseException {
    new TimeValue((String) null);
  }

  /**
   * This method tests the constructor with values (4 {@code int}).
   */
  @Test
  public void test_contructor_with_four_values() throws ParseException {
    TimeValue timeValue = new TimeValue(9, 1, 6, 3);
    assertThat(timeValue.getHour()).isEqualTo(9);
    assertThat(timeValue.getMinutes()).isEqualTo(1);
    assertThat(timeValue.getSeconds()).isEqualTo(6);
    assertThat(timeValue.getNanoSeconds()).isEqualTo(3);
  }

  /**
   * This method tests the constructor with values (3 {@code int}).
   */
  @Test
  public void test_contructor_with_three_values() throws ParseException {
    TimeValue timeValue = new TimeValue(9, 1, 6);
    assertThat(timeValue.getHour()).isEqualTo(9);
    assertThat(timeValue.getMinutes()).isEqualTo(1);
    assertThat(timeValue.getSeconds()).isEqualTo(6);
    assertThat(timeValue.getNanoSeconds()).isEqualTo(0);
  }

  /**
   * This method tests the constructor with values (2 {@code int}).
   */
  @Test
  public void test_contructor_with_two_values() throws ParseException {
    TimeValue timeValue = new TimeValue(9, 1);
    assertThat(timeValue.getHour()).isEqualTo(9);
    assertThat(timeValue.getMinutes()).isEqualTo(1);
    assertThat(timeValue.getSeconds()).isEqualTo(0);
    assertThat(timeValue.getNanoSeconds()).isEqualTo(0);
  }

  /**
   * This method tests the {@code parse} static method with a {@code String} containing nanoseconds.
   */
  @Test
  public void test_parse_containing_nanoseconds() throws ParseException {
    TimeValue timeValue = TimeValue.parse("09:01:06.000000003");
    assertThat(timeValue.getHour()).isEqualTo(9);
    assertThat(timeValue.getMinutes()).isEqualTo(1);
    assertThat(timeValue.getSeconds()).isEqualTo(6);
    assertThat(timeValue.getNanoSeconds()).isEqualTo(3);
  }

  /**
   * This method tests the {@code parse} static method with a {@code String} containing seconds.
   */
  @Test
  public void test_parse_containing_seconds() throws ParseException {
    TimeValue timeValue = TimeValue.parse("09:01:06");
    assertThat(timeValue.getHour()).isEqualTo(9);
    assertThat(timeValue.getMinutes()).isEqualTo(1);
    assertThat(timeValue.getSeconds()).isEqualTo(6);
    assertThat(timeValue.getNanoSeconds()).isEqualTo(0);
  }

  /**
   * This method tests the {@code parse} static method with a {@code String} containing minutes.
   */
  @Test
  public void test_parse_containing_minutes() throws ParseException {
    TimeValue timeValue = TimeValue.parse("09:01");
    assertThat(timeValue.getHour()).isEqualTo(9);
    assertThat(timeValue.getMinutes()).isEqualTo(1);
    assertThat(timeValue.getSeconds()).isEqualTo(0);
    assertThat(timeValue.getNanoSeconds()).isEqualTo(0);
  }

  /**
   * This method should throw a {@code NullPointerException} because passing a {@code null} parameter to {@code parse} static method
   * with a {@code String}.
   */
  @Test(expected = NullPointerException.class)
  public void should_parse_fail_if_date_is_null() throws ParseException {
    TimeValue.parse(null);
  }

  /**
   * This method should throw a {@code ParseException} because passing a parameter to {@code parse} static method with a bad length.
   */
  @Test(expected = ParseException.class)
  public void should_parse_fail_if_date_have_bad_length() throws ParseException {
    TimeValue.parse("12345678901");
  }

  /**
   * This method should throw a {@code ParseException} because passing a parameter to {@code parse} static method with a bad character on year.
   */
  @Test(expected = ParseException.class)
  public void should_parse_fail_if_date_have_bad_character_on_year() throws ParseException {
    TimeValue.parse("a9:01");
  }

  /**
   * This method should throw a {@code ParseException} because passing a parameter to {@code parse} static method with a bad character on separator.
   */
  @Test(expected = ParseException.class)
  public void should_parse_fail_if_date_have_bad_character_on_separator() throws ParseException {
    TimeValue.parse("09a01");
  }

  /**
   * This method tests the {@code from} method containing seconds.
   */
  @Test
  public void test_from_containing_seconds() throws ParseException {
    TimeValue timeValue = TimeValue.from(Time.valueOf("09:01:06"));
    assertThat(timeValue.getHour()).isEqualTo(9);
    assertThat(timeValue.getMinutes()).isEqualTo(1);
    assertThat(timeValue.getSeconds()).isEqualTo(6);
    assertThat(timeValue.getNanoSeconds()).isEqualTo(0);
  }

  /**
   * This method should throw a {@code NullPointerException} because passing a {@code null} parameter to {@code from} method.
   */
  @Test(expected = NullPointerException.class)
  public void should_from_fail_if_date_is_null() throws ParseException {
    TimeValue.from(null);
  }

  /**
   * This method tests the {@code of} static method with four values.
   */
  @Test
  public void test_of_with_four_values() throws ParseException {
    TimeValue timeValue = TimeValue.of(9, 1, 6, 3);
    assertThat(timeValue.getHour()).isEqualTo(9);
    assertThat(timeValue.getMinutes()).isEqualTo(1);
    assertThat(timeValue.getSeconds()).isEqualTo(6);
    assertThat(timeValue.getNanoSeconds()).isEqualTo(3);
  }

  /**
   * This method tests the {@code of} static method with three values.
   */
  @Test
  public void test_of_with_three_values() throws ParseException {
    TimeValue timeValue = TimeValue.of(9, 1, 6);
    assertThat(timeValue.getHour()).isEqualTo(9);
    assertThat(timeValue.getMinutes()).isEqualTo(1);
    assertThat(timeValue.getSeconds()).isEqualTo(6);
    assertThat(timeValue.getNanoSeconds()).isEqualTo(0);
  }

  /**
   * This method tests the {@code of} static method with two values.
   */
  @Test
  public void test_of_with_two_values() throws ParseException {
    TimeValue timeValue = TimeValue.of(9, 1);
    assertThat(timeValue.getHour()).isEqualTo(9);
    assertThat(timeValue.getMinutes()).isEqualTo(1);
    assertThat(timeValue.getSeconds()).isEqualTo(0);
    assertThat(timeValue.getNanoSeconds()).isEqualTo(0);
  }

  /**
   * This method tests the {@code toString} method.
   */
  @Test
  public void test_toString() {
    assertThat(TimeValue.of(9, 1, 6, 3).toString()).isEqualTo("09:01:06.000000003");
  }

  /**
   * This method tests the {@code equals} method.
   */
  @Test
  public void test_equals() {
    assertThat(TimeValue.of(9, 1).equals(TimeValue.of(9, 1))).isTrue();
    assertThat(TimeValue.of(9, 1, 6).equals(TimeValue.of(9, 1, 6))).isTrue();
    assertThat(TimeValue.of(9, 1, 6, 3).equals(TimeValue.of(9, 1, 6, 3))).isTrue();

    assertThat(TimeValue.of(9, 1).equals(TimeValue.of(10, 1))).isFalse();
    assertThat(TimeValue.of(9, 1).equals(TimeValue.of(9, 2))).isFalse();
    assertThat(TimeValue.of(9, 1, 6).equals(TimeValue.of(9, 1, 7))).isFalse();
    assertThat(TimeValue.of(9, 1, 6, 3).equals(TimeValue.of(9, 1, 6, 4))).isFalse();
    assertThat(TimeValue.of(9, 1, 6, 3).equals("")).isFalse();
  }

  /**
   * This method tests the {@code compareTo} method.
   */
  @Test
  public void test_compareTo() {
    assertThat(TimeValue.of(9, 1).compareTo(TimeValue.of(9, 0))).isEqualTo(1);
    assertThat(TimeValue.of(9, 1).compareTo(TimeValue.of(8, 1))).isEqualTo(1);
    assertThat(TimeValue.of(9, 1, 6).compareTo(TimeValue.of(9, 1, 5))).isEqualTo(1);
    assertThat(TimeValue.of(9, 1, 6).compareTo(TimeValue.of(9, 0, 6))).isEqualTo(1);
    assertThat(TimeValue.of(9, 1, 6).compareTo(TimeValue.of(8, 1, 6))).isEqualTo(1);
    assertThat(TimeValue.of(9, 1, 6, 3).compareTo(TimeValue.of(9, 1, 6, 2))).isEqualTo(1);
    assertThat(TimeValue.of(9, 1, 6, 3).compareTo(TimeValue.of(9, 1, 5, 3))).isEqualTo(1);
    assertThat(TimeValue.of(9, 1, 6, 3).compareTo(TimeValue.of(9, 0, 6, 3))).isEqualTo(1);
    assertThat(TimeValue.of(9, 1, 6, 3).compareTo(TimeValue.of(8, 1, 6, 3))).isEqualTo(1);

    assertThat(TimeValue.of(9, 1).compareTo(TimeValue.of(9, 1))).isEqualTo(0);
    assertThat(TimeValue.of(9, 1, 6).compareTo(TimeValue.of(9, 1, 6))).isEqualTo(0);
    assertThat(TimeValue.of(9, 1, 6, 3).compareTo(TimeValue.of(9, 1, 6, 3))).isEqualTo(0);

    assertThat(TimeValue.of(9, 1).compareTo(TimeValue.of(9, 2))).isEqualTo(-1);
    assertThat(TimeValue.of(9, 1).compareTo(TimeValue.of(10, 1))).isEqualTo(-1);
    assertThat(TimeValue.of(9, 1, 6).compareTo(TimeValue.of(9, 1, 7))).isEqualTo(-1);
    assertThat(TimeValue.of(9, 1, 6).compareTo(TimeValue.of(9, 2, 6))).isEqualTo(-1);
    assertThat(TimeValue.of(9, 1, 6).compareTo(TimeValue.of(10, 1, 6))).isEqualTo(-1);
    assertThat(TimeValue.of(9, 1, 6, 3).compareTo(TimeValue.of(9, 1, 6, 4))).isEqualTo(-1);
    assertThat(TimeValue.of(9, 1, 6, 3).compareTo(TimeValue.of(9, 1, 7, 3))).isEqualTo(-1);
    assertThat(TimeValue.of(9, 1, 6, 3).compareTo(TimeValue.of(9, 2, 6, 3))).isEqualTo(-1);
    assertThat(TimeValue.of(9, 1, 6, 3).compareTo(TimeValue.of(10, 1, 6, 3))).isEqualTo(-1);
  }

  /**
   * This method tests the {@code isBefore} method.
   */
  @Test
  public void test_isBefore() {
    assertThat(TimeValue.of(9, 1).isBefore(TimeValue.of(9, 0))).isFalse();
    assertThat(TimeValue.of(9, 1).isBefore(TimeValue.of(8, 1))).isFalse();
    assertThat(TimeValue.of(9, 1, 6).isBefore(TimeValue.of(9, 1, 5))).isFalse();
    assertThat(TimeValue.of(9, 1, 6).isBefore(TimeValue.of(9, 0, 6))).isFalse();
    assertThat(TimeValue.of(9, 1, 6).isBefore(TimeValue.of(8, 1, 6))).isFalse();
    assertThat(TimeValue.of(9, 1, 6, 3).isBefore(TimeValue.of(9, 1, 6, 2))).isFalse();
    assertThat(TimeValue.of(9, 1, 6, 3).isBefore(TimeValue.of(9, 1, 5, 3))).isFalse();
    assertThat(TimeValue.of(9, 1, 6, 3).isBefore(TimeValue.of(9, 0, 6, 3))).isFalse();
    assertThat(TimeValue.of(9, 1, 6, 3).isBefore(TimeValue.of(8, 1, 6, 3))).isFalse();

    assertThat(TimeValue.of(9, 1).isBefore(TimeValue.of(9, 1))).isFalse();
    assertThat(TimeValue.of(9, 1, 6).isBefore(TimeValue.of(9, 1, 6))).isFalse();
    assertThat(TimeValue.of(9, 1, 6, 3).isBefore(TimeValue.of(9, 1, 6, 3))).isFalse();

    assertThat(TimeValue.of(9, 1).isBefore(TimeValue.of(9, 2))).isTrue();
    assertThat(TimeValue.of(9, 1).isBefore(TimeValue.of(10, 1))).isTrue();
    assertThat(TimeValue.of(9, 1, 6).isBefore(TimeValue.of(9, 1, 7))).isTrue();
    assertThat(TimeValue.of(9, 1, 6).isBefore(TimeValue.of(9, 2, 6))).isTrue();
    assertThat(TimeValue.of(9, 1, 6).isBefore(TimeValue.of(10, 1, 6))).isTrue();
    assertThat(TimeValue.of(9, 1, 6, 3).isBefore(TimeValue.of(9, 1, 6, 4))).isTrue();
    assertThat(TimeValue.of(9, 1, 6, 3).isBefore(TimeValue.of(9, 1, 7, 3))).isTrue();
    assertThat(TimeValue.of(9, 1, 6, 3).isBefore(TimeValue.of(9, 2, 6, 3))).isTrue();
    assertThat(TimeValue.of(9, 1, 6, 3).isBefore(TimeValue.of(10, 1, 6, 3))).isTrue();
  }

  /**
   * This method tests the {@code isAfter} method.
   */
  @Test
  public void test_isAfter() {
    assertThat(TimeValue.of(9, 1).isAfter(TimeValue.of(9, 0))).isTrue();
    assertThat(TimeValue.of(9, 1).isAfter(TimeValue.of(8, 1))).isTrue();
    assertThat(TimeValue.of(9, 1, 6).isAfter(TimeValue.of(9, 1, 5))).isTrue();
    assertThat(TimeValue.of(9, 1, 6).isAfter(TimeValue.of(9, 0, 6))).isTrue();
    assertThat(TimeValue.of(9, 1, 6).isAfter(TimeValue.of(8, 1, 6))).isTrue();
    assertThat(TimeValue.of(9, 1, 6, 3).isAfter(TimeValue.of(9, 1, 6, 2))).isTrue();
    assertThat(TimeValue.of(9, 1, 6, 3).isAfter(TimeValue.of(9, 1, 5, 3))).isTrue();
    assertThat(TimeValue.of(9, 1, 6, 3).isAfter(TimeValue.of(9, 0, 6, 3))).isTrue();
    assertThat(TimeValue.of(9, 1, 6, 3).isAfter(TimeValue.of(8, 1, 6, 3))).isTrue();

    assertThat(TimeValue.of(9, 1).isAfter(TimeValue.of(9, 1))).isFalse();
    assertThat(TimeValue.of(9, 1, 6).isAfter(TimeValue.of(9, 1, 6))).isFalse();
    assertThat(TimeValue.of(9, 1, 6, 3).isAfter(TimeValue.of(9, 1, 6, 3))).isFalse();

    assertThat(TimeValue.of(9, 1).isAfter(TimeValue.of(9, 2))).isFalse();
    assertThat(TimeValue.of(9, 1).isAfter(TimeValue.of(10, 1))).isFalse();
    assertThat(TimeValue.of(9, 1, 6).isAfter(TimeValue.of(9, 1, 7))).isFalse();
    assertThat(TimeValue.of(9, 1, 6).isAfter(TimeValue.of(9, 2, 6))).isFalse();
    assertThat(TimeValue.of(9, 1, 6).isAfter(TimeValue.of(10, 1, 6))).isFalse();
    assertThat(TimeValue.of(9, 1, 6, 3).isAfter(TimeValue.of(9, 1, 6, 4))).isFalse();
    assertThat(TimeValue.of(9, 1, 6, 3).isAfter(TimeValue.of(9, 1, 7, 3))).isFalse();
    assertThat(TimeValue.of(9, 1, 6, 3).isAfter(TimeValue.of(9, 2, 6, 3))).isFalse();
    assertThat(TimeValue.of(9, 1, 6, 3).isAfter(TimeValue.of(10, 1, 6, 3))).isFalse();
  }
}
