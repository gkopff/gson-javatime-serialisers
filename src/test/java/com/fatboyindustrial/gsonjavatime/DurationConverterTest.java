/*
 * Copyright 2014-2022 Greg Kopff
 * All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.fatboyindustrial.gsonjavatime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.lang.reflect.Type;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link DurationConverter}.
 */
public class DurationConverterTest
{
  /** The specific genericized type for {@code Duration}. */
  private static final Type DURATION_TYPE = new TypeToken<Duration>() {}.getType();

  /**
   * Tests that serialising to JSON works.
   */
  @Test
  public void testSerialisation()
  {
    final Gson gson = registerDuration(new GsonBuilder()).create();

    final Duration duration = Duration.of(100, ChronoUnit.SECONDS);
    final String json = gson.toJson(duration, Duration.class);

    assertThat(json, is("\"PT1M40S\""));
  }

  /**
   * Tests that deserialising from JSON works.
   */
  @Test
  public void testDeserialisation()
  {
    final Gson gson = registerDuration(new GsonBuilder()).create();

    final String json = "\"PT1M40S\"";
    final Duration duration = gson.fromJson(json, Duration.class);

    assertThat(duration, is(Duration.of(100, ChronoUnit.SECONDS)));
  }

  /**
   * Tests that deserialising from JSON works with an empty value.
   */
  @Test
  public void testDeserialisationWithEmptyValue()
  {
    final Gson gson = registerDuration(new GsonBuilder()).create();

    final String json = "\"\"";
    final Duration duration = gson.fromJson(json, Duration.class);

    assertNull(duration);
  }

  /**
   * Tests that deserialising from JSON containing whitespace raises the expected exception.
   */
  @Test(expected = DateTimeException.class)
  public void testDeserialisationWithWhitespace()
  {
    final Gson gson = registerDuration(new GsonBuilder()).create();

    final String json = "\" \"";
    gson.fromJson(json, Duration.class);
  }

  /**
   * Registers the {@link DurationConverter} converter.
   * @param builder The GSON builder to register the converter with.
   * @return A reference to {@code builder}.
   */
  private static GsonBuilder registerDuration(GsonBuilder builder)
  {
    builder.registerTypeAdapter(DURATION_TYPE, new DurationConverter());

    return builder;
  }
}
