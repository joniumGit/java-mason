package dev.jonium.mason;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.jonium.mason.annotations.ControlType;
import dev.jonium.mason.impl.SimpleMasonMeta;
import dev.jonium.mason.support.ControlsSupport;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

import static dev.jonium.mason.serialization.Tokens.Meta.*;

/**
 * Represents the meta attribute in a Mason document
 * <p>
 * According to the standard this class can be freely extended to add custom properties
 * </p>
 * <p>
 * By default deserializes as {@link SimpleMasonMeta}
 * </p>
 *
 * @see SimpleMasonMeta
 */
@JsonDeserialize(as = SimpleMasonMeta.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public interface MasonMeta extends ControlsSupport {

    @JsonProperty(TITLE)
    String getTitle();

    @JsonProperty(DESCRIPTION)
    String getDescription();

    @ControlType
    @JsonProperty(CONTROLS)
    @NotNull Map<@NotNull String, @NotNull MasonControl> getControls();

    @JsonSetter(value = TITLE, nulls = Nulls.FAIL)
    void setTitle(String title);

    @JsonSetter(value = DESCRIPTION, nulls = Nulls.FAIL)
    void setDescription(String description);

    @ControlType
    @JsonSetter(value = CONTROLS)
    void setControls(@NotNull Map<@NotNull String, @NotNull MasonControl> controls);

}
