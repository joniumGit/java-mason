package dev.jonium.mason.features;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import dev.jonium.mason.MasonControl;
import dev.jonium.mason.annotations.ControlType;
import dev.jonium.mason.serialization.Tokens;
import dev.jonium.mason.support.ControlsSupport;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

/**
 * Adds support for mason Controls property to a class
 * <p>
 * Provides everything needed to serialize and deserialize the Controls property.
 * Also adds some convenience functions
 * </p>
 */
public interface MasonControlsFeature extends ControlsSupport {

    @ControlType
    @JsonProperty(Tokens.Body.CONTROLS)
    @NotNull Map<String, MasonControl> getControls();

    @ControlType
    @JsonSetter(value = Tokens.Body.CONTROLS)
    void setControls(@NotNull Map<String, MasonControl> controls);

}
