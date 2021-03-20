package dev.jonium.mason.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.jonium.mason.Tokens;
import dev.jonium.mason.collections.CollectionType;
import dev.jonium.mason.collections.Controls;
import dev.jonium.mason.collections.Namespaces;
import dev.jonium.mason.collections.support.ControlsSupport;
import dev.jonium.mason.collections.support.NamespacesSupport;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Mason implements ControlsSupport, NamespacesSupport {

    @Getter
    @Setter
    @JsonProperty(Tokens.Body.META)
    private Meta meta;

    @Getter
    @Setter
    @JsonProperty(Tokens.Body.ERROR)
    private Error error;

    @Getter
    @Setter
    @CollectionType
    @JsonProperty(Tokens.Body.CONTROLS)
    private Controls controls = new Controls();

    @Getter
    @Setter
    @CollectionType
    @JsonProperty(Tokens.Body.NAMESPACES)
    private Namespaces namespaces = new Namespaces();

}
