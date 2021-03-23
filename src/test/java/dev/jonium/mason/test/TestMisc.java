package dev.jonium.mason.test;

import dev.jonium.mason.impl.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

@DisplayName("Miscellaneous tests")
public class TestMisc {

    @Test
    @DisplayName("Test defaults are present and right type")
    public void defaults() {
        var mason = new SimpleMason<>();
        var control = new SimpleMasonControl();
        var error = new SimpleMasonError();
        var meta = new SimpleMasonMeta();
        var fd = new SimpleMasonFileDescriptor();
        for (var c : List.of(
                mason.getControls().getClass(),
                mason.getNamespaces().getClass(),
                error.getControls().getClass(),
                meta.getControls().getClass()
        )) {
            Assertions.assertEquals(LinkedHashMap.class, c);
        }
        for (var c : List.of(
                error.getMessages().getClass(),
                control.getAlts().getClass(),
                control.getAccepts().getClass(),
                control.getOutputs().getClass(),
                control.getFileDescriptors().getClass(),
                fd.getAccepts().getClass()
        )) {
            Assertions.assertEquals(LinkedHashSet.class, c);
        }
    }

}
