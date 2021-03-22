module dev.jonium.mason {
    requires static lombok;
    requires static jakarta.validation;

    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;

    opens dev.jonium.mason to com.fasterxml.jackson.core, com.fasterxml.jackson.databind;
    opens dev.jonium.mason.impl to com.fasterxml.jackson.core, com.fasterxml.jackson.databind;
    opens dev.jonium.mason.features to com.fasterxml.jackson.core, com.fasterxml.jackson.databind;
    opens dev.jonium.mason.support to com.fasterxml.jackson.core, com.fasterxml.jackson.databind;
    opens dev.jonium.mason.annotations to com.fasterxml.jackson.core, com.fasterxml.jackson.databind;
    opens dev.jonium.mason.serialization to com.fasterxml.jackson.core, com.fasterxml.jackson.databind;

    exports dev.jonium.mason;
    exports dev.jonium.mason.impl;
    exports dev.jonium.mason.features;
    exports dev.jonium.mason.support;
    exports dev.jonium.mason.annotations;
    exports dev.jonium.mason.serialization;
}