package dev.jonium.mason;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dev.jonium.mason.annotations.ArrayType;
import dev.jonium.mason.impl.SimpleMasonControl;
import dev.jonium.mason.impl.SimpleMasonFileDescriptor;
import dev.jonium.mason.support.AcceptsSupport;
import dev.jonium.mason.support.AltsSupport;
import dev.jonium.mason.support.FileDescriptorSupport;
import dev.jonium.mason.support.OutputSupport;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

import static dev.jonium.mason.serialization.Tokens.Controls.*;

/**
 * Represents the base of a Mason control object
 * <p>
 * Identical to a {@link SimpleMasonControl}, but doesn't allow for alternative controls
 * </p>
 * <p>
 * According to the standard this class shouldn't be extended to add additional properties
 * </p>
 *
 * @see SimpleMasonControl
 */
@JsonDeserialize(as = SimpleMasonControl.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public interface MasonControl
        extends FileDescriptorSupport,
                AcceptsSupport,
                OutputSupport,
                AltsSupport
{

    @JsonProperty(value = HREF, required = true)
    void setHref(@NotNull String href);

    @JsonSetter(value = IS_HREF_TEMPLATE, nulls = Nulls.FAIL)
    void setHrefTemplate(Boolean hrefTemplate);

    @JsonSetter(value = TITLE, nulls = Nulls.FAIL)
    void setTitle(String title);

    @JsonSetter(value = DESCRIPTION, nulls = Nulls.FAIL)
    void setDescription(String description);

    @JsonSetter(value = METHOD, nulls = Nulls.FAIL)
    void setMethod(String method);

    @JsonSetter(value = ENCODING, nulls = Nulls.FAIL)
    void setEncoding(@NotNull MasonEncoding encoding);

    @JsonDeserialize(as = ObjectNode.class)
    @JsonSetter(value = SCHEMA, nulls = Nulls.FAIL)
    void setSchema(JsonNode schema);

    @JsonSetter(value = SCHEMA_URL, nulls = Nulls.FAIL)
    void setSchemaUrl(String schemaUrl);

    @JsonDeserialize(as = ObjectNode.class)
    @JsonSetter(value = TEMPLATE, nulls = Nulls.FAIL)
    void setTemplate(JsonNode template);

    @ArrayType
    @JsonSetter(value = ACCEPT)
    void setAccepts(@NotNull Collection<@NotNull String> accepts);

    @ArrayType
    @JsonSetter(value = OUTPUT)
    void setOutputs(@NotNull Collection<@NotNull String> outputs);

    @JsonProperty(value = HREF, required = true)
    @NotNull String getHref();

    @JsonProperty(IS_HREF_TEMPLATE)
    Boolean getHrefTemplate();

    @JsonProperty(TITLE)
    String getTitle();

    @JsonProperty(DESCRIPTION)
    String getDescription();

    @JsonProperty(METHOD)
    String getMethod();

    @JsonProperty(ENCODING)
    MasonEncoding getEncoding();

    @JsonSerialize(as = ObjectNode.class)
    @JsonProperty(SCHEMA)
    @NotNull JsonNode getSchema();

    @JsonProperty(SCHEMA_URL)
    String getSchemaUrl();

    @JsonSerialize(as = ObjectNode.class)
    @JsonProperty(TEMPLATE)
    @NotNull JsonNode getTemplate();

    @ArrayType
    @JsonProperty(ACCEPT)
    @NotNull Collection<@NotNull String> getAccepts();

    @ArrayType
    @JsonProperty(OUTPUT)
    @NotNull Collection<@NotNull String> getOutputs();

    @ArrayType
    @JsonDeserialize(as = LinkedHashSet.class, contentAs = SimpleMasonFileDescriptor.class)
    @JsonSetter(FILES)
    void setFileDescriptors(@NotNull Collection<@NotNull MasonFileDescriptor> fileDescriptors);

    @ArrayType
    @JsonSerialize(as = LinkedHashSet.class, contentAs = SimpleMasonFileDescriptor.class)
    @JsonProperty(FILES)
    @NotNull Collection<@NotNull MasonFileDescriptor> getFileDescriptors();

    @ArrayType
    @JsonSerialize(as = ArrayList.class, contentAs = SimpleMasonControl.class)
    @JsonProperty(ALT)
    @NotNull Collection<@NotNull MasonControl> getAlts();

    @ArrayType
    @JsonDeserialize(as = ArrayList.class, contentAs = SimpleMasonControl.class)
    @JsonSetter(ALT)
    void setAlts(@NotNull Collection<@NotNull MasonControl> alts);

}
