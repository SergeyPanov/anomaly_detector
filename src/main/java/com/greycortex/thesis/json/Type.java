package com.greycortex.thesis.json;

public enum Type {
    STRING("string"),
    NUMBER("number"),
    ARRAY("array"),
    OBJECT("object"),
    NULL("null"),
    BOOLEAN("boolean"),
    INTEGER("integer");


    private final String value;
    private Type(String value) {
        this.value = value;
    }

    public static Type getEnum(String name) {
        return Type.valueOf(name.toUpperCase());
    }
    public String getValue() {
        return value;
    }
}
