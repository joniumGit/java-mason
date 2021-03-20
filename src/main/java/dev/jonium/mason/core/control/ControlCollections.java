package dev.jonium.mason.core.control;

import dev.jonium.mason.collections.FileDescriptors;
import dev.jonium.mason.collections.support.AcceptsSupport;
import dev.jonium.mason.collections.support.FileDescriptorSupport;
import dev.jonium.mason.collections.support.OutputSupport;

import java.util.Collection;

public interface ControlCollections extends FileDescriptorSupport, AcceptsSupport, OutputSupport {

    void setFileDescriptors(FileDescriptors fileDescriptors);

    void setOutputs(Collection<String> outputs);

    void setAccepts(Collection<String> accepts);

}
