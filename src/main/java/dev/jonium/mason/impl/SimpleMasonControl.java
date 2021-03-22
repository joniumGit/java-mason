package dev.jonium.mason.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import dev.jonium.mason.MasonControl;
import dev.jonium.mason.MasonEncoding;
import dev.jonium.mason.MasonFileDescriptor;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * Represents a Mason control object
 * <p>
 * If all of these fields are equal the control should be equal
 * </p>
 * <ln>
 * <li>{@link MasonControl#getHref()}</li>
 * <li>{@link MasonControl#getMethod()}</li>
 * <li>{@link MasonControl#getEncoding()}</li>
 * </ln>
 * <p>
 * Works in hash structures if these are not changed after creation
 * </p>
 * <p>
 * According to the standard this class shouldn't be extended to add additional properties
 * </p>
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public final class SimpleMasonControl implements MasonControl {
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    private String href;
    private Boolean hrefTemplate;
    private String title;
    private String description;
    @EqualsAndHashCode.Include
    private String method;
    @NotNull
    @NonNull
    @Builder.Default
    @EqualsAndHashCode.Include
    private MasonEncoding encoding = MasonEncoding.NONE;
    {
        encoding = MasonEncoding.NONE;
    }
    private JsonNode schema;
    private String schemaUrl;
    private JsonNode template;
    @NotNull
    @NonNull
    @Singular
    private Collection<@NotNull String> accepts = new LinkedHashSet<>();
    @NotNull
    @NonNull
    @Singular
    private Collection<@NotNull String> outputs = new LinkedHashSet<>();
    @NotNull
    @NonNull
    @Singular
    private Collection<@NotNull MasonFileDescriptor> fileDescriptors = new LinkedHashSet<>();
    @NotNull
    @NonNull
    @Singular
    private Collection<@NotNull MasonControl> alts = new LinkedHashSet<>();
}
