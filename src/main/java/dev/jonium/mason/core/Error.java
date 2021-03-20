package dev.jonium.mason.core;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.jonium.mason.Tokens;
import dev.jonium.mason.collections.CollectionType;
import dev.jonium.mason.collections.Controls;
import dev.jonium.mason.collections.support.ControlsSupport;
import dev.jonium.mason.serialization.InstantSerializer;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.Collection;
import java.util.LinkedHashSet;

import static dev.jonium.mason.Tokens.Error.*;

@ToString
@JsonRootName(Tokens.Body.ERROR)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Error implements ControlsSupport {

    @JsonCreator
    public Error(@NotNull @NonNull @JsonProperty(MESSAGE) String message) {
        this.message = message;
    }

    @Getter
    @Setter
    @JsonProperty(MESSAGES)
    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    @JsonDeserialize(as = LinkedHashSet.class)
    @CollectionType
    private Collection<String> messages = new LinkedHashSet<>();

    @Getter
    @Setter
    @JsonProperty(CONTROLS)
    @CollectionType
    private Controls controls = new Controls();

    @Getter
    @Setter
    @NotNull
    @NonNull
    @JsonProperty(MESSAGE)
    private String message;

    @Getter
    @Setter
    @JsonProperty(ID)
    private String id;

    @Getter
    @Setter
    @JsonProperty(CODE)
    private String code;

    @Getter
    @Setter
    @JsonProperty(DETAILS)
    private String details;

    @Getter
    @Setter
    @JsonProperty(HTTP_STATUS_CODE)
    private Integer httpStatusCode;

    @Getter
    @Setter
    @JsonProperty(TIME)
    @JsonSerialize(using = InstantSerializer.class)
    private Instant time;

}
