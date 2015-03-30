package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.Table;
import org.assertj.db.type.TimeValue;
import org.junit.Test;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link AssertionsOnColumnEquality} class :
 * {@link AssertionsOnColumnEquality#hasValuesEqualTo(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, java.util.List, org.assertj.db.type.DateValue...)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnColumnEquality_HasValuesEqualTo_DateValue_Test {

  /**
   * This method tests the {@code hasValuesEqualTo} assertion method.
   */
  @Test
  public void test_have_values_equal_to() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    List<Object> list = new ArrayList<Object>(Arrays.asList(Date.valueOf("2007-12-23"), Date.valueOf("2002-07-25"), null));
    TableAssert tableAssert2 = AssertionsOnColumnEquality.hasValuesEqualTo(tableAssert, info, list, DateValue.of(2007, 12, 23), DateValue.of(2002, 7, 25), null);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    list = new ArrayList<Object>(Arrays.asList(Timestamp.valueOf("2007-12-23 09:01:00"), Timestamp.valueOf("2002-07-25 03:30:05"), null));
    tableAssert2 = AssertionsOnColumnEquality.hasValuesEqualTo(tableAssert, info, list,
                                                               DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)),
                                                               DateTimeValue.of(DateValue.of(2002, 7, 25), TimeValue.of(3, 30, 5)), null);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the values are different.
   */
  @Test
  public void should_fail_because_values_are_different() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      List<Object> list = new ArrayList<Object>(Arrays.asList(Date.valueOf("2007-12-23"), Date.valueOf("2002-07-25")));
      AssertionsOnColumnEquality.hasValuesEqualTo(tableAssert, info, list, DateValue.of(2007, 12, 23), DateValue.of(2002, 7, 26));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <2002-07-25>\n"
                                                      + "to be equal to: \n"
                                                      + "  <2002-07-26>");
    }
    try {
      List<Object> list = new ArrayList<Object>(Arrays.asList(Timestamp.valueOf("2007-12-23 00:00:00"), Timestamp.valueOf("2002-07-25 00:00:05")));
      AssertionsOnColumnEquality.hasValuesEqualTo(tableAssert, info, list, DateValue.of(2007, 12, 23),
                                                  DateValue.of(2002, 7, 25));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <2002-07-25T00:00:05.000000000>\n"
                                                      + "to be equal to: \n"
                                                      + "  <2002-07-25>");
    }
  }

  /**
   * This method should fail because one of the values is not a date.
   */
  @Test
  public void should_fail_because_one_value_is_not_a_date() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    List<Object> list = new ArrayList<Object>(Arrays.asList(false, Date.valueOf("2002-07-25")));
    try {
      AssertionsOnColumnEquality.hasValuesEqualTo(tableAssert, info, list, DateValue.of(2007, 12, 23), DateValue.of(2002, 7, 25));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting that the value at index 0:\n"
                                                      + "  <false>\n"
                                                      + "to be of type\n"
                                                      + "  <[DATE, DATE_TIME, NOT_IDENTIFIED]>\n"
                                                      + "but was of type\n"
                                                      + "  <BOOLEAN>");
    }
  }

  /**
   * This method should fail because the number of values is different.
   */
  @Test
  public void should_fail_because_the_number_of_values_is_different() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    List<Object> list = new ArrayList<Object>(Arrays.asList(Date.valueOf("2007-12-23"), Date.valueOf("2002-07-25")));
    try {
      AssertionsOnColumnEquality.hasValuesEqualTo(tableAssert, info, list, DateValue.of(2007, 12, 23), DateValue.of(2002, 7, 25), DateValue.of(2015, 3, 30));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting size (number of rows) to be equal to :\n"
                                                      + "   <3>\n"
                                                      + "but was:\n"
                                                      + "   <2>");
    }
  }
}