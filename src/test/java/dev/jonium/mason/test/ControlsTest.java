package dev.jonium.mason.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.exc.InvalidNullException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import dev.jonium.mason.Mason;
import dev.jonium.mason.MasonEncoding;
import dev.jonium.mason.impl.SimpleMasonControl;
import dev.jonium.mason.impl.SimpleMasonFileDescriptor;
import dev.jonium.mason.serialization.Tokens;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@DisplayName("Controls tests")
public class ControlsTest {

    @Test
    @DisplayName("Test Sample Alt 1")
    public void altSample() {
        var mason = Utils.readFromFile("/control/control_test_alt_1.json", Mason.class);
        Assertions.assertNotNull(mason.getControls());
        var control = mason.getControls().get("self");
        Assertions.assertEquals("href-self", control.getHref());
        Assertions.assertNotNull(control.getAlts());
        Assertions.assertEquals(2, control.getAlts().size());
        Assertions.assertTrue(control.getAlts().stream().anyMatch(
                alt -> Objects.equals("self-xml", alt.getTitle())
                       && Objects.equals("href-xml-self", alt.getHref())
        ));
        Assertions.assertTrue(control.getAlts().stream().anyMatch(
                alt -> Objects.equals("self-other", alt.getTitle())
                       && Objects.equals("self-other", alt.getHref())
        ));
    }

    @Test
    @DisplayName("Test Sample Alt 2")
    public void altSample2() {
        var mason = Utils.readFromFile("/control/control_test_alt_2.json", Mason.class);
        var control = mason.getControls().get("self");
        var alt = control.getAlts().stream().findFirst().orElseThrow(AssertionError::new);
        Assertions.assertEquals("test", alt.getTitle());
        Assertions.assertEquals("test", alt.getHref());
        Assertions.assertNotNull(alt.getHrefTemplate());
        Assertions.assertFalse(alt.getHrefTemplate());
        Assertions.assertEquals("test", alt.getDescription());
        Assertions.assertEquals("POST", alt.getMethod());
        Assertions.assertEquals(MasonEncoding.JSON, alt.getEncoding());
        Assertions.assertTrue(alt.getSchema().isObject());
        Assertions.assertEquals("test", alt.getSchemaUrl());
        Assertions.assertTrue(alt.getTemplate().isObject());
        Assertions.assertTrue(alt.getAccepts().containsAll(List.of("test1", "test2")));
        Assertions.assertTrue(alt.getOutputs().containsAll(List.of("test1", "test2")));
        Assertions.assertEquals(2, alt.getFileDescriptors().size());
        Assertions.assertTrue(alt.getFileDescriptors().stream().allMatch(Objects::nonNull));
    }

    @Test
    @DisplayName("Test Sample Encoding")
    public void encoding() {
        var mason = Utils.readFromFile("/control/control_test_encoding.json", Mason.class);
        Function<MasonEncoding, Boolean> tester = me -> mason.getControls()
                                                             .values()
                                                             .stream()
                                                             .anyMatch(c -> c.getEncoding() == me);
        Assertions.assertTrue(tester.apply(MasonEncoding.NONE));
        Assertions.assertTrue(tester.apply(MasonEncoding.JSON));
        Assertions.assertTrue(tester.apply(MasonEncoding.JSON_FILES));
        Assertions.assertTrue(tester.apply(MasonEncoding.RAW));
        Assertions.assertFalse(tester.apply(null));
    }

    @Test
    @DisplayName("Test Sample Schema and template type")
    public void schema() {
        var mason = Utils.readFromFile("/control/control_test_schema_type_object.json", Mason.class);
        Function<Function<JsonNode, Boolean>, Boolean> tester = nc -> mason.getControls().values().stream().anyMatch(
                mc -> nc.apply(mc.getSchema())
                      && nc.apply(mc.getTemplate())
        );
        Assertions.assertTrue(tester.apply(JsonNode::isObject));
        Assertions.assertThrows(
                InvalidNullException.class,
                () -> Utils.getMapper().readValue(
                        Utils.readFile("/control/control_test_schema_type_null.json"),
                        Mason.class
                )
        );
        for (var file : List.of("number", "array", "text")) {
            Assertions.assertThrows(
                    MismatchedInputException.class,
                    () -> Utils.getMapper().readValue(
                            Utils.readFile(String.format("/control/control_test_schema_type_%1$s.json", file)),
                            Mason.class
                    )
            );
        }
    }

    @Test
    @DisplayName("Test Sample Full")
    public void full() {
        var mason = Utils.readFromFile("/control/control_test_full.json", Mason.class);
        Assertions.assertFalse(mason.getControls().isEmpty());
        Assertions.assertFalse(
                mason.getControls()
                     .values()
                     .stream()
                     .findFirst()
                     .orElseThrow(AssertionError::new)
                     .getAlts()
                     .isEmpty()
        );
    }

    @Test
    @DisplayName("Serialize and test types")
    public void serialize() {
        var control = SimpleMasonControl
                .builder()
                .href("test")
                .alt(SimpleMasonControl.builder().href("hello").build())
                .accepts(List.of("test"))
                .outputs(List.of("test"))
                .fileDescriptor(SimpleMasonFileDescriptor.builder().name("test").build())
                .fileDescriptor(SimpleMasonFileDescriptor.builder().name("test1").build())
                .build();
        var tree = Utils.toTree(control);
        Assertions.assertTrue(tree.get(Tokens.Controls.ALT).isArray());
        Assertions.assertEquals(1, tree.get(Tokens.Controls.ALT).size());
        for (int i = 0; i < tree.get(Tokens.Controls.ALT).size(); i++) {
            Assertions.assertTrue(tree.get(Tokens.Controls.ALT).get(i).isObject());
        }
        Assertions.assertTrue(tree.get(Tokens.Controls.ACCEPT).isArray());
        Assertions.assertTrue(tree.get(Tokens.Controls.OUTPUT).isArray());
        Assertions.assertTrue(tree.get(Tokens.Controls.FILES).isArray());
        Assertions.assertEquals(2, tree.get(Tokens.Controls.FILES).size());
        for (int i = 0; i < tree.get(Tokens.Controls.ALT).size(); i++) {
            Assertions.assertTrue(tree.get(Tokens.Controls.FILES).get(i).isObject());
        }
    }

}
