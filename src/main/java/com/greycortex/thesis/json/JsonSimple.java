package com.greycortex.thesis.json;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

public class JsonSimple extends JsonAbstract {
    private Number max = null;
    private Number min = null;

    private String format;

    public JsonSimple(String name, Set<Type> type, String format) {
        super(name, type);
        this.format = format;
    }

    public JsonSimple(String name, Set<Type> type) {
        this(name, type, null);
    }


    private JsonSimple() {
        this(null, null, null);
    }


    public Number getMin() {
        return min;
    }

    public Number getMax() {
        return max;
    }


    public void setMax(Number max) {
        this.max = max;
    }

    public void setMin(Number min) {
        this.min = min;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        JsonSimple that = (JsonSimple) o;
        return Objects.equals(max, that.max) &&
                Objects.equals(min, that.min) &&
                Objects.equals(format, that.format);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), max, min, format);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{")
                .append("name: ")
                .append(getName())
                .append(", ")
                .append("type: ")
                .append(getType())
                .append(", ")
                .append("min: ")
                .append(min)
                .append(", ")
                .append("max: ")
                .append(max)
                .append(", ")
                .append("format: ")
                .append(format)
                .append("}");
        return builder.toString();
    }

    public String getFormat() {
        return format;
    }
}
