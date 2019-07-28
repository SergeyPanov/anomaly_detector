package com.greycortex.thesis.schema;

public enum DBTypes {

    VARCHAR("string"),
    NUMERIC("number"),
    BOOLEAN("boolean"),
    INTEGER("integer"),
    NULL("null");


    private String value;


    public static DBTypes getEnum(String name) {
        if (VARCHAR.value.equals(name)) return VARCHAR;
        if (NUMERIC.value.equals(name)) return NUMERIC;
        if (BOOLEAN.value.equals(name)) return BOOLEAN;
        if (INTEGER.value.equals(name)) return INTEGER;
        return NULL;
    }

    DBTypes(String value) {
        this.value = value;
    }

}
