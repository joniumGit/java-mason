package dev.jonium.mason.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.Instant;

/**
 * This is a helper class to serialize {@link Instant} into a RFC3339 timestamp
 */
public class RFC3339Deserializer extends JsonDeserializer<Instant> {

    @Override
    public Instant deserialize(
            JsonParser jsonParser,
            DeserializationContext deserializationContext
    ) throws IOException, JsonProcessingException
    {
        //TODO: RFC3339
        return Instant.parse(jsonParser.readValueAs(String.class));
    }
}
