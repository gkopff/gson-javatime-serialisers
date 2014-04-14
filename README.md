# gson-javatime-serialisers

## What is it?

A set of [GSON][1] serialiser/deserialisers for dealing with [Java 8 `java.time` entities][1].  Wherever possible, [ISO 8601 string representations](http://en.wikipedia.org/wiki/ISO_8601) are used.

## Getting it

````
<dependency>
  <groupId>com.fatboyindustrial.gson-javatime-serialisers</groupId>
  <artifactId>gson-javatime-serialisers</artifactId>
  <version>1.0.0</version>
</dependency>
````

## Using it

````
final Gson gson = Converters.registerOffsetDateTime(new GsonBuilder()).create();
final OffsetDateTime original = OffsetDateTime.now();

final String json = gson.toJson(original);
final OffsetDateTime reconstituted = gson.fromJson(json, OffsetDateTime.class);
````





[1]: https://code.google.com/p/google-gson/
[2]: http://docs.oracle.com/javase/8/docs/api/java/time/package-summary.html
