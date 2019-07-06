package com.greycortex.thesis.json;

public enum Types {
    STRING("string"),
    NUMBER("number"),
    ARRAY("array"),
    ARRAY_COMPLEX("complex_array"), // Array contains elements of different types
    OBJECT("object"),
    INTEGER("integer");


    private final String value;
    private Types(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
