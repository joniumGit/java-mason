package dev.jonium.mason;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.jonium.mason.annotations.ArrayType;
import dev.jonium.mason.impl.SimpleMasonFileDescriptor;
import dev.jonium.mason.support.AcceptsSupport;
import jakarta.validation.constraints.NotNull;

import java.util.Collection;

import static dev.jonium.mason.serialization.Tokens.Controls.File.*;


/**
 * Represents the files attribute in a Mason control
 *
 * @see SimpleMasonFileDescriptor
 */
@JsonDeserialize(as = SimpleMasonFileDescriptor.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public interface MasonFileDescriptor extends AcceptsSupport {

    @JsonProperty(value = NAME, required = true)
    void setName(String name);

    @JsonSetter(value = TITLE, nulls = Nulls.FAIL)
    void setTitle(String title);

    @JsonSetter(value = DESCRIPTION, nulls = Nulls.FAIL)
    void setDescription(String description);

    @ArrayType
    @JsonSetter(value = ACCEPT)
    void setAccepts(@NotNull Collection<@NotNull String> accepts);

    @JsonProperty(value = NAME, required = true)
    String getName();

    @JsonProperty(TITLE)
    String getTitle();

    @JsonProperty(DESCRIPTION)
    String getDescription();

    @ArrayType
    @JsonProperty(ACCEPT)
    @NotNull Collection<@NotNull String> getAccepts();
}
