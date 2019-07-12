package com.greycortex.thesis.json;

import java.util.ArrayList;
import java.util.Set;

public class JsonSimple extends JsonAbstract {
    private Number max = -1;
    private Number min = -1;

    private String format;

    public JsonSimple(String name, Set<Type> type, String format) {
        super(name, type);
        this.format = format;
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
