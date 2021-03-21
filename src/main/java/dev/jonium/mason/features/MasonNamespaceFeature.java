package dev.jonium.mason.features;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.jonium.mason.annotations.CollectionType;
import dev.jonium.mason.serialization.NamespaceValueDeserializer;
import dev.jonium.mason.serialization.NamespaceValueSerializer;
import dev.jonium.mason.serialization.Tokens;
import dev.jonium.mason.support.NamespacesSupport;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Adds support for Mason Namespaces to a class
 * <p>
 * Provides everything needed to automatically serialize and deserialize the namespaces attribute. Also adds some
 * convenience functions for namespaces
 * </p>
 */
public interface MasonNamespaceFeature extends NamespacesSupport {

    @CollectionType
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    @JsonSerialize(
            as = LinkedHashMap.class,
            keyAs = String.class,
            contentUsing = NamespaceValueSerializer.class
    )
    @JsonProperty(Tokens.Body.NAMESPACES)
    Map<String, String> getNamespaces();

    @CollectionType
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    @JsonDeserialize(
            as = LinkedHashMap.class,
            keyAs = String.class,
            contentUsing = NamespaceValueDeserializer.class
    )
    @JsonSetter(Tokens.Body.NAMESPACES)
    void setNamespaces(Map<String, String> namespaces);

}
