package com.fatboyindustrial.gsonjavatime;

import org.junit.Test;

import java.time.Instant;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test for {@code https://github.com/gkopff/gson-javatime-serialisers/issues/20}.
 */
public class Issue20Test
{
  /**
   * Tests that the serialisation produces ISO format strings.
   */
  @Test
  public void test()
  {
    final Instant instant = Instant.parse("2017-06-08T22:11:28.566Z");

    final Issue20 issue = new Issue20(instant);
    final String expected =
        "{\"date\":\"2017-06-08T22:11:28.566Z\"," +
        "\"date2\":\"2017-06-08T22:11:28.566\"," +
        "\"date3\":\"2017-06-08\"," +
        "\"date4\":\"2017-06-08T00:00:00Z\"}";

    assertThat(issue.toJSON(), is(expected));
  }
}
