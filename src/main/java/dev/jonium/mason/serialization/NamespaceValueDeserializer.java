package dev.jonium.mason.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import dev.jonium.mason.collections.Namespaces;

import java.io.IOException;

/**
 * Uses {@link NamespaceValue} to Deserialize namespace objects form a Mason document
 *
 * @see Namespaces
 * @see NamespaceValueSerializer
 * @see NamespaceValueDeserializer
 */
public class NamespaceValueDeserializer extends JsonDeserializer<String> {

    @Override
    public String deserialize(
            JsonParser jsonParser,
            DeserializationContext deserializationContext
    ) throws IOException, JsonProcessingException
    {
        return jsonParser.readValueAs(NamespaceValue.class).getName();
    }
}
