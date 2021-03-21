package dev.jonium.mason.fields;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import dev.jonium.mason.serialization.Tokens;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Implements {@link MasonMeta}
 *
 * @see SimpleMasonMeta#equals(Object)
 * @see SimpleMasonMeta#hashCode()
 */
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@JsonRootName(Tokens.Body.META)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SimpleMasonMeta implements MasonMeta {

    @JsonProperty(Tokens.Meta.TITLE)
    private String title;

    @JsonProperty(Tokens.Meta.DESCRIPTION)
    private String description;

    @NotNull
    @NonNull
    @Singular
    private Map<String, MasonControl> controls = new LinkedHashMap<>();

    /**
     * If two Meta objects share the same {@link MasonMeta#getTitle()} they are deemed equal
     * <p>
     * Allows for contains checks
     * </p>
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof MasonMeta
               && Objects.equals(getTitle(), ((MasonMeta) o).getTitle());
    }

    /**
     * All {@link SimpleMasonMeta} instances share a hashcode of {@link MasonMeta} class
     */
    @Override
    public int hashCode() {
        return MasonMeta.class.hashCode();
    }
}
