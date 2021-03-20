package dev.jonium.mason.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.jonium.mason.collections.Alts;
import dev.jonium.mason.collections.CollectionType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static dev.jonium.mason.Tokens.Controls.ALT;
import static dev.jonium.mason.Tokens.Controls.HREF;

@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Control extends Alt {

    @JsonCreator
    public Control(
            @NotNull @JsonProperty(HREF) String href
    )
    {
        super(href);
    }

    @Getter
    @Setter
    @JsonProperty(ALT)
    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    @CollectionType
    private Alts alts = new Alts();

}
