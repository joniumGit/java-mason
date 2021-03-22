package dev.jonium.mason.impl;

import dev.jonium.mason.MasonFileDescriptor;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * Represents the files attribute in a Mason control
 * <br>
 * <br>
 * Defines Equals and HashCode with Lombok using the <b>{@link SimpleMasonFileDescriptor#name}</b> field.
 * Works in hash structures if it is not changed after creation
 * <br>
 * <br>
 * According to the standard this class shouldn't be extended to add additional properties
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public final class SimpleMasonFileDescriptor implements MasonFileDescriptor {
    @EqualsAndHashCode.Include
    private String name;
    private String title;
    private String description;
    @NonNull
    @NotNull
    @Singular
    private Collection<@NotNull String> accepts = new LinkedHashSet<>();
}
