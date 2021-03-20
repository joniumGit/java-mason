package dev.jonium.mason.core.control;

import dev.jonium.mason.core.Encoding;
import jakarta.validation.constraints.NotNull;

public interface ControlObject extends ControlCollections {

    void setHref(@NotNull String href);

    @NotNull String getHref();

    void setHrefTemplate(Boolean hrefTemplate);

    Boolean getHrefTemplate();

    void setTitle(String title);

    String getTitle();

    void setDescription(String description);

    String getDescription();

    void setMethod(String method);

    String getMethod();

    void setEncoding(Encoding encoding);

    Encoding getEncoding();

    void setSchema(Object schema);

    Object getSchema();

    void setSchemaUrl(String schemaUrl);

    String getSchemaUrl();

    void setTemplate(Object template);

    Object getTemplate();

}
