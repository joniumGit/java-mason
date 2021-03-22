package dev.jonium.mason.support;

import dev.jonium.mason.MasonControl;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

import java.util.Collection;
import java.util.Objects;

public interface AltsSupport {

    @NotNull Collection<@NotNull MasonControl> getAlts();

    default Boolean addAlt(@NotNull @NonNull MasonControl alt) {
        return getAlts().add(alt);
    }

    default Boolean removeAlt(@NotNull @NonNull MasonControl alt) {
        return getAlts().remove(alt);
    }

    default Boolean addAlts(@NotNull @NonNull Collection<@NotNull MasonControl> alts) {
        if (alts.parallelStream().anyMatch(Objects::isNull)) {
            throw new NullPointerException("Null value");
        }
        return getAlts().addAll(alts);
    }

    default Boolean removeAlts(@NotNull @NonNull Collection<@NotNull MasonControl> alts) {
        return getAlts().removeAll(alts);
    }

    default void clearAlts() {
        getAlts().clear();
    }

}
