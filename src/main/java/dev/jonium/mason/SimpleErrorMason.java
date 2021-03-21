package dev.jonium.mason;

import dev.jonium.mason.fields.SimpleMasonError;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Simple implementation of {@link ErrorMason}
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SimpleErrorMason implements ErrorMason<SimpleMasonError> {
    private SimpleMasonError error;
}
