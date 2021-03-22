package dev.jonium.mason.support;

import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

import java.util.Collection;
import java.util.Objects;

public interface MessagesSupport {

    @NotNull Collection<@NotNull String> getMessages();

    default Boolean addMessage(@NotNull @NonNull String message) {
        return getMessages().add(message);
    }

    default Boolean addMessages(@NotNull @NonNull Collection<@NotNull String> messages) {
        if (messages.parallelStream().anyMatch(Objects::isNull)) {
            throw new NullPointerException("Null value");
        }
        return getMessages().addAll(messages);
    }

    default Boolean removeMessage(@NotNull @NonNull String message) {
        return getMessages().remove(message);
    }

    default Boolean removeMessages(@NotNull @NonNull Collection<@NotNull String> messages) {
        return getMessages().removeAll(messages);
    }

    default void clearMessages() {
        getMessages().clear();
    }

}
