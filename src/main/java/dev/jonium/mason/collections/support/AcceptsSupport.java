package dev.jonium.mason.collections.support;

import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

import java.util.Collection;

public interface AcceptsSupport {

    @NotNull Collection<String> getAccepts();

    default Boolean addAccept(@NotNull @NonNull String accept) {
        return getAccepts().add(accept);
    }

    default Boolean addAccepts(@NotNull @NonNull Collection<String> accepts) {
        return getAccepts().addAll(accepts);
    }

    default Boolean removeAccept(@NotNull @NonNull String accept) {
        return getAccepts().remove(accept);
    }

    default Boolean removeAccepts(@NotNull @NonNull Collection<String> accepts) {
        return getAccepts().removeAll(accepts);
    }

    default void clearAccepts() {
        getAccepts().clear();
    }

}
