package dev.jonium.mason.fields;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.jonium.mason.annotations.ArrayType;
import dev.jonium.mason.annotations.ControlType;
import dev.jonium.mason.serialization.InstantDeserializer;
import dev.jonium.mason.serialization.InstantSerializer;
import dev.jonium.mason.serialization.Tokens;
import dev.jonium.mason.support.ControlsSupport;
import dev.jonium.mason.support.MessagesSupport;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.Collection;
import java.util.Map;

import static dev.jonium.mason.serialization.Tokens.Error.*;

/**
 * Represents the Error attribute in Mason document.
 * <p>
 * According to the standard this class can be freely extended to add custom properties
 * </p>
 * <p>
 * By default deserializes as {@link SimpleMasonError}
 * </p>
 *
 * @see SimpleMasonError#equals(Object)
 * @see SimpleMasonError#hashCode()
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(as = SimpleMasonError.class)
public interface MasonError extends ControlsSupport, MessagesSupport {

    @NotNull
    @JsonProperty(Tokens.Error.MESSAGE)
    String getMessage();

    @ArrayType
    @JsonProperty(MESSAGES)
    Collection<String> getMessages();

    @ControlType
    @JsonProperty(CONTROLS)
    Map<String, MasonControl> getControls();

    @JsonProperty(ID)
    String getId();

    @JsonProperty(CODE)
    String getCode();

    @JsonProperty(DETAILS)
    String getDetails();

    @JsonProperty(HTTP_STATUS_CODE)
    Integer getHttpStatusCode();

    @JsonSerialize(using = InstantSerializer.class)
    @JsonProperty(TIME)
    Instant getTime();

    @JsonSetter(MESSAGE)
    void setMessage(String message);

    @ArrayType
    @JsonSetter(MESSAGES)
    void setMessages(Collection<String> messages);

    @ControlType
    @JsonSetter(CONTROLS)
    void setControls(Map<String, MasonControl> controls);

    @JsonSetter(ID)
    void setId(String id);

    @JsonSetter(CODE)
    void setCode(String code);

    @JsonSetter(DETAILS)
    void setDetails(String details);

    @JsonSetter(HTTP_STATUS_CODE)
    void setHttpStatusCode(Integer httpStatusCode);

    @JsonDeserialize(using = InstantDeserializer.class)
    @JsonSetter(TIME)
    void setTime(Instant time);

}
