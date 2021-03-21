package dev.jonium.mason.annotations;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used internally to mark and control the serialization and deserialization of general collections in Mason
 */
@JsonSetter(nulls = Nulls.AS_EMPTY)
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CollectionType {

    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface Setter {

    }

    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface Getter {

    }

}
