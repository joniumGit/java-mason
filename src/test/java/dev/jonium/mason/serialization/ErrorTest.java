package dev.jonium.mason.serialization;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import dev.jonium.mason.core.Error;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

public class ErrorTest {

    @Test
    public void readMason() {
        var om = new ObjectMapper();
        om.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        om.findAndRegisterModules();
        Error me;
        String file;
        try (var is = getClass().getResourceAsStream("/error_sample.json")) {
            file = new String(is.readAllBytes());
        } catch (IOException e) {
            Assertions.fail("Failed to read sample JSON", e);
            return;
        }
        me = Assertions.assertDoesNotThrow(
                () -> om.readValue(file, Error.class)
        );
        Assertions.assertEquals(400, me.getHttpStatusCode());
        Assertions.assertEquals("INVALID", me.getCode());
        Assertions.assertEquals(2, me.getMessages().size());
        Assertions.assertTrue(
                Arrays.stream(me.getClass().getDeclaredMethods())
                      .filter(m -> m.getName().startsWith("get"))
                      .allMatch(m -> Assertions.assertDoesNotThrow(() -> m.invoke(me) != null))
        );
        om.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
        var writer = om.writerWithDefaultPrettyPrinter();
        var serialized = Assertions.assertDoesNotThrow(() -> writer.writeValueAsString(me));
        System.out.println(serialized);
    }

}
