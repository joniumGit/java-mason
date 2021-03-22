package dev.jonium.mason.test;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import dev.jonium.mason.MasonEncoding;
import dev.jonium.mason.impl.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;

@DisplayName("Test impl")
public class TestObjectMethods {

    @Test
    @DisplayName("Test toString")
    void controls() {
        var i = Instant.now();
        var builtFile = SimpleMasonFileDescriptor
                .builder()
                .name("name")
                .title("title")
                .description("desc")
                .accept("all")
                .build();
        var builtControl = SimpleMasonControl
                .builder()
                .href("href")
                .hrefTemplate(false)
                .title("title")
                .description("desc")
                .method("GET")
                .encoding(MasonEncoding.JSON)
                .schema(JsonNodeFactory.instance.nullNode())
                .schemaUrl("url")
                .template(JsonNodeFactory.instance.nullNode())
                .accept("all")
                .output("all")
                .fileDescriptor(builtFile)
                .alt(SimpleMasonControl.builder().href("title").build())
                .build();
        var builtError = SimpleMasonError
                .builder()
                .message("msg")
                .id("id")
                .code("code")
                .httpStatusCode(400)
                .details("details")
                .addMessage("msgs")
                .control("ctrl", builtControl)
                .time(i)
                .build();
        var builtMeta = SimpleMasonMeta
                .builder()
                .title("title")
                .description("desc")
                .control("ctrl", builtControl)
                .build();
        var builtMason = SimpleMason
                .builder()
                .meta(builtMeta)
                .error(builtError)
                .control("ctrl", builtControl)
                .namespace("name", "space")
                .build();
        Assertions.assertDoesNotThrow(builtFile::toString);
        Assertions.assertDoesNotThrow(builtControl::toString);
        Assertions.assertDoesNotThrow(builtError::toString);
        Assertions.assertDoesNotThrow(builtMeta::toString);
        Assertions.assertDoesNotThrow(builtMason::toString);
        Assertions.assertTrue(builtMason.toString().contains(builtMeta.toString()));
        Assertions.assertTrue(builtMason.toString().contains(builtError.toString()));
        Assertions.assertTrue(builtMason.toString().contains(builtControl.toString()));
        Assertions.assertTrue(builtMason.toString().contains(builtFile.toString()));
    }

}
