package dev.jonium.mason.serialization;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;

import static dev.jonium.mason.serialization.Tokens.Namespaces.NAME;

/**
 * Helper for Serializing/Deserializing Mason Namespaces
 *
 * @see NamespaceValueSerializer
 * @see NamespaceValueDeserializer
 */
public final class NamespaceValue {

    @NotNull
    @Getter
    @JsonProperty(NAME)
    private final String name;

    @JsonCreator
    public NamespaceValue(
            @JsonProperty(value = NAME, required = true) @NotNull @NonNull String name
    )
    {
        this.name = name;
    }


}
