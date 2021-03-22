package dev.jonium.mason.impl;

import dev.jonium.mason.Mason;
import dev.jonium.mason.MasonControl;
import dev.jonium.mason.MasonError;
import dev.jonium.mason.MasonMeta;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Simple implementation of {@link Mason}
 * <p>Equals and hashcode of everything.</p>
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public final class SimpleMason implements Mason<MasonMeta, MasonError> {
    private MasonMeta meta;
    private MasonError error;
    @NotNull
    @NonNull
    @Singular
    private Map<@NotNull String, @NotNull MasonControl> controls = new LinkedHashMap<>();
    @NotNull
    @NonNull
    @Singular
    private Map<@NotNull String, @NotNull String> namespaces = new LinkedHashMap<>();
}
