package dev.jonium.mason.collections;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.jonium.mason.Tokens;
import dev.jonium.mason.core.Control;
import dev.jonium.mason.core.FileDescriptor;

import java.util.ArrayList;

/**
 * Container for {@link FileDescriptor} objects present in a {@link Control}
 * <p>
 * Although this class allows any amount of "equal" descriptors, having more than one of the same kind
 * violates the standard.
 *
 * @see Control
 * @see FileDescriptor
 */
@JsonRootName(Tokens.Controls.FILES)
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
@JsonSerialize(contentAs = FileDescriptor.class)
@JsonDeserialize(contentAs = FileDescriptor.class)
public class FileDescriptors extends ArrayList<FileDescriptor> {}
