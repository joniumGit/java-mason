package dev.jonium.mason.annotations;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.LinkedHashSet;

/**
 * Annotation used internally to mark and control the serialization and deserialization of Arrays in Mason
 * <p>
 * All of the arrays this is used on should be arrays that can't contain duplicates as this will deserialize them
 * as a {@link LinkedHashSet}.
 * </p>
 */
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
@JsonSetter(nulls = Nulls.AS_EMPTY)
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ArrayType {

    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface Setter {

    }

    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    @JsonDeserialize(as = LinkedHashSet.class)
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface Getter {

    }

}
