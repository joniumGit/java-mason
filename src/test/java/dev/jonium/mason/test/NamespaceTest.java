package dev.jonium.mason.test;

import dev.jonium.mason.Mason;
import dev.jonium.mason.impl.SimpleMason;
import dev.jonium.mason.serialization.Tokens;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Namespace tests")
public class NamespaceTest {

    @Test
    @DisplayName("Test sample 1")
    void sample() {
        var mason = Utils.readFromFile("/namespace/namespace_test_1.json", Mason.class);
        Assertions.assertEquals("test", mason.getNamespaces().keySet().stream().findFirst().orElseThrow());
        Assertions.assertEquals("test", mason.getNamespaces().get("test"));
    }

    @Test
    @DisplayName("Test sample 2")
    void sample2() {
        var mason = Utils.readFromFile("/namespace/namespace_test_2.json", Mason.class);
        Assertions.assertEquals(2, mason.getNamespaces().size());
    }

    @Test
    @DisplayName("Serialize and test types")
    void serialize() {
        var mason = new SimpleMason();
        mason.addNamespace("test", "test");
        Assertions.assertTrue(mason.getNamespaces().containsKey("test"));
        var s = Utils.write(mason);
        var tree = Assertions.assertDoesNotThrow(() -> Utils.getMapper().readTree(s));
        var namespaces = tree.get(Tokens.Body.NAMESPACES);
        Assertions.assertTrue(namespaces.isObject());
        var testNs = namespaces.get("test");
        Assertions.assertTrue(testNs.isObject(), namespaces.toString());
        Assertions.assertEquals("test", testNs.get(Tokens.Namespaces.NAME).asText());
        var mason2 = Utils.read(s, Mason.class);
        Assertions.assertTrue(mason2.getNamespaces().containsKey("test"));
    }

}
