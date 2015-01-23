/*
 * Copyright 2014 Greg Kopff
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
import java.time.LocalTime;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link LocalTimeConverter}.
 */
public class LocalTimeConverterTest
{
  /** The specific genericized type for {@code LocalTime}. */
  private static final Type LOCAL_TIME_TYPE = new TypeToken<LocalTime>(){}.getType();

  /**
   * Tests that serialising to JSON works.
   */
  @Test
  public void testSerialisation() throws Exception
  {
    final Gson gson = registerLocalTime(new GsonBuilder()).create();

    final LocalTime localTime = LocalTime.parse("12:56:00");
    final String json = gson.toJson(localTime);

    assertThat(json, is("\"12:56:00\""));
  }

  /**
   * Tests that deserialising from JSON works.
   */
  @Test
  public void testDeserialisation() throws Exception
  {
    final Gson gson = registerLocalTime(new GsonBuilder()).create();

    final String json = "\"12:56:00\"";
    final LocalTime localTime = gson.fromJson(json, LocalTime.class);

    assertThat(localTime, is(LocalTime.parse("12:56:00")));
  }

  /**
   * Registers the {@link LocalTimeConverter} converter.
   * @param builder The GSON builder to register the converter with.
   * @return A reference to {@code builder}.
   */
  private static GsonBuilder registerLocalTime(GsonBuilder builder)
  {
    builder.registerTypeAdapter(LOCAL_TIME_TYPE, new LocalTimeConverter());

    return builder;
  }
}
