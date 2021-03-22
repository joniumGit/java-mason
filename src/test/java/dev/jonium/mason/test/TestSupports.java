package dev.jonium.mason.test;

import dev.jonium.mason.impl.*;
import dev.jonium.mason.support.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@DisplayName("Testing support interfaces")
public class TestSupports {

    /**
     * Finds a method based on regex
     */
    Method findWithPattern(String regex, Class<?> clazz) {
        return Arrays.stream(clazz.getMethods())
                     .filter(m -> m.getName().matches(regex))
                     .findFirst()
                     .orElseThrow(AssertionError::new);
    }

    /**
     * Asserts that a reflective invocation throws an NPE with default Lombok message or Null starting message
     */
    void assertWrappedNPE(Executable supplier) {
        var ive = Assertions.assertThrows(InvocationTargetException.class, supplier);
        Assertions.assertTrue(
                ive.getCause().getMessage().startsWith("Null")
                || ive.getCause().getMessage().contains("non-null")
        );
        Assertions.assertEquals(
                NullPointerException.class,
                ive.getCause().getClass()
        );
    }

    /**
     * Tests different kinds of inputs on collection type supports
     */
    void testCollectionType(
            Object tester,
            Object insertable,
            Class<?> methodsProvider
    )
    {
        Method getter = findWithPattern("^get\\w+$", methodsProvider);
        Method singleSetter = findWithPattern("^add\\w+(?<!s)$", methodsProvider);
        Method singleRemove = findWithPattern("^remove\\w+(?<!s)$", methodsProvider);
        Method multiAdd = findWithPattern("^add\\w+(?<=s)$", methodsProvider);
        Method multiRemove = findWithPattern("^remove\\w+(?<=s)$", methodsProvider);
        Method clear = findWithPattern("clear\\w+", methodsProvider);
        Assertions.assertTrue(Assertions.assertDoesNotThrow(() -> (Boolean) singleSetter.invoke(tester, insertable)));
        Assertions.assertTrue(Assertions.assertDoesNotThrow(() -> (Boolean) singleRemove.invoke(tester, insertable)));
        Assertions.assertTrue(Assertions.assertDoesNotThrow(() -> (Boolean) singleSetter.invoke(tester, insertable)));
        Assertions.assertDoesNotThrow(() -> clear.invoke(tester));
        Assertions.assertTrue(Assertions.assertDoesNotThrow(() -> ((Collection<?>) getter.invoke(tester)).isEmpty()));
        Assertions.assertDoesNotThrow(() -> multiAdd.invoke(tester, List.of(insertable)));
        Assertions.assertFalse(Assertions.assertDoesNotThrow(() -> ((Collection<?>) getter.invoke(tester)).isEmpty()));
        Assertions.assertDoesNotThrow(() -> multiRemove.invoke(tester, List.of(insertable)));
        Assertions.assertTrue(Assertions.assertDoesNotThrow(() -> ((Collection<?>) getter.invoke(tester)).isEmpty()));
        assertWrappedNPE(() -> singleSetter.invoke(tester, (Object) null));
        assertWrappedNPE(() -> multiAdd.invoke(tester, (Object) null));
        var list = new ArrayList<>();
        list.add(null);
        assertWrappedNPE(() -> multiAdd.invoke(tester, list));
    }

