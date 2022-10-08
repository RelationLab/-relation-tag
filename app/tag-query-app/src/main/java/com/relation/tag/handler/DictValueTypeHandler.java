package com.relation.tag.handler;

public interface DictValueTypeHandler<T> {
    T parse(String value);
}
