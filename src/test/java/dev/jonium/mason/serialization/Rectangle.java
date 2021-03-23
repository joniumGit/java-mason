package dev.jonium.mason.serialization;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public final class Rectangle {
    @JsonProperty
    public Integer width = 1;
    @JsonProperty
    public float height = 2.22f;
    @JsonUnwrapped
    public Name name = new Name();
    @JsonProperty
    public Name otherName = new Name();
}
