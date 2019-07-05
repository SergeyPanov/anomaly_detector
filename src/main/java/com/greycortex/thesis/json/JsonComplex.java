package com.greycortex.thesis.json;

import java.util.ArrayList;
import java.util.List;

public class JsonComplex extends JsonAbstract {
    private List<JsonAbstract> elements;

    public JsonComplex(String name, String type) {
        super(name, type);
        this.elements = new ArrayList<>();
    }

    public JsonAbstract get(int i) {
        return elements.get(i);
    }

    public void add(JsonAbstract el) {
        elements.add(el);
    }
}
