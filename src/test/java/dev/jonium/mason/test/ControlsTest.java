package dev.jonium.mason.test;

import com.fasterxml.jackson.databind.JsonNode;
import dev.jonium.mason.Mason;
import dev.jonium.mason.fields.MasonEncoding;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@DisplayName("Controls tests")
public class ControlsTest {

    @Test
    @DisplayName("Test Sample Alt 1")
    void altSample() {
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
    void altSample2() {
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
    void encoding() {
        var mason = Utils.readFromFile("/control/control_test_encoding.json", Mason.class);
        Function<MasonEncoding, Boolean> tester = me -> mason.getControls()
                                                             .values()
                                                             .stream()
                                                             .anyMatch(c -> c.getEncoding() == me);
        Assertions.assertTrue(tester.apply(MasonEncoding.NONE));
        Assertions.assertTrue(tester.apply(MasonEncoding.JSON));
        Assertions.assertTrue(tester.apply(MasonEncoding.JSON_FILES));
        Assertions.assertTrue(tester.apply(MasonEncoding.RAW));
        Assertions.assertTrue(tester.apply(null));
    }

    @Test
    @DisplayName("Test Sample Schema and template type")
    void schema() {
        var mason = Utils.readFromFile("/control/control_test_shema_type.json", Mason.class);
        Function<Function<JsonNode, Boolean>, Boolean> tester = nc -> mason.getControls().values().stream().anyMatch(
                mc -> nc.apply(mc.getSchema())
                      && nc.apply(mc.getTemplate())
        );
        Assertions.assertTrue(tester.apply(JsonNode::isNumber));
        Assertions.assertTrue(tester.apply(JsonNode::isTextual));
        Assertions.assertTrue(tester.apply(JsonNode::isObject));
        Assertions.assertTrue(tester.apply(JsonNode::isArray));
        Assertions.assertTrue(tester.apply(JsonNode::isNull));
    }

    @Disabled
    @Test
    @DisplayName("Test Sample Full")
    void full() {
        // TODO
    }

    @Disabled
    @Test
    @DisplayName("Serialize and test types")
    void serialize() {
        // TODO
    }

}
