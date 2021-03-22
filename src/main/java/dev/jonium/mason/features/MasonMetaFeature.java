package dev.jonium.mason.features;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import dev.jonium.mason.MasonMeta;
import dev.jonium.mason.serialization.Tokens;

/**
 * Adds support for Mason Meta property to a class.
 * <p>
 * Provides everything needed to automatically serialize and deserialize the Meta property
 * </p>
 * <p>
 * <b>NOTE:</b> If you make a custom subclass of {@link MasonMeta} remember to add a
 * {@link com.fasterxml.jackson.databind.annotation.JsonDeserialize} annotation to specify what they should be
 * deserialized as.
 * </p>
 */
public interface MasonMetaFeature<T extends MasonMeta> {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty(Tokens.Body.META)
    T getMeta();

    @JsonSetter(Tokens.Body.META)
    void setMeta(T meta);
}
