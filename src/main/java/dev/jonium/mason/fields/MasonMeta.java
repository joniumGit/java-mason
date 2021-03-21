package dev.jonium.mason.fields;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.jonium.mason.annotations.ControlType;
import dev.jonium.mason.support.ControlsSupport;

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
 * @see SimpleMasonMeta#equals(Object)
 * @see SimpleMasonMeta#hashCode()
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(as = SimpleMasonMeta.class)
public interface MasonMeta extends ControlsSupport {

    @JsonProperty(TITLE)
    String getTitle();

    @JsonProperty(DESCRIPTION)
    String getDescription();

    @ControlType
    @JsonProperty(CONTROLS)
    Map<String, MasonControl> getControls();

    @JsonSetter(TITLE)
    void setTitle(String title);

    @JsonSetter(DESCRIPTION)
    void setDescription(String description);

    @ControlType
    @JsonSetter(CONTROLS)
    void setControls(Map<String, MasonControl> controls);

}
