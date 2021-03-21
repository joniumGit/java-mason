package dev.jonium.mason.test;

import dev.jonium.mason.ErrorMason;
import dev.jonium.mason.SimpleErrorMason;
import dev.jonium.mason.fields.MasonControl;
import dev.jonium.mason.fields.MasonError;
import dev.jonium.mason.fields.SimpleMasonError;
import dev.jonium.mason.serialization.Tokens;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

@DisplayName("Error tests")
public class ErrorTest {

    ErrorMason<?> reference;

    {
        reference = new SimpleErrorMason(
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
                                        MasonControl.builder().href("test").build()
                                ).build()
        );
    }

    @Test
    @DisplayName("Test sample and equality")
    public void sample() {
        var mason = Utils.readFromFile("/error/error_test_full.json", ErrorMason.class);
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

    @Test
    @DisplayName("Test properties")
    public void properties() {
        var message = "test";
        var error = new SimpleMasonError(message);
        Assertions.assertEquals(message, error.getMessage());
        message = "newMessage";
        error.setMessage(message);
        Assertions.assertEquals(message, error.getMessage());
        error.addMessage(message);
        Assertions.assertTrue(error.getMessages().contains(message));
        var values = List.of("hello", "world");
        error.addMessages(values);
        Assertions.assertTrue(error.getMessages().containsAll(values));
        error.setMessages(new LinkedHashSet<>(values));
        Assertions.assertTrue(error.getMessages().containsAll(values));
        error.clearMessages();
        Assertions.assertTrue(error.getMessages().isEmpty());
        error.setMessages(null);
        Assertions.assertNull(error.getMessages());
        var id = "ID";
        error.setId(id);
        Assertions.assertEquals(id, error.getId());
        error.setCode(id);
        Assertions.assertEquals(id, error.getCode());
        error.setHttpStatusCode(100);
        Assertions.assertEquals(100, error.getHttpStatusCode());
        var time = Instant.now();
        error.setTime(time);
        Assertions.assertEquals(time, error.getTime());
        error.setControls(null);
        Assertions.assertNull(error.getControls());
        var ctrl = new MasonControl("test");
        error.setControls(new LinkedHashMap<>());
        error.addControl("test", ctrl);
        Assertions.assertTrue(error.getControls().containsKey("test"));
        Assertions.assertTrue(error.getControls().containsValue(ctrl));
        Assertions.assertEquals(ctrl, error.addControl("test", new MasonControl("hello")));
        Assertions.assertEquals(1, error.getControls().size());
        error.clearControls();
        Assertions.assertTrue(error.getControls().isEmpty());
    }

}
