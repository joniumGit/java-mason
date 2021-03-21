package dev.jonium.mason.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Uses {@link NamespaceValue} to Serialize {@link String}s into Mason namespace objects
 *
 * @see NamespaceValueSerializer
 * @see NamespaceValueDeserializer
 */
public class NamespaceValueSerializer extends JsonSerializer<String> {
    @Override
    public void serialize(
            String s,
            JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider
    ) throws IOException
    {
        jsonGenerator.writeObject(new NamespaceValue(s));
    }
}
