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
import java.time.OffsetTime;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link OffsetTimeConverter}.
 */
public class OffsetTimeConverterTest
{
  /** The specific genericized type for {@code OffsetTime}. */
  private static final Type OFFSET_TIME_TYPE = new TypeToken<OffsetTime>(){}.getType();

  /**
   * Tests that the {@link OffsetTime} can be round-tripped.
   */
  @Test
  public void testRoundTrip()
  {
    final Gson gson = registerOffsetTime(new GsonBuilder()).create();
    final OffsetTime ot = OffsetTime.now();

    assertThat(gson.fromJson(gson.toJson(ot), OffsetTime.class), is(ot));
  }

  /**
   * Registers the {@link OffsetTimeConverter} converter.
   * @param builder The GSON builder to register the converter with.
   * @return A reference to {@code builder}.
   */
  private static GsonBuilder registerOffsetTime(GsonBuilder builder)
  {
    builder.registerTypeAdapter(OFFSET_TIME_TYPE, new OffsetTimeConverter());

    return builder;
  }
}
