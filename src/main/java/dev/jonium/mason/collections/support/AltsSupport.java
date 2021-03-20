package dev.jonium.mason.collections.support;

import dev.jonium.mason.collections.Alts;
import dev.jonium.mason.core.Alt;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

import java.util.Collection;
import java.util.Comparator;

public interface AltsSupport {

    @NotNull Alts getAlts();

    default Boolean addAlt(@NotNull @NonNull Alt alt) {
        return getAlts().add(alt);
    }

    default Boolean removeAlt(@NotNull @NonNull Alt alt) {
        return getAlts().remove(alt);
    }

    default Boolean addAlts(@NotNull @NonNull Collection<Alt> alts) {
        return getAlts().addAll(alts);
    }

    default Boolean removeAlts(@NotNull @NonNull Collection<Alt> alts) {
        return getAlts().removeAll(alts);
    }

    default void sortAlts(@NotNull @NonNull Comparator<Alt> comparator) {
        getAlts().sort(comparator);
    }

}
