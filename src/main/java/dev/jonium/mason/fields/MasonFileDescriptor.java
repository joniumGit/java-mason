package dev.jonium.mason.fields;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.jonium.mason.annotations.ArrayType;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Collection;
import java.util.LinkedHashSet;

import static dev.jonium.mason.serialization.Tokens.Controls.File.*;

/**
 * Represents the files attribute in a Mason control
 * <br>
 * <br>
 * Defines Equals and HashCode with Lombok using the <b>name</b> field
 * <br>
 * <br>
 * According to the standard this class shouldn't be extended to add additional properties
 */
@Getter
@Setter
@ToString
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MasonFileDescriptor {

    @JsonCreator
    public MasonFileDescriptor(@NotNull @NonNull @JsonProperty(NAME) String name) {
        this.name = name;
    }

    @JsonProperty(NAME)
    @EqualsAndHashCode.Include
    private final String name;

    @JsonProperty(TITLE)
    private String title;

    @JsonProperty(DESCRIPTION)
    private String description;

    @NotNull
    @Singular
    @ArrayType
    @JsonProperty(ACCEPT)
    private Collection<String> accepts = new LinkedHashSet<>();

}
