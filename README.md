# gson-javatime-serialisers

## What is it?

A set of [GSON][1] serialiser/deserialisers for dealing with [Java 8 `java.time` entities][2].  Wherever possible, [ISO 8601 string representations](http://en.wikipedia.org/wiki/ISO_8601) are used.

## Getting it

````
<dependency>
  <groupId>com.fatboyindustrial.gson-javatime-serialisers</groupId>
  <artifactId>gson-javatime-serialisers</artifactId>
  <version>1.1.0</version>
</dependency>
````

## Using it

````
final Gson gson = Converters.registerOffsetDateTime(new GsonBuilder()).create();
final OffsetDateTime original = OffsetDateTime.now();

final String json = gson.toJson(original);
final OffsetDateTime reconstituted = gson.fromJson(json, OffsetDateTime.class);
````

## Testing

Unrelated to `gson-javatime-serialisers` itself, but if you're working with Java 8 time, you may be interested in [`spencerwi/hamcrest-jdk8-time`][3]


[1]: https://code.google.com/p/google-gson/
[2]: http://docs.oracle.com/javase/8/docs/api/java/time/package-summary.html
[3]: https://github.com/spencerwi/hamcrest-jdk8-time
