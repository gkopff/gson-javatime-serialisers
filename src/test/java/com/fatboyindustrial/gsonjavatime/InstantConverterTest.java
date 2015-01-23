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
import java.time.Instant;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link InstantConverter}.
 */
public class InstantConverterTest
{
  /** The specific genericized type for {@code LocalDate}. */
  private static final Type INSTANT_TYPE = new TypeToken<Instant>(){}.getType();

  /**
   * Tests that serialising to JSON works.
   */
  @Test
  public void testSerialisation() throws Exception
  {
    final Gson gson = registerInstant(new GsonBuilder()).create();

    final Instant now = Instant.parse("1969-07-21T02:56:00Z");
    final String json = gson.toJson(now);

    assertThat(json, is("\"1969-07-21T02:56:00Z\""));
  }

  /**
   * Tests that deserialising from JSON works.
   */
  @Test
  public void testDeserialisation() throws Exception
  {
    final Gson gson = registerInstant(new GsonBuilder()).create();

    final String json = "\"1969-07-21T02:56:00Z\"";
    final Instant instant = gson.fromJson(json, Instant.class);

    assertThat(instant, is(Instant.parse("1969-07-21T02:56:00Z")));
  }

  /**
   * Registers the {@link InstantConverter} converter.
   * @param builder The GSON builder to register the converter with.
   * @return A reference to {@code builder}.
   */
  private static GsonBuilder registerInstant(GsonBuilder builder)
  {
    builder.registerTypeAdapter(INSTANT_TYPE, new InstantConverter());

    return builder;
  }
}
