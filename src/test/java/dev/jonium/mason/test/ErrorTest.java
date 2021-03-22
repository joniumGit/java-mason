package dev.jonium.mason.test;

import dev.jonium.mason.Mason;
import dev.jonium.mason.MasonError;
import dev.jonium.mason.impl.SimpleMason;
import dev.jonium.mason.impl.SimpleMasonControl;
import dev.jonium.mason.impl.SimpleMasonError;
import dev.jonium.mason.serialization.Tokens;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

@DisplayName("Error tests")
public class ErrorTest {

    Mason<?, ?> reference;

    {
        reference = SimpleMason.builder().error(
                SimpleMasonError.builder()
                                .id("test-1-error")
                                .message("error")
                                .code("ERROR")
                                .messages(List.of("test", "error"))
                                .details("error")
                                .httpStatusCode(501)
                                .time(Instant.parse("2021-05-12T23:20:50.52Z"))
                                .control(
                                        "test",
                                        SimpleMasonControl.builder().href("test").build()
                                ).build()
        ).build();
    }

    @Test
    @DisplayName("Test sample and equality")
    public void sample() {
        var mason = Utils.readFromFile("/error/error_test_full.json", Mason.class);
        Utils.assertGetEquals(
                MasonError.class,
                reference.getError(),
                mason.getError()
        );
        Assertions.assertEquals(reference.getError(), mason.getError());
        Assertions.assertEquals(reference.getError().hashCode(), mason.getError().hashCode());
    }

    @Test
    @DisplayName("Serialize and test types")
    public void serialize() {
        var tree = Utils.toTree(reference);
        var error = tree.get(Tokens.Body.ERROR);
        Assertions.assertTrue(error.isObject());
        Assertions.assertTrue(error.get(Tokens.Error.CONTROLS).isObject());
        Assertions.assertTrue(error.get(Tokens.Error.MESSAGES).isArray());
    }

}
