package dev.jonium.mason.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.Instant;

public class InstantSerializer extends JsonSerializer<Instant> {

    @Override
    public void serialize(
            Instant instant, JsonGenerator jsonGenerator, SerializerProvider serializerProvider
    ) throws IOException
    {
        //TODO: RFC3339
        jsonGenerator.writeRawValue(instant.toString());
    }
}
