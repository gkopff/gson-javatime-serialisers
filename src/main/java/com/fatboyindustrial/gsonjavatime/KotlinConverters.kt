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

package com.fatboyindustrial.gsonjavatime

import com.google.gson.GsonBuilder
import java.time.*

/**
 * Registers all Java Time converters onto the receiver.
 *
 * @see Converters.registerAll
 */
fun GsonBuilder.registerAllJavaConverters(): GsonBuilder = this
    .registerLocalDate()
    .registerLocalDateTime()
    .registerLocalTime()
    .registerOffsetDateTime()
    .registerOffsetTime()
    .registerZonedDateTime()
    .registerInstant()

/**
 * Registers the [LocalDate] converter onto the receiver.
 *
 * @see Converters.registerLocalDate
 */
fun GsonBuilder.registerLocalDate(): GsonBuilder = Converters.registerLocalDate(this)

/**
 * Registers the [LocalDateTime] converter onto the receiver.
 *
 * @see Converters.registerLocalDateTime
 */
fun GsonBuilder.registerLocalDateTime(): GsonBuilder = Converters.registerLocalDateTime(this)

/**
 * Registers the [LocalTime] converter onto the receiver.
 *
 * @see Converters.registerLocalTime
 */
fun GsonBuilder.registerLocalTime(): GsonBuilder = Converters.registerLocalTime(this)

/**
 * Registers the [OffsetDateTime\] converter onto the receiver.
 *
 * @see Converters.registerOffsetDateTime
 */
fun GsonBuilder.registerOffsetDateTime(): GsonBuilder = Converters.registerOffsetDateTime(this)

/**
 * Registers the [OffsetTime] converter onto the receiver.
 *
 * @see Converters.registerOffsetTime
 */
fun GsonBuilder.registerOffsetTime(): GsonBuilder = Converters.registerOffsetTime(this)

/**
 * Registers the [ZonedDateTime] converter onto the receiver.
 *
 * @see Converters.registerZonedDateTime
 */
fun GsonBuilder.registerZonedDateTime(): GsonBuilder = Converters.registerZonedDateTime(this)

/**
 * Registers the [Instant] converter onto the receiver.
 *
 * @see Converters.registerInstant
 */
fun GsonBuilder.registerInstant(): GsonBuilder = Converters.registerInstant(this)