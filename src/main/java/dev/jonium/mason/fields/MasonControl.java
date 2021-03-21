package dev.jonium.mason.fields;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.jonium.mason.annotations.ArrayType;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Collection;
import java.util.LinkedHashSet;

import static dev.jonium.mason.serialization.Tokens.Controls.ALT;
import static dev.jonium.mason.serialization.Tokens.Controls.HREF;

/**
 * Represents a Mason control object
 * <p>
 * Only adds ability to have alternative controls to {@link MasonAlt}
 * </p>
 * <p>
 * According to the standard this class shouldn't be extended to add additional properties
 * </p>
 */
@ToString
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MasonControl extends MasonAlt {

    @JsonCreator
    public MasonControl(
            @NotNull @JsonProperty(HREF) String href
    )
    {
        super(href);
    }

    @Getter
    @Setter
    @NotNull
    @NonNull
    @Singular
    @ArrayType
    @JsonSerialize(contentAs = MasonAlt.class)
    @JsonDeserialize(contentAs = MasonAlt.class)
    @JsonProperty(ALT)
    private Collection<MasonAlt> alts = new LinkedHashSet<>();

}
