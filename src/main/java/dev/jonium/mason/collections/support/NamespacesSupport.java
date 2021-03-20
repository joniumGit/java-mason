package dev.jonium.mason.collections.support;

import dev.jonium.mason.collections.Namespaces;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

import java.util.Map;

public interface NamespacesSupport {

    @NotNull Namespaces getNamespaces();

    default String addNamespace(
            @NotNull @NonNull String prefix,
            @NotNull @NonNull String uri
    )
    {
        return getNamespaces().put(prefix, uri);
    }

    default void addNamespaces(@NotNull @NonNull Map<String, String> namespaces) {
        getNamespaces().putAll(namespaces);
    }

    default String removeNamespace(@NotNull @NonNull String prefix)
    {
        return getNamespaces().remove(prefix);
    }

    default void clearNamespaces() {
        getNamespaces().clear();
    }

}
