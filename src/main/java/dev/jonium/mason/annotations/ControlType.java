package dev.jonium.mason.annotations;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.jonium.mason.impl.SimpleMasonControl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.LinkedHashMap;

/**
 * Default annotation for serializing and deserializing MasonControls
 */
@JsonSetter(nulls = Nulls.FAIL, contentNulls = Nulls.FAIL)
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonSerialize(as = LinkedHashMap.class, keyAs = String.class, contentAs = SimpleMasonControl.class)
@JsonDeserialize(as = LinkedHashMap.class, keyAs = String.class, contentAs = SimpleMasonControl.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ControlType {}
