package dev.jonium.mason.test;

import com.fasterxml.jackson.databind.node.ObjectNode;
import dev.jonium.mason.*;
import dev.jonium.mason.impl.*;
import dev.jonium.mason.serialization.RFC3339DateUtils;
import dev.jonium.mason.serialization.Tokens;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

@DisplayName("Test impl")
public class TestMason {

    ZonedDateTime time = ZonedDateTime.now();
    MasonFileDescriptor builtFile = SimpleMasonFileDescriptor
            .builder()
            .name("name")
            .title("title")
            .description("desc")
            .accept("all")
            .build();
    MasonControl builtControl = SimpleMasonControl
            .builder()
            .href("href")
            .hrefTemplate(false)
            .title("title")
            .description("desc")
            .method("GET")
            .encoding(MasonEncoding.JSON)
            .schema(Utils.getMapper().convertValue(Map.of("hello", "world"), ObjectNode.class))
            .schemaUrl("url")
            .template(Utils.getMapper().convertValue(Map.of("hello", "world"), ObjectNode.class))
            .accept("all")
            .output("all")
            .fileDescriptor(builtFile)
            .alt(SimpleMasonControl.builder().href("title").build())
            .build();
    MasonError builtError = SimpleMasonError
            .builder()
            .message("msg")
            .id("id")
            .code("code")
            .httpStatusCode(400)
            .details("details")
            .addMessage("msgs")
            .control("ctrl", builtControl)
            .time(RFC3339DateUtils.writeOffsetUnknown(time))
            .build();
    MasonMeta builtMeta = SimpleMasonMeta
            .builder()
            .title("title")
            .description("desc")
            .control("ctrl", builtControl)
            .build();
    Mason<?, ?> builtMason = SimpleMason
            .builder()
            .meta(builtMeta)
            .error(builtError)
            .control("ctrl", builtControl)
            .namespace("name", "space")
            .build();

    /**
     * Tests that toString contains all sub-components
     */
    @Test
    @DisplayName("Test toString")
    public void strings() {
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

    /**
     * Tests that implementations work with collections
     */
    @Test
    @DisplayName("Test collection contains")
    public void contains() {
        var set = new LinkedHashSet<>();
        for (var o : List.of(builtMason, builtMeta, builtError, builtControl, builtFile)) {
            Assertions.assertTrue(set.add(o));
            Assertions.assertTrue(set.contains(o));
            Assertions.assertTrue(set.remove(o));
            Assertions.assertTrue(set.isEmpty());
        }
    }

    /**
     * Only tests main types since others are tested in their own tests
     */
    @Test
    @DisplayName("Serialize and test types")
    public void types() {
        var tree = Utils.toTree(builtMason);
        Assertions.assertTrue(tree.get(Tokens.Body.META).isObject());
        Assertions.assertTrue(tree.get(Tokens.Body.CONTROLS).isObject());
        Assertions.assertTrue(tree.get(Tokens.Body.NAMESPACES).isObject());
        Assertions.assertTrue(tree.get(Tokens.Body.ERROR).isObject());
        System.out.println(Assertions.assertDoesNotThrow(
                () -> Utils.getMapper().writerWithDefaultPrettyPrinter().writeValueAsString(builtMason)
        ));
    }

}
