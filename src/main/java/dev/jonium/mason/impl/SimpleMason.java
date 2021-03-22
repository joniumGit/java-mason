package dev.jonium.mason.impl;

import dev.jonium.mason.Mason;
import dev.jonium.mason.MasonControl;
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
public final class SimpleMason implements Mason<SimpleMasonMeta, SimpleMasonError> {
    private SimpleMasonMeta meta;
    private SimpleMasonError error;
    @NotNull
    @NonNull
    @Singular
    private Map<@NotNull String, @NotNull MasonControl> controls = new LinkedHashMap<>();
    @NotNull
    @NonNull
    @Singular
    private Map<@NotNull String, @NotNull String> namespaces = new LinkedHashMap<>();
}
