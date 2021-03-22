package dev.jonium.mason.support;

import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

import java.util.Collection;
import java.util.Objects;

public interface OutputSupport {

    @NotNull Collection<@NotNull String> getOutputs();

    default Boolean addOutput(@NotNull @NonNull String output) {
        return getOutputs().add(output);
    }

    default Boolean addOutputs(@NotNull @NonNull Collection<@NotNull String> outputs) {
        if (outputs.parallelStream().anyMatch(Objects::isNull)) {
            throw new NullPointerException("Null value");
        }
        return getOutputs().addAll(outputs);
    }

    default Boolean removeOutput(@NotNull @NonNull String output) {
        return getOutputs().remove(output);
    }

    default Boolean removeOutputs(@NotNull @NonNull Collection<@NotNull String> outputs) {
        return getOutputs().removeAll(outputs);
    }

    default void clearOutputs() {
        getOutputs().clear();
    }
}
