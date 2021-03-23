# Java-Mason

Implements serialization support for the [Mason+Json](https://github.com/JornWildt/Mason) standard with
[Jackson](https://github.com/FasterXML/jackson) and pure Java 11.

Implements a simple wrapper class that will add Mason support to any existing Jackson serializable object.

If you use this and notice some issues, please add an issue here on github.

## Notes

#### RFC3339 Dates

The Mason standard uses RFC3339 dates as times, but there is no support for all the features in Java, so
a [RFC3339DateUtils](src/main/java/dev/jonium/mason/serialization/RFC3339DateUtils.java) class was made to try to
provide support for some special cases of them.

This class is an 'attempt' at supporting the special cases of them, but no guarantee that it handles all of them.

Thus, all the dates a are deserialized as strings, and their reading is left for the user.

#### Implementation

All the serialization is implemented in interfaces that should serialize nicely, no matter the implementation.

However, if the implementing class changes the Jackson annotations present, no support is guaranteed.

Using the implemented [Simple](src/main/java/dev/jonium/mason/impl) classes should work on all kinds of Mason.

#### Tests

The tests cover all features available and all output seems to be valid.

The tests rely on parsing manually created sample inputs to verify deserialization, and validating serialized structure
types so that they conform with the standard.

Still needs some more work on tests. Any help from someone familiar with good unit test would be helpful.

## Requires

* Java 11
* [Jackson](https://github.com/FasterXML/jackson) for serialization
* [Lombok](https://projectlombok.org/) for code generation
* [Jakarta Validation](https://mvnrepository.com/artifact/jakarta.validation) for validation annotations
* [Junit5](https://junit.org/junit5/) for testing
