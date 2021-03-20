package dev.jonium.mason.core;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import dev.jonium.mason.Tokens;
import dev.jonium.mason.collections.CollectionType;
import dev.jonium.mason.collections.Controls;
import dev.jonium.mason.collections.support.ControlsSupport;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@JsonRootName(Tokens.Body.META)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Meta implements ControlsSupport {

    @Setter
    @Getter
    @JsonProperty(Tokens.Meta.TITLE)
    private String title;

    @Setter
    @Getter
    @JsonProperty(Tokens.Meta.DESCRIPTION)
    private String description;

    @Getter
    @Setter
    @JsonProperty(Tokens.Meta.CONTROLS)
    @CollectionType
    private Controls controls = new Controls();

}
