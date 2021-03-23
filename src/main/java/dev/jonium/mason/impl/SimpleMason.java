package dev.jonium.mason.impl;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
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
 * <p>Allows wrapping of objects into mason</p>
 * <p>Equals and hashcode of everything.</p>
 *
 * @param <T> Type of object to wrap
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public final class SimpleMason<T> implements Mason<MasonMeta, MasonError> {
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
    @JsonUnwrapped
    T wrapped;
}
