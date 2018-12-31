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
import java.time.ZoneId;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link ZoneIdConverter}.
 */
public class ZoneIdConverterTest {

    /** The specific genericized type for {@code ZoneId}. */
    private static final Type ZONE_ID_TYPE = new TypeToken<ZoneId>(){}.getType();

    /**
     * Tests that serialising to JSON works.
     */
    @Test
    public void testSerialisation() throws Exception
    {
        final Gson gson = registerZoneId(new GsonBuilder()).create();

        final ZoneId zoneId = ZoneId.of("Australia/Brisbane");
        final String json= gson.toJson(zoneId, ZoneId.class);
        assertThat(json, is("\"Australia/Brisbane\""));
    }

    /**
     * Tests that deserialising from JSON works.
     */
    @Test
    public void testDeserialisation() throws Exception
    {
        final Gson gson = registerZoneId(new GsonBuilder()).create();

        final String json = "\"Australia/Brisbane\"";
        final ZoneId zoneId = gson.fromJson(json, ZoneId.class);

        assertThat(zoneId, is(ZoneId.of("Australia/Brisbane")));
    }

    /**
     * Registers the {@link ZoneIdConverter} converter.
     * @param builder The GSON builder to register the converter with.
     * @return A reference to {@code builder}.
     */
    private static GsonBuilder registerZoneId(GsonBuilder builder)
    {
        builder.registerTypeAdapter(ZONE_ID_TYPE, new ZoneIdConverter());

        return builder;
    }
}
