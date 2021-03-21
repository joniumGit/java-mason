package dev.jonium.mason.fields;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import dev.jonium.mason.serialization.Tokens;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.*;

import static dev.jonium.mason.serialization.Tokens.Error.MESSAGE;

/**
 * Implements {@link MasonError}
 *
 * @see SimpleMasonError#equals(Object)
 * @see SimpleMasonError#hashCode()
 */
@Getter
@Setter
@ToString
@SuperBuilder
@JsonRootName(Tokens.Body.ERROR)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SimpleMasonError implements MasonError {

    @JsonCreator
    public SimpleMasonError(@NotNull @NonNull @JsonProperty(MESSAGE) String message) {
        this.message = message;
    }

    @NotNull
    @NonNull
    private String message;
    @Singular(value = "addMessage")
    private Collection<String> messages = new LinkedHashSet<>();
    @Singular
    private Map<String, MasonControl> controls = new LinkedHashMap<>();
    private String id;
    private String code;
    private String details;
    private Integer httpStatusCode;
    private Instant time;

    /**
     * Two errors are equal if:
     * <p>
     * {@link MasonError#getId()} is present for both and equal
     * </p>
     * <b>OR</b>
     * <p>
     * {@link MasonError#getMessage()} are equal
     * </p>
     * <br>
     * Mostly useful for debug
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof MasonError) {
            var e = (MasonError) o;
            return getId() != null && e.getId() != null
                   ? Objects.equals(getId(), e.getId())
                   : Objects.equals(getMessage(), e.getMessage());
        }
        return false;
    }

    /**
     * Returns the hashcode of {@link MasonError} class
     */
    @Override
    public int hashCode() {
        return MasonError.class.hashCode();
    }
}
