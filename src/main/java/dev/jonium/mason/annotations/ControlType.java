package dev.jonium.mason.annotations;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.jonium.mason.fields.MasonControl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * One mega annotation for annotating all methods related to controls
 */
@JsonSetter(nulls = Nulls.AS_EMPTY)
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonSerialize(keyAs = String.class, contentAs = MasonControl.class)
@JsonDeserialize(keyAs = String.class, contentAs = MasonControl.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ControlType {}
