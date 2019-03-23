package com.fatboyindustrial.gsonjavatime

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import org.hamcrest.Matchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import java.time.*

class KotlinConvertersTest {

    /**
     * Tests that serializing to JSON works.
     */
    @Test
    fun testSerialization() {
        val gson = GsonBuilder().registerAllJavaConverters().create()

        val container = Container()
        container.ld = LocalDate.of(1969, 7, 21)
        container.lt = LocalTime.of(12, 56, 0)
        container.ldt = LocalDateTime.of(container.ld, container.lt)
        container.odt = OffsetDateTime.of(container.ld, container.lt, ZoneOffset.ofHours(10))
        container.ot = OffsetTime.of(container.lt, ZoneOffset.ofHours(10))
        container.zdt = ZonedDateTime.of(container.ld, container.lt, ZoneId.of("Australia/Brisbane"))
        container.i = container.odt?.toInstant()

        val jsonString = gson.toJson(container)
        val json = gson.fromJson(jsonString, JsonObject::class.java).asJsonObject

        assertThat<String>(json.get("ld").asString, `is`<String>("1969-07-21"))
        assertThat<String>(json.get("lt").asString, `is`<String>("12:56:00"))
        assertThat<String>(json.get("ldt").asString, `is`<String>("1969-07-21T12:56:00"))
        assertThat<String>(json.get("odt").asString, `is`<String>("1969-07-21T12:56:00+10:00"))
        assertThat<String>(json.get("ot").asString, `is`<String>("12:56:00+10:00"))
        assertThat<String>(json.get("zdt").asString, `is`<String>("1969-07-21T12:56:00+10:00[Australia/Brisbane]"))
        assertThat<String>(json.get("i").asString, `is`<String>("1969-07-21T02:56:00Z"))
    }

    /**
     * Tests that deserializing from JSON works.
     */
    @Test
    fun testDeserialization() {
        val gson = Converters.registerAll(GsonBuilder()).create()

        val container = Container()
        container.ld = LocalDate.of(1969, 7, 21)
        container.lt = LocalTime.of(12, 56, 0)
        container.ldt = LocalDateTime.of(container.ld, container.lt)
        container.odt = OffsetDateTime.of(container.ld, container.lt, ZoneOffset.ofHours(10))
        container.ot = OffsetTime.of(container.lt, ZoneOffset.ofHours(10))
        container.zdt = ZonedDateTime.of(container.ld, container.lt, ZoneId.of("Australia/Brisbane"))
        container.i = container.odt?.toInstant()

        val serialized = JsonObject()
        serialized.add("ld", JsonPrimitive("1969-07-21"))
        serialized.add("lt", JsonPrimitive("12:56:00"))
        serialized.add("ldt", JsonPrimitive("1969-07-21T12:56:00"))
        serialized.add("odt", JsonPrimitive("1969-07-21T12:56:00+10:00"))
        serialized.add("ot", JsonPrimitive("12:56:00+10:00"))
        serialized.add("zdt", JsonPrimitive("1969-07-21T12:56:00+10:00[Australia/Brisbane]"))
        serialized.add("i", JsonPrimitive("1969-07-21T02:56:00Z"))

        val jsonString = gson.toJson(serialized)
        val deserialized = gson.fromJson<Container>(jsonString, Container::class.java)

        assertThat<LocalDate>(deserialized.ld, `is`<LocalDate>(container.ld))
        assertThat<LocalDateTime>(deserialized.ldt, `is`<LocalDateTime>(container.ldt))
        assertThat<LocalTime>(deserialized.lt, `is`<LocalTime>(container.lt))
        assertThat<OffsetDateTime>(deserialized.odt, `is`<OffsetDateTime>(container.odt))
        assertThat<OffsetTime>(deserialized.ot, `is`<OffsetTime>(container.ot))
        assertThat<ZonedDateTime>(deserialized.zdt, `is`<ZonedDateTime>(container.zdt))
        assertThat<Instant>(deserialized.i, `is`<Instant>(container.i))
    }

    /**
     * Container for serialising many fields.
     */
    private class Container {
        var ld: LocalDate? = null
        var ldt: LocalDateTime? = null
        var lt: LocalTime? = null
        var odt: OffsetDateTime? = null
        var ot: OffsetTime? = null
        var zdt: ZonedDateTime? = null
        var i: Instant? = null
    }
}