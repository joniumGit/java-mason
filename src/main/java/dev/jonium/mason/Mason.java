package dev.jonium.mason;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.jonium.mason.features.MasonControlsFeature;
import dev.jonium.mason.features.MasonErrorFeature;
import dev.jonium.mason.features.MasonMetaFeature;
import dev.jonium.mason.features.MasonNamespaceFeature;
import dev.jonium.mason.impl.SimpleMason;

/**
 * Represents a Mason object
 * <p>
 * Can be extended to have all functionalities for Mason.
 * </p>
 * <p>
 * Alternatively you can pick and choose which features you need from
 * <ul>
 *     <li>{@link MasonControlsFeature}</li>
 *     <li>{@link MasonErrorFeature}</li>
 *     <li>{@link MasonMetaFeature}</li>
 *     <li>{@link MasonNamespaceFeature}</li>
 * </ul>
 * <p>
 * <b>remember to add</b> {@link JsonProperty} <b>annotation to have correct field names</b>
 * </p>
 * <p>
 *     By default deserializes as {@link SimpleMason}
 * </p>
 *
 * @see SimpleMason
 */
@JsonDeserialize(as = SimpleMason.class)
public interface Mason<M extends MasonMeta, E extends MasonError>
        extends MasonMetaFeature<M>,
                MasonControlsFeature,
                MasonErrorFeature<E>,
                MasonNamespaceFeature
{}
