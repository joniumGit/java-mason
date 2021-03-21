package dev.jonium.mason.fields;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.jonium.mason.annotations.ArrayType;
import dev.jonium.mason.support.AcceptsSupport;
import dev.jonium.mason.support.FileDescriptorSupport;
import dev.jonium.mason.support.OutputSupport;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;

import static dev.jonium.mason.serialization.Tokens.Controls.*;

/**
 * Represents the base of a Mason control object
 * <p>
 * Identical to a {@link MasonControl}, but doesn't allow for alternative controls
 * </p>
 * <p>
 * According to the standard this class shouldn't be extended to add additional properties
 * </p>
 *
 * @see MasonAlt#equals(Object)
 * @see MasonAlt#hashCode()
 */
@Getter
@Setter
@ToString
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MasonAlt implements FileDescriptorSupport, AcceptsSupport, OutputSupport {

    @JsonCreator
    public MasonAlt(
            @NotNull @NonNull @JsonProperty(HREF) String href
    )
    {
        this.href = href;
    }

    @NotNull
    @NonNull
    @JsonProperty(HREF)
    private String href;

    @JsonProperty(IS_HREF_TEMPLATE)
    private Boolean hrefTemplate;

    @JsonProperty(TITLE)
    private String title;

    @JsonProperty(DESCRIPTION)
    private String description;

    @JsonProperty(METHOD)
    private String method;

    @JsonProperty(ENCODING)
    private MasonEncoding encoding;

    @JsonProperty(SCHEMA)
    private JsonNode schema;

    @JsonProperty(SCHEMA_URL)
    private String schemaUrl;

    @JsonProperty(TEMPLATE)
    private JsonNode template;

    @Singular
    @ArrayType
    @JsonProperty(ACCEPT)
    private Collection<String> accepts = new LinkedHashSet<>();

    @Singular
    @ArrayType
    @JsonProperty(OUTPUT)
    private Collection<String> outputs = new LinkedHashSet<>();

    @Singular
    @ArrayType
    @JsonSerialize(contentAs = MasonFileDescriptor.class)
    @JsonDeserialize(contentAs = MasonFileDescriptor.class)
    @JsonProperty(FILES)
    private Collection<MasonFileDescriptor> fileDescriptors = new LinkedHashSet<>();

    /**
     * Provides <b>an assumption of equality</b>.
     * <p>
     * If all of these fields are equal the control should point to the same place
     * </p>
     * <ln>
     * <li>{@link MasonAlt#href}</li>
     * <li>{@link MasonAlt#method}</li>
     * <li>{@link MasonAlt#encoding}</li>
     * </ln>
     * <p>
     * This allows for simple comparisons and contains checks for controls using the most important properties
     * </p>
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof MasonAlt) {
            var alt = (MasonAlt) o;
            return Objects.equals(getHref(), alt.getHref())
                   && Objects.equals(getMethod(), alt.getMethod())
                   && Objects.equals(
                    Optional.ofNullable(getEncoding()).orElse(MasonEncoding.NONE),
                    Optional.ofNullable(alt.getEncoding()).orElse(MasonEncoding.NONE)
            );
        }
        return false;
    }

    /**
     * <b>NOTE:</b> Only implemented so that all controls share the same hashcode
     * <p>
     * <b>
     * This means that their performance in hash structures is very bad
     * </b>
     * </p>
     */
    @Override
    public int hashCode() {
        return MasonAlt.class.hashCode();
    }
}
