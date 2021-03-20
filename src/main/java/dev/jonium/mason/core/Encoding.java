package dev.jonium.mason.core;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Encoding {

    @JsonEnumDefaultValue
    NONE("none"),
    JSON("json"),
    JSON_FILES("json+files"),
    RAW("raw");

    @Getter(onMethod_ = {@JsonValue})
    private final String raw;

}
