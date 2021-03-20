package dev.jonium.mason.collections.support;

import dev.jonium.mason.collections.FileDescriptors;
import dev.jonium.mason.core.FileDescriptor;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

import java.util.Collection;
import java.util.Comparator;

public interface FileDescriptorSupport {

    @NotNull FileDescriptors getFileDescriptors();

    default Boolean addFileDescriptor(@NotNull @NonNull FileDescriptor fd) {
        return getFileDescriptors().add(fd);
    }

    default Boolean removeFileDescriptor(@NotNull @NonNull FileDescriptor fd) {
        return getFileDescriptors().remove(fd);
    }

    default Boolean addFileDescriptors(@NotNull @NonNull Collection<FileDescriptor> fds) {
        return getFileDescriptors().addAll(fds);
    }

    default Boolean removeFileDescriptors(@NotNull @NonNull Collection<FileDescriptor> fds) {
        return getFileDescriptors().removeAll(fds);
    }

    default void sortFileDescriptors(@NotNull @NonNull Comparator<FileDescriptor> comparator) {
        getFileDescriptors().sort(comparator);
    }

    default void clearFileDescriptors() {
        getFileDescriptors().clear();
    }

}
