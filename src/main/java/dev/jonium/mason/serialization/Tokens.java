package dev.jonium.mason.serialization;

/**
 * Contains the reserved token names in a Mason document
 * <p>
 * All classes that allow extending use "@" in the beginning of their properties.
 * </p>
 *
 * @see <a href=https://github.com/JornWildt/Mason/blob/master/Documentation/Mason-draft-2.md>Mason standard (Github URL)</a>
 */
public interface Tokens {

    /**
     * Mason specific http content type
     */
    String CONTENT_TYPE = "application/vnd.mason+json";

    interface Body {
        String META = "@meta";
        String CONTROLS = "@controls";
        String NAMESPACES = "@namespaces";
        String ERROR = "@error";
    }

    interface Meta {
        String TITLE = "@title";
        String DESCRIPTION = "@description";
        String CONTROLS = "@controls";
    }

    interface Namespaces {
        String NAME = "name";
    }

    interface Controls {
        String HREF = "href";
        String IS_HREF_TEMPLATE = "isHrefTemplate";
        String TITLE = "title";
        String DESCRIPTION = "description";
        String METHOD = "method";
        String ENCODING = "encoding";
        String SCHEMA = "schema";
        String SCHEMA_URL = "schemaUrl";
        String TEMPLATE = "template";
        String ACCEPT = "accept";
        String OUTPUT = "output";
        String FILES = "files";
        String ALT = "alt";

        interface Encoding {
            String NONE = "none";
            String JSON = "json";
            String JSON_FILES = "json+files";
            String RAW = "raw";
        }

        interface File {
            String NAME = "name";
            String TITLE = "title";
            String DESCRIPTION = "description";
            String ACCEPT = "accept";
        }
    }

    interface Error {
        String MESSAGE = "@message";
        String ID = "@id";
        String CODE = "@code";
        String MESSAGES = "@messages";
        String DETAILS = "@details";
        String HTTP_STATUS_CODE = "@httpStatusCode";
        String CONTROLS = "@controls";
        String TIME = "@time";
    }
}
