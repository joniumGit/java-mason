package dev.jonium.mason.test;

import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public final class Utils {

    public static String readFile(String file) {
        try (var is = Utils.class.getResourceAsStream(file)) {
            return new String(is.readAllBytes());
        } catch (IOException e) {
            Assertions.fail("Failed to read sample JSON", e);
            return "";
        }
    }

    public static <T> T readFromFile(String file, Class<T> clazz) {
        return read(readFile(file), clazz);
    }

    public static TreeNode toTree(Object o) {
        return Assertions.assertDoesNotThrow(() -> getMapper().readTree(write(o)));
    }

    public static <T> void assertGetEquals(
            Class<T> clazz,
            T o1,
            T o2
    )
    {
        Assertions.assertTrue(
                Arrays.stream(clazz.getMethods())
                      .filter(m -> m.getName().startsWith("get"))
                      .allMatch(m -> {
                          var objects = Assertions.assertDoesNotThrow(() -> new Object[]{m.invoke(o1),
                                                                                         m.invoke(o2)});
                          return Objects.deepEquals(objects[0], objects[1]);
                      })
        );
    }

    public static <T> T read(String content, Class<T> clazz) {
        return Assertions.assertDoesNotThrow(() -> getMapper().readValue(content, clazz));
    }

    public static String write(Object o) {
        return Assertions.assertDoesNotThrow(() -> getMapper().writeValueAsString(o));
    }

    @Getter
    private static ObjectMapper mapper;

    static {
        var om = new ObjectMapper();
        om = om.findAndRegisterModules();
        mapper = om;
    }

}
