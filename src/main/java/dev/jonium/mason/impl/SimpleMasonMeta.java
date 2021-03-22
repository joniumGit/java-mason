package dev.jonium.mason.impl;


import dev.jonium.mason.MasonControl;
import dev.jonium.mason.MasonMeta;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Implements {@link MasonMeta}
 * <br>
 * <br>
 * Defines Equals and HashCode with Lombok using the <b>{@link SimpleMasonMeta#title}</b> field.
 * Works in hash structures if it is not changed after creation
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
public final class SimpleMasonMeta implements MasonMeta {
    @EqualsAndHashCode.Include
    private String title;
    private String description;
    @NotNull
    @NonNull
    @Singular
    private Map<@NotNull String, @NotNull MasonControl> controls = new LinkedHashMap<>();
}
