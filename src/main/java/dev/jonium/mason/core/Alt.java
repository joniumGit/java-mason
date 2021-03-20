package dev.jonium.mason.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.jonium.mason.collections.CollectionType;
import dev.jonium.mason.collections.FileDescriptors;
import dev.jonium.mason.core.control.ControlObject;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;
import java.util.LinkedHashSet;

import static dev.jonium.mason.Tokens.Controls.*;


@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Alt implements ControlObject {

    @JsonCreator
    public Alt(
            @NotNull @NonNull @JsonProperty(HREF) String href
    )
    {
        this.href = href;
    }

    @Getter
    @Setter
    @NotNull
    @NonNull
    @JsonProperty(HREF)
    private String href;

    @Getter
    @Setter
    @JsonProperty(IS_HREF_TEMPLATE)
    private Boolean hrefTemplate;

    @Getter
    @Setter
    @JsonProperty(TITLE)
    private String title;

    @Getter
    @Setter
    @JsonProperty(DESCRIPTION)
    private String description;

    @Getter
    @Setter
    @JsonProperty(METHOD)
    private String method;

    @Getter
    @Setter
    @JsonProperty(ENCODING)
    private Encoding encoding;

    @Getter
    @Setter
    @JsonProperty(SCHEMA)
    private Object schema;

    @Getter
    @Setter
    @JsonProperty(SCHEMA_URL)
    private String schemaUrl;

    @Getter
    @Setter
    @JsonProperty(TEMPLATE)
    private Object template;

    @Getter
    @Setter
    @JsonProperty(ACCEPT)
    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    @JsonDeserialize(as = LinkedHashSet.class)
    @CollectionType
    private Collection<String> accepts = new LinkedHashSet<>();

    @Getter
    @Setter
    @JsonProperty(OUTPUT)
    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    @JsonDeserialize(as = LinkedHashSet.class)
    @CollectionType
    private Collection<String> outputs = new LinkedHashSet<>();

    @Getter
    @Setter
    @JsonProperty(FILES)
    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    @CollectionType
    private FileDescriptors fileDescriptors = new FileDescriptors();

}
