package com.greycortex.thesis.json;

import java.util.ArrayList;
import java.util.List;

public class JsonComplex extends JsonAbstract {
    private List<JsonAbstract> elements;

    public JsonComplex(String name, Types type) {
        super(name, type);
        this.elements = new ArrayList<>();
    }

    public JsonComplex(){
        this(null, null);
    }

    public JsonAbstract get(int i) {
        return elements.get(i);
    }

    public void add(JsonAbstract el) {
        elements.add(el);
    }
    public void add(int index, JsonAbstract el) {
        elements.add(index, el);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append("{")
        .append("name: ")
        .append(getName())
        .append(", ")
        .append("type: ")
        .append(getType());

        if (elements.size() > 0){
            builder.append(", ");
        }

        for (int i = 0; i < elements.size(); i++) {
            builder.append(elements.get(i).toString());
            if (i != elements.size() - 1) {
                builder.append(", ");
            }
        }

        builder.append("}");

        return builder.toString();
    }
}
