package com.relation.tag.enums;

import org.apache.logging.log4j.util.Strings;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface ResponseCode {
    Map<String, String> ERROR_MESSAGE = new ConcurrentHashMap();

    String symbol();

    int code();

    default String errorMessage(String language) {
        if (Strings.isBlank(language)) {
            language = "EN-US";
        }

        return (String) ERROR_MESSAGE.get(String.join(".", this.symbol(), language.toUpperCase()));
    }

    default String errorMessage() {
        return "";
    }

    static void initErrorMessage(Map<String, String> errorMessage) {
        ERROR_MESSAGE.putAll(errorMessage);
    }
}

