package com.greycortex.thesis.json;

public abstract class JsonAbstract {
    private String name;

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    private Types type;

    public JsonAbstract(String name, Types type) {
        this.name = name;
        this.type = type;
    }

    public JsonAbstract() {
        this(null, null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
