package dev.jonium.mason.test;

import dev.jonium.mason.Mason;
import dev.jonium.mason.impl.SimpleMason;
import dev.jonium.mason.impl.SimpleMasonControl;
import dev.jonium.mason.impl.SimpleMasonMeta;
import dev.jonium.mason.serialization.Tokens;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Meta tests")
public class MetaTest {

    @Test
    @DisplayName("Test sample")
    public void sample() {
        var mason = Utils.read(Utils.readFile("/meta/meta_test_full.json"), Mason.class);
        Assertions.assertNotNull(mason.getMeta());
        Assertions.assertEquals("test", mason.getMeta().getTitle());
        Assertions.assertEquals("test", mason.getMeta().getDescription());
        Assertions.assertEquals("test", mason.getMeta().getControls().get("self").getHref());
    }

    @Test
    @DisplayName("Serialize and test types")
    public void serialize() {
        var tree = Utils.toTree(SimpleMason.builder().meta(
                SimpleMasonMeta.builder()
                               .title("test")
                               .description("test")
                               .control("self", SimpleMasonControl.builder().href("self").build())
                               .build()
        ).build());
        var meta = tree.get(Tokens.Body.META);
        Assertions.assertTrue(meta.isObject());
        Assertions.assertTrue(meta.get(Tokens.Meta.CONTROLS).isObject());
    }

}
