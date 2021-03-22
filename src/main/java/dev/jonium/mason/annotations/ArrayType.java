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
@JsonSetter(nulls = Nulls.FAIL, contentNulls = Nulls.FAIL)
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(as = LinkedHashSet.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ArrayType {}
