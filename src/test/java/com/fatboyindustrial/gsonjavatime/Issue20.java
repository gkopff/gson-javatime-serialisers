package com.fatboyindustrial.gsonjavatime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

/**
 * Test case from {@code https://github.com/gkopff/gson-javatime-serialisers/issues/20}.
 */
@SuppressWarnings({ "FieldCanBeLocal", "unused" })         // used reflectively
public class Issue20
{
  private static final Gson gson = Converters.registerAll(new GsonBuilder()).create();

  private final Instant date;
  private final LocalDateTime date2;
  private final LocalDate date3;
  private final Instant date4;

  public Issue20(final Instant date)
  {
    this.date = date;
    this.date2 = LocalDateTime.ofInstant(date, ZoneId.of("UTC"));
    this.date3 = this.date2.toLocalDate();
    this.date4 = this.date3.atStartOfDay().toInstant(ZoneOffset.UTC);
  }

  public String toJSON()
  {
    return gson.toJson(this);
  }
}
