package dev.jonium.mason.collections.support;

import dev.jonium.mason.collections.Controls;
import dev.jonium.mason.core.Control;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

import java.util.Map;

public interface ControlsSupport {

    @NotNull Controls getControls();

    default Control addControl(
            @NotNull @NonNull String name,
            @NotNull @NonNull Control control
    )
    {
        return getControls().put(name, control);
    }

    default void addControls(@NotNull @NonNull Map<String, Control> controlMap) {
        getControls().putAll(controlMap);
    }

    default Control removeControl(@NotNull @NonNull String name) {
        return getControls().remove(name);
    }

}
