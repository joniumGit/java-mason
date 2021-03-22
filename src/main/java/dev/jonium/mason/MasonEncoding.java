package dev.jonium.mason;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;

import static dev.jonium.mason.serialization.Tokens.Controls.Encoding;

/**
 * Contains all possible values for {@link dev.jonium.mason.serialization.Tokens.Controls#ENCODING}
 */
public enum MasonEncoding {

    /**
     * The default value if no other encoding is detected
     */
    @JsonEnumDefaultValue
    @JsonProperty(Encoding.NONE) NONE,
    @JsonProperty(Encoding.JSON) JSON,
    @JsonProperty(Encoding.JSON_FILES) JSON_FILES,
    @JsonProperty(Encoding.RAW) RAW

}
