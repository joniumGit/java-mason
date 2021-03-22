package dev.jonium.mason.support;

import dev.jonium.mason.MasonFileDescriptor;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

import java.util.Collection;
import java.util.Objects;

public interface FileDescriptorSupport {

    @NotNull Collection<@NotNull MasonFileDescriptor> getFileDescriptors();

    default Boolean addFileDescriptor(@NotNull @NonNull MasonFileDescriptor fd) {
        return getFileDescriptors().add(fd);
    }

    default Boolean removeFileDescriptor(@NotNull @NonNull MasonFileDescriptor fd) {
        return getFileDescriptors().remove(fd);
    }

    default Boolean addFileDescriptors(@NotNull @NonNull Collection<@NotNull MasonFileDescriptor> fds) {
        if (fds.parallelStream().anyMatch(Objects::isNull)) {
            throw new NullPointerException("Null value");
        }
        return getFileDescriptors().addAll(fds);
    }

    default Boolean removeFileDescriptors(@NotNull @NonNull Collection<@NotNull MasonFileDescriptor> fds) {
        return getFileDescriptors().removeAll(fds);
    }

    default void clearFileDescriptors() {
        getFileDescriptors().clear();
    }

}
