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
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link Converters}.
 */
public class ConvertersTest
{
  /**
   * Tests that serialising to JSON works.
   */
  @Test
  public void testSerialisation() throws Exception
  {
    final Gson gson = Converters.registerAll(new GsonBuilder()).create();

    final Container container = new Container();
    container.ld = LocalDate.of(1969, 7, 21);
    container.lt = LocalTime.of(12, 56, 0);
    container.ldt = LocalDateTime.of(container.ld, container.lt);
    container.odt = OffsetDateTime.of(container.ld, container.lt, ZoneOffset.ofHours(10));
    container.ot = OffsetTime.of(container.lt, ZoneOffset.ofHours(10));
    container.zdt = ZonedDateTime.of(container.ld, container.lt, ZoneId.of("Australia/Brisbane"));
    container.i = container.odt.toInstant();

    final String jsonString = gson.toJson(container);
    final JsonObject json = gson.fromJson(jsonString, JsonObject.class).getAsJsonObject();

    assertThat(json.get("ld").getAsString(), is("1969-07-21"));
    assertThat(json.get("lt").getAsString(), is("12:56:00"));
    assertThat(json.get("ldt").getAsString(), is("1969-07-21T12:56:00"));
    assertThat(json.get("odt").getAsString(), is("1969-07-21T12:56:00+10:00"));
    assertThat(json.get("ot").getAsString(), is("12:56:00+10:00"));
    assertThat(json.get("zdt").getAsString(), is("1969-07-21T12:56:00+10:00[Australia/Brisbane]"));
    assertThat(json.get("i").getAsString(), is("1969-07-21T02:56:00Z"));
  }

  /**
   * Tests that deserialising from JSON works.
   */
  @Test
  public void testDeserialisation() throws Exception
  {
    final Gson gson = Converters.registerAll(new GsonBuilder()).create();

    final Container container = new Container();
    container.ld = LocalDate.of(1969, 7, 21);
    container.lt = LocalTime.of(12, 56, 0);
    container.ldt = LocalDateTime.of(container.ld, container.lt);
    container.odt = OffsetDateTime.of(container.ld, container.lt, ZoneOffset.ofHours(10));
    container.ot = OffsetTime.of(container.lt, ZoneOffset.ofHours(10));
    container.zdt = ZonedDateTime.of(container.ld, container.lt, ZoneId.of("Australia/Brisbane"));
    container.i = container.odt.toInstant();
    
    final JsonObject serialized = new JsonObject();
    serialized.add("ld", new JsonPrimitive("1969-07-21"));
    serialized.add("lt", new JsonPrimitive("12:56:00"));
    serialized.add("ldt", new JsonPrimitive("1969-07-21T12:56:00"));
    serialized.add("odt", new JsonPrimitive("1969-07-21T12:56:00+10:00"));
    serialized.add("ot", new JsonPrimitive("12:56:00+10:00"));
    serialized.add("zdt", new JsonPrimitive("1969-07-21T12:56:00+10:00[Australia/Brisbane]"));
    serialized.add("i", new JsonPrimitive("1969-07-21T02:56:00Z"));

    final String jsonString = gson.toJson(serialized);
    final Container deserialised = gson.fromJson(jsonString, Container.class);
    
    assertThat(deserialised.ld, is(container.ld));
    assertThat(deserialised.ldt, is(container.ldt));
    assertThat(deserialised.lt, is(container.lt));
    assertThat(deserialised.odt, is(container.odt));
    assertThat(deserialised.ot, is(container.ot));
    assertThat(deserialised.zdt, is(container.zdt));
    assertThat(deserialised.i, is(container.i));
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
