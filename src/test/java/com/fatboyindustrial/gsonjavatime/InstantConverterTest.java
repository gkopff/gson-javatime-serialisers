package com.fatboyindustrial.gsonjavatime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;

import org.junit.Test;

import java.time.Instant;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class InstantConverterTest
{
  
  private final Gson gson = new GsonBuilder().registerTypeAdapter(Instant.class, new InstantConverter()).create();

  @Test
  public void should_serialise_to_iso_instant() throws Exception
  {
    Instant now = Instant.now();
    String toJson = gson.toJson(now);
    
    assertThat(toJson, equalTo("\"" + now.toString() + "\""));
  }
  
  @Test
  public void should_deserialise_from_iso_instant() throws Exception
  {
    Instant now = Instant.now();
    Instant fromJson = gson.fromJson(new JsonPrimitive(now.toString()), Instant.class);
    
    assertThat(fromJson, equalTo(now));
  }
}
