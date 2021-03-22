package dev.jonium.mason.impl;

import dev.jonium.mason.MasonControl;
import dev.jonium.mason.MasonError;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * Implements {@link MasonError}
 * <br>
 * <br>
 * Defines Equals and HashCode with Lombok using the
 * <b>{@link SimpleMasonError#message}</b> and
 * <b>{@link SimpleMasonError#id}</b> field.
 * Works in hash structures if not changed after creation
 * <br>
 * <br>
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public final class SimpleMasonError implements MasonError {
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    private String message;
    @EqualsAndHashCode.Include
    private String id;
    private String code;
    private Integer httpStatusCode;
    private String details;
    @NotNull
    @NonNull
    @Singular(value = "addMessage")
    private Collection<@NotNull String> messages = new LinkedHashSet<>();
    @NotNull
    @NonNull
    @Singular
    private Map<@NotNull String, @NotNull MasonControl> controls = new LinkedHashMap<>();
    private Instant time;
}
