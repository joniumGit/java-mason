package dev.jonium.mason.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.Instant;

/**
 * This is a helper class to serialize {@link Instant} into a RFC3339 timestamp
 */
public class InstantSerializer extends JsonSerializer<Instant> {

    @Override
    public void serialize(
            Instant instant, JsonGenerator jsonGenerator, SerializerProvider serializerProvider
    ) throws IOException
    {
        //TODO: RFC3339
        jsonGenerator.writeString(instant.toString());
    }
}
