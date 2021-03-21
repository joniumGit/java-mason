package dev.jonium.mason.support;

import dev.jonium.mason.fields.MasonFileDescriptor;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

import java.util.Collection;

public interface FileDescriptorSupport {

    @NotNull Collection<MasonFileDescriptor> getFileDescriptors();

    default Boolean addFileDescriptor(@NotNull @NonNull MasonFileDescriptor fd) {
        return getFileDescriptors().add(fd);
    }

    default Boolean removeFileDescriptor(@NotNull @NonNull MasonFileDescriptor fd) {
        return getFileDescriptors().remove(fd);
    }

    default Boolean addFileDescriptors(@NotNull @NonNull Collection<MasonFileDescriptor> fds) {
        return getFileDescriptors().addAll(fds);
    }

    default Boolean removeFileDescriptors(@NotNull @NonNull Collection<MasonFileDescriptor> fds) {
        return getFileDescriptors().removeAll(fds);
    }

    default void clearFileDescriptors() {
        getFileDescriptors().clear();
    }

}
