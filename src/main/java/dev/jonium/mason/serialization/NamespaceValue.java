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
public class NamespaceValue {

    @Getter
    @JsonProperty(NAME)
    private final String name;


    @JsonCreator
    public NamespaceValue(
            @JsonProperty(NAME) @NotNull @NonNull String name
    )
    {
        this.name = name;
    }


}
