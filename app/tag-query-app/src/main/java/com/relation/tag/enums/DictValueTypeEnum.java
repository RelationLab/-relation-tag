package com.relation.tag.enums;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.relation.tag.handler.DictValueTypeHandler;

import java.math.BigDecimal;
import java.util.List;

public enum DictValueTypeEnum implements DictValueTypeHandler {
    STRING {
        @Override
        public String parse(String value) {
            return value;
        }
    }, BIGDECIMAL {
        @Override
        public Object parse(String value) {
            return new BigDecimal(value);
        }
    }, JSONOBJECT {
        @Override
        public JSONObject parse(String value) {
            return JSON.parseObject(value);
        }
    }, ARRAY {
        @Override
        public List<String> parse(String value) {
            return JSON.parseArray(value, String.class);
        }
    }, INTEGER {
        @Override
        public Integer parse(String value) {
            return Integer.valueOf(value);
        }
    }, LONG {
        @Override
        public Long parse(String value) {
            return Long.valueOf(value);
        }
    }, BOOLEAN {
        @Override
        public Boolean parse(String value) {
            return Boolean.valueOf(value);
        }
    }, DOUBLE {
        @Override
        public Double parse(String value) {
            return Double.valueOf(value);
        }
    };

}
