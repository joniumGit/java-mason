package dev.jonium.mason.support;

import dev.jonium.mason.MasonControl;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

import java.util.Map;
import java.util.Objects;

public interface ControlsSupport {

    @NotNull Map<@NotNull String, @NotNull MasonControl> getControls();

    default MasonControl addControl(
            @NotNull @NonNull String name,
            @NotNull @NonNull MasonControl control
    )
    {
        return getControls().put(name, control);
    }

    default void addControls(@NotNull @NonNull Map<@NotNull String, @NotNull MasonControl> controlMap) {
        if (controlMap.keySet().parallelStream().anyMatch(Objects::isNull)) {
            throw new NullPointerException("Null key");
        } else if (controlMap.values().parallelStream().anyMatch(Objects::isNull)) {
            throw new NullPointerException("Null value");
        } else {
            getControls().putAll(controlMap);
        }
    }

    default MasonControl removeControl(@NotNull @NonNull String name) {
        return getControls().remove(name);
    }

    default void clearControls() {
        getControls().clear();
    }

}
