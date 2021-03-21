package dev.jonium.mason;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.jonium.mason.features.MasonErrorFeature;
import dev.jonium.mason.fields.MasonError;

/**
 * Convenience class to represent Mason errors. Can be extended to add support for custom properties.
 * <p>
 * By default deserializes as {@link SimpleMason}
 * </p
 */
@JsonDeserialize(as = SimpleErrorMason.class)
public interface ErrorMason<T extends MasonError> extends MasonErrorFeature<T> {}
