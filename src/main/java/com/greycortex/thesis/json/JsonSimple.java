package com.greycortex.thesis.json;

public class JsonSimple extends JsonAbstract {
    private Number max = -1;
    private Number min = -1;

    public JsonSimple(String name, String type) {
        super(name, type);
    }

    private JsonSimple() {
        this(null, null);
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
                .append("}");
        return builder.toString();
    }
}
