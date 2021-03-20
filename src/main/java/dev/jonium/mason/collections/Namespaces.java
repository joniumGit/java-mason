package dev.jonium.mason.collections;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.jonium.mason.Tokens;
import dev.jonium.mason.serialization.NamespaceValueDeserializer;
import dev.jonium.mason.serialization.NamespaceValueSerializer;

import java.util.LinkedHashMap;

/**
 * Container and JAckson support class for Mason Namespaces
 * <p>
 * Deserialized and Serialized as a Map using
 * {@link NamespaceValueSerializer}
 * and
 * {@link NamespaceValueDeserializer}.
 * <br>
 * <br>
 * Map keys represent the Namespace <b>prefix</b> and values the <b>name/uri</b> value
 * <br>
 * <p>
 * Example:
 * <pre>
 * "storage:view-all" : {
 *      "name" : "https://localhost:8000/api/storage/"
 * },
 * "KEY" : {
 *      "name" : "VALUE"
 * }
 * </pre>
 *
 * @see Namespaces
 * @see NamespaceValueSerializer
 * @see NamespaceValueDeserializer
 */
@JsonRootName(Tokens.Body.NAMESPACES)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonSerialize(using = NamespaceValueSerializer.class)
@JsonDeserialize(using = NamespaceValueDeserializer.class)
public class Namespaces extends LinkedHashMap<String, String> {}
