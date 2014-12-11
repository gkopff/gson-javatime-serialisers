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

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZonedDateTime;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link Converters}.
 */
public class ConvertersTest
{
  /**
   * Tests that the {@link Converters#registerAll} method registers the converters successfully.
   */
  @Test
  public void testRegisterAll()
  {
    final Gson gson = Converters.registerAll(new GsonBuilder()).create();
    final Container original = new Container();
    original.ld = LocalDate.now();
    original.ldt = LocalDateTime.now();
    original.lt = LocalTime.now();
    original.odt = OffsetDateTime.now();
    original.ot = OffsetTime.now();
    original.zdt = ZonedDateTime.now();
    original.i = Instant.now();

    final Container reconstituted = gson.fromJson(gson.toJson(original), Container.class);

    assertThat(reconstituted.ld, is(original.ld));
    assertThat(reconstituted.ldt, is(original.ldt));
    assertThat(reconstituted.lt, is(original.lt));
    assertThat(reconstituted.odt, is(original.odt));
    assertThat(reconstituted.ot, is(original.ot));
    assertThat(reconstituted.zdt, is(original.zdt));
    assertThat(reconstituted.i, is(original.i));
  }

  /**
   * Container for serialising many fields.
   */
  private static class Container
  {
    private LocalDate ld;
    private LocalDateTime ldt;
    private LocalTime lt;
    private OffsetDateTime odt;
    private OffsetTime ot;
    private ZonedDateTime zdt;
    private Instant i;
  }
}
