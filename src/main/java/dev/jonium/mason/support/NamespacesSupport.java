package dev.jonium.mason.support;

import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

import java.util.Map;
import java.util.Objects;

public interface NamespacesSupport {

    @NotNull Map<@NotNull String, @NotNull String> getNamespaces();

    default String addNamespace(
            @NotNull @NonNull String prefix,
            @NotNull @NonNull String uri
    )
    {
        return getNamespaces().put(prefix, uri);
    }

    default void addNamespaces(@NotNull @NonNull Map<@NotNull String, @NotNull String> namespaces) {
        if (namespaces.keySet().parallelStream().anyMatch(Objects::isNull)) {
            throw new NullPointerException("Null key");
        } else if (namespaces.values().parallelStream().anyMatch(Objects::isNull)) {
            throw new NullPointerException("Null value");
        } else {
            getNamespaces().putAll(namespaces);
        }
    }

    default String removeNamespace(@NotNull @NonNull String prefix)
    {
        return getNamespaces().remove(prefix);
    }

    default void clearNamespaces() {
        getNamespaces().clear();
    }
}
