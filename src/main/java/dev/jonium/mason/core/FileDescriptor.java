package dev.jonium.mason.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.jonium.mason.collections.CollectionType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Collection;
import java.util.LinkedHashSet;

import static dev.jonium.mason.Tokens.Controls.File.*;

@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FileDescriptor {

    @JsonCreator
    public FileDescriptor(
            @NotNull @NonNull @JsonProperty(NAME) String name,
            @NotNull @NonNull @JsonProperty(TITLE) String title,
            @NotNull @NonNull @JsonProperty(DESCRIPTION) String description
    )
    {
        this.name = name;
        this.title = title;
        this.description = description;
    }

    @Getter
    @JsonProperty(NAME)
    private final String name;

    @Getter
    @JsonProperty(TITLE)
    private final String title;

    @Getter
    @JsonProperty(DESCRIPTION)
    private final String description;

    @Getter
    @Setter
    @CollectionType
    @EqualsAndHashCode.Exclude
    @JsonDeserialize(as = LinkedHashSet.class)
    private Collection<String> accepts = new LinkedHashSet<>();

}
