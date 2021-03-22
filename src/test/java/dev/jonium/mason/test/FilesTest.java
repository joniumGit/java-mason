package dev.jonium.mason.test;

import dev.jonium.mason.impl.SimpleMasonControl;
import dev.jonium.mason.impl.SimpleMasonFileDescriptor;
import dev.jonium.mason.serialization.Tokens;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("File descriptor tests")
public class FilesTest {

    @Test
    @DisplayName("Serialize and test types")
    void serialize() {
        var fd = SimpleMasonFileDescriptor.builder()
                                          .title("test")
                                          .accept("test")
                                          .accept("test1")
                                          .description("test")
                                          .build();
        var tree = Utils.toTree(SimpleMasonControl.builder().href("test").fileDescriptor(fd).build());
        var files = tree.get(Tokens.Controls.FILES);
        Assertions.assertTrue(files.isArray());
        var file = files.get(0);
        Assertions.assertTrue(file.get(Tokens.Controls.File.ACCEPT).isArray());
    }


}
