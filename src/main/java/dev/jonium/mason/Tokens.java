package dev.jonium.mason;

public interface Tokens {

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
