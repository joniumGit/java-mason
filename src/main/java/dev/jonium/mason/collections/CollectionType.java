package dev.jonium.mason.collections;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

@JsonSetter(nulls = Nulls.SKIP)
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public @interface CollectionType {}
