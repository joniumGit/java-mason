package dev.jonium.mason.support;

import dev.jonium.mason.fields.MasonControl;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

import java.util.Map;

public interface ControlsSupport {

    @NotNull Map<String, MasonControl> getControls();

    default MasonControl addControl(
            @NotNull @NonNull String name,
            @NotNull @NonNull MasonControl control
    )
    {
        return getControls().put(name, control);
    }

    default void addControls(@NotNull @NonNull Map<String, MasonControl> controlMap) {
        getControls().putAll(controlMap);
    }

    default MasonControl removeControl(@NotNull @NonNull String name) {
        return getControls().remove(name);
    }

    default void clearControls() {
        getControls().clear();
    }

}
