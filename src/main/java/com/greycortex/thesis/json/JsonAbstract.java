package com.greycortex.thesis.json;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class JsonAbstract {
    private String name;

    private Set<Type> type;

    public JsonAbstract(String name, Set<Type> type) {
        this.name = name;
        this.type = type;
    }

    public JsonAbstract(String name){
        this(name, new HashSet<>());
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

    public Set<Type> getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JsonAbstract that = (JsonAbstract) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }
}