    /**
     * Tests different kinds of inputs on map type supports
     */
    void testMapType(
            Object tester,
            Map.Entry<?, ?> insertable,
            Class<?> methodsProvider
    )
    {
        Method getter = findWithPattern("^get\\w+$", methodsProvider);
        Method singleSetter = findWithPattern("^add\\w+(?<!s)$", methodsProvider);
        Method singleRemove = findWithPattern("^remove\\w+(?<!s)$", methodsProvider);
        Method multiAdd = findWithPattern("^add\\w+(?<=s)$", methodsProvider);
        Method clear = findWithPattern("^clear\\w+$", methodsProvider);
        Assertions.assertNull(Assertions.assertDoesNotThrow(
                () -> singleSetter.invoke(tester, insertable.getKey(), insertable.getValue())
        ));
        Assertions.assertEquals(insertable.getValue(), Assertions.assertDoesNotThrow(
                () -> singleSetter.invoke(tester, insertable.getKey(), insertable.getValue())
        ));
        Assertions.assertEquals(insertable.getValue(), Assertions.assertDoesNotThrow(
                () -> singleRemove.invoke(tester, insertable.getKey())
        ));
        Assertions.assertTrue(Assertions.assertDoesNotThrow(() -> ((Map<?, ?>) getter.invoke(tester)).isEmpty()));
        Assertions.assertNull(Assertions.assertDoesNotThrow(
                () -> singleSetter.invoke(tester, insertable.getKey(), insertable.getValue())
        ));
        Assertions.assertDoesNotThrow(() -> clear.invoke(tester));
        Assertions.assertTrue(Assertions.assertDoesNotThrow(() -> ((Map<?, ?>) getter.invoke(tester)).isEmpty()));
        assertWrappedNPE(() -> singleSetter.invoke(tester, null, insertable.getValue()));
        assertWrappedNPE(() -> singleSetter.invoke(tester, insertable.getKey(), null));
        var map = new HashMap<>();
        map.put(null, null);
        assertWrappedNPE(() -> multiAdd.invoke(tester, map));
        map.clear();
        map.put(null, insertable.getValue());
        assertWrappedNPE(() -> multiAdd.invoke(tester, map));
        map.clear();
        map.put(insertable.getKey(), null);
        assertWrappedNPE(() -> multiAdd.invoke(tester, map));
        Assertions.assertTrue(Assertions.assertDoesNotThrow(() -> ((Map<?, ?>) getter.invoke(tester)).isEmpty()));
        map.clear();
        map.put(insertable.getKey(), insertable.getValue());
        Assertions.assertDoesNotThrow(() -> multiAdd.invoke(tester, map));
        Assertions.assertFalse(Assertions.assertDoesNotThrow(() -> ((Map<?, ?>) getter.invoke(tester)).isEmpty()));
    }

    @Test
    @DisplayName("Accepts")
    void accepts() {
        var control = new SimpleMasonControl();
        var file = new SimpleMasonFileDescriptor();
        for (var o : List.of(control, file)) {
            testCollectionType(
                    o,
                    "hello",
                    AcceptsSupport.class
            );
        }
    }

    @Test
    @DisplayName("Outputs")
    void outputs() {
        var control = new SimpleMasonControl();
        testCollectionType(
                control,
                "hello",
                OutputSupport.class
        );
    }

    @Test
    @DisplayName("Alts")
    void alts() {
        var control = new SimpleMasonControl();
        testCollectionType(
                control,
                SimpleMasonControl.builder().href("hello").build(),
                AltsSupport.class
        );
    }

    @Test
    @DisplayName("Messages")
    void messages() {
        var error = new SimpleMasonError();
        testCollectionType(
                error,
                "hello",
                MessagesSupport.class
        );
    }

    @Test
    @DisplayName("FileDescriptor")
    void fd() {
        var control = new SimpleMasonControl();
        testCollectionType(
                control,
                SimpleMasonFileDescriptor.builder().name("hello").build(),
                FileDescriptorSupport.class
        );
    }

    @Test
    @DisplayName("Controls")
    void controls() {
        var mason = new SimpleMason();
        var error = new SimpleMasonError();
        var meta = new SimpleMasonMeta();
        for (var o : List.of(mason, error, meta)) {
            testMapType(
                    o,
                    new AbstractMap.SimpleEntry<>("hello", SimpleMasonControl.builder().href("hello").build()),
                    ControlsSupport.class
            );
        }
    }

    @Test
    @DisplayName("Namespaces")
    void ns() {
        var mason = new SimpleMason();
        testMapType(
                mason,
                new AbstractMap.SimpleEntry<>("hello", "world"),
                NamespacesSupport.class
        );
    }

}
