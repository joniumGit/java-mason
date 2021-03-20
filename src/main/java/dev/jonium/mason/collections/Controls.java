package dev.jonium.mason.collections;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.jonium.mason.Tokens;
import dev.jonium.mason.collections.support.ControlsSupport;
import dev.jonium.mason.core.Control;

import java.util.LinkedHashMap;

/**
 * Container and Jackson support class for {@link Control}s in a Mason document
 *
 * @see Control
 */
@JsonRootName(Tokens.Body.CONTROLS)
@JsonDeserialize(contentAs = Control.class)
@JsonSerialize(contentAs = Control.class)
public class Controls extends LinkedHashMap<String, Control> implements ControlsSupport {
    @JsonIgnore
    @Override
    public Controls getControls() {
        return this;
    }
}
