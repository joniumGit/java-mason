module dev.jonium.mason {
    requires static lombok;
    requires static jakarta.validation;

    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;

    opens dev.jonium.mason to com.fasterxml.jackson.core, com.fasterxml.jackson.databind;
    opens dev.jonium.mason.core to com.fasterxml.jackson.core, com.fasterxml.jackson.databind;
    opens dev.jonium.mason.core.control to com.fasterxml.jackson.core, com.fasterxml.jackson.databind;
    opens dev.jonium.mason.serialization to com.fasterxml.jackson.core, com.fasterxml.jackson.databind;
    opens dev.jonium.mason.collections to com.fasterxml.jackson.core, com.fasterxml.jackson.databind;
    opens dev.jonium.mason.collections.support to com.fasterxml.jackson.core, com.fasterxml.jackson.databind;

    exports dev.jonium.mason;
    exports dev.jonium.mason.core;
    exports dev.jonium.mason.core.control;
    exports dev.jonium.mason.serialization;
    exports dev.jonium.mason.collections;
    exports dev.jonium.mason.collections.support;
}