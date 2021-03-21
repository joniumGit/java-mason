package dev.jonium.mason.features;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import dev.jonium.mason.fields.MasonError;
import dev.jonium.mason.serialization.Tokens;

/**
 * Adds support for Mason Error property to a class.
 * <p>
 * Provides everything needed to automatically serialize and deserialize the Error property
 * </p>
 * <p>
 * <b>NOTE:</b> If you make a custom subclass of {@link MasonError} remember to add a
 * {@link com.fasterxml.jackson.databind.annotation.JsonDeserialize} annotation to specify what they should be
 * deserialized as.
 * </p>
 */
public interface MasonErrorFeature<T extends MasonError> {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty(Tokens.Body.ERROR)
    T getError();

    /**
     * Should be implemented if setting an error is possible
     */
    @JsonSetter(Tokens.Body.ERROR)
    void setError(T error);
}
