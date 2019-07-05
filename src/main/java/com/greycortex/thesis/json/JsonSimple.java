package com.greycortex.thesis.json;

public class JsonSimple extends JsonAbstract {
    private int max = -1;
    private int min = -1;

    public JsonSimple(String name, String type) {
        super(name, type);
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
