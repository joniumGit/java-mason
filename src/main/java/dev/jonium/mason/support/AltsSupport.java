package dev.jonium.mason.support;

import dev.jonium.mason.fields.MasonAlt;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

import java.util.Collection;

public interface AltsSupport {

    @NotNull Collection<MasonAlt> getAlts();

    default Boolean addAlt(@NotNull @NonNull MasonAlt alt) {
        return getAlts().add(alt);
    }

    default Boolean removeAlt(@NotNull @NonNull MasonAlt alt) {
        return getAlts().remove(alt);
    }

    default Boolean addAlts(@NotNull @NonNull Collection<MasonAlt> alts) {
        return getAlts().addAll(alts);
    }

    default Boolean removeAlts(@NotNull @NonNull Collection<MasonAlt> alts) {
        return getAlts().removeAll(alts);
    }

}
