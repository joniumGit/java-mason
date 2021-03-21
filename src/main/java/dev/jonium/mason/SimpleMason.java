package dev.jonium.mason;

import dev.jonium.mason.fields.MasonControl;
import dev.jonium.mason.fields.SimpleMasonError;
import dev.jonium.mason.fields.SimpleMasonMeta;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Simple implementation of {@link Mason}
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SimpleMason implements Mason<SimpleMasonMeta, SimpleMasonError> {
    private SimpleMasonMeta meta;
    private SimpleMasonError error;
    @Singular
    private Map<String, MasonControl> controls = new LinkedHashMap<>();
    @Singular
    private Map<String, String> namespaces = new LinkedHashMap<>();
}
