package dev.jonium.mason;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.jonium.mason.annotations.ArrayType;
import dev.jonium.mason.annotations.ControlType;
import dev.jonium.mason.impl.SimpleMasonError;
import dev.jonium.mason.serialization.RFC3339DateUtils;
import dev.jonium.mason.support.ControlsSupport;
import dev.jonium.mason.support.MessagesSupport;
import jakarta.validation.constraints.NotNull;

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
 * @see SimpleMasonError
 */
@JsonDeserialize(as = SimpleMasonError.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public interface MasonError extends ControlsSupport, MessagesSupport {

    @JsonProperty(value = MESSAGE, required = true)
    @NotNull String getMessage();

    @ArrayType
    @JsonProperty(MESSAGES)
    Collection<@NotNull String> getMessages();

    @ControlType
    @JsonProperty(CONTROLS)
    @NotNull Map<@NotNull String, @NotNull MasonControl> getControls();

    @JsonProperty(ID)
    String getId();

    @JsonProperty(CODE)
    String getCode();

    @JsonProperty(DETAILS)
    String getDetails();

    @JsonProperty(HTTP_STATUS_CODE)
    Integer getHttpStatusCode();

    /**
     * @see RFC3339DateUtils
     */
    @JsonProperty(TIME)
    String getTime();

    @JsonProperty(value = MESSAGE, required = true)
    void setMessage(String message);

    @ArrayType
    @JsonSetter(value = MESSAGES, nulls = Nulls.FAIL)
    void setMessages(Collection<@NotNull String> messages);

    @ControlType
    @JsonSetter(value = CONTROLS, nulls = Nulls.FAIL)
    void setControls(@NotNull Map<@NotNull String, @NotNull MasonControl> controls);

    @JsonSetter(value = ID, nulls = Nulls.FAIL)
    void setId(String id);

    @JsonSetter(value = CODE, nulls = Nulls.FAIL)
    void setCode(String code);

    @JsonSetter(value = DETAILS, nulls = Nulls.FAIL)
    void setDetails(String details);

    @JsonSetter(value = HTTP_STATUS_CODE, nulls = Nulls.FAIL)
    void setHttpStatusCode(Integer httpStatusCode);

    /**
     * @see RFC3339DateUtils
     */
    @JsonSetter(value = TIME, nulls = Nulls.FAIL)
    void setTime(String time);

}
