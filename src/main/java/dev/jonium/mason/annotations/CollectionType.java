package dev.jonium.mason.annotations;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mega annotation for Collection types
 */
@JsonSetter(nulls = Nulls.FAIL, contentNulls = Nulls.FAIL)
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CollectionType {}
