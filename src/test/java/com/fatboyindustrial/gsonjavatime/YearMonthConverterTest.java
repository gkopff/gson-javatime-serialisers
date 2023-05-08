package com.fatboyindustrial.gsonjavatime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.lang.reflect.Type;
import java.time.YearMonth;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class YearMonthConverterTest {

    /** The specific genericized type for {@code YearMonth}. */
    private static final Type YEAR_MONTH_TYPE = TypeToken.get(YearMonth.class).getType();

    /**
     * Tests that serialising to JSON works.
     */
    @Test
    public void testSerialisation(){
        final Gson gson = registerYearMonth(new GsonBuilder()).create();

        final YearMonth yearMonth = YearMonth.of(2020, 11);
        final String json = gson.toJson(yearMonth, YearMonth.class);

        assertThat(json, is("\"2020-11\""));
    }

    /**
     * Tests that deserialising from JSON works.
     */
    @Test
    public void testDeserialisation(){
        final Gson gson = registerYearMonth(new GsonBuilder()).create();

        final String json = "\"2020-11\"";
        final YearMonth yearMonth = gson.fromJson(json, YearMonth.class);

        assertThat(yearMonth, is(YearMonth.of(2020, 11)));
    }

    /**
     * Registers the {@link YearMonthConverter} converter.
     * @param builder The GSON builder to register the converter with.
     * @return A reference to {@code builder}.
     */
    private static GsonBuilder registerYearMonth(GsonBuilder builder){
        builder.registerTypeAdapter(YEAR_MONTH_TYPE, new YearMonthConverter());

        return builder;
    }
}