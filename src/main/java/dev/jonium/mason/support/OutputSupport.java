package dev.jonium.mason.support;

import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

import java.util.Collection;

public interface OutputSupport {

    @NotNull Collection<String> getOutputs();

    default Boolean addOutput(@NotNull @NonNull String output) {
        return getOutputs().add(output);
    }

    default Boolean addOutputs(@NotNull @NonNull Collection<String> outputs) {
        return getOutputs().addAll(outputs);
    }

    default Boolean removeOutput(@NotNull @NonNull String output) {
        return getOutputs().remove(output);
    }

    default Boolean removeOutputs(@NotNull @NonNull Collection<String> outputs) {
        return getOutputs().removeAll(outputs);
    }

    default void clearOutputs() {
        getOutputs().clear();
    }


}
