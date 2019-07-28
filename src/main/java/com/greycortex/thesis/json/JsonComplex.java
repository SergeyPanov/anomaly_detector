package com.greycortex.thesis.json;

import com.greycortex.thesis.schema.ERDTable;

import java.util.*;

public class JsonComplex extends JsonAbstract {
    private static final int ONLY_INDEX = 0;

    private ArrayList<JsonAbstract> elements;

    public JsonComplex(String name, Set<Type> type) {
        super(name, type);
        this.elements = new ArrayList<>();
    }

    public JsonComplex() {
        this(null, null);
    }

    public JsonAbstract get(int i) {
        return elements.get(i);
    }

    public void add(int index, JsonAbstract el) {
        elements.add(index, el);
    }

    public void add(JsonAbstract el) {elements.add(el);}

    private boolean areOnlySimples() {
        for (JsonAbstract el :
                elements) {
            for (Type t:
                    el.getType()) {
                if (!t.isSimple()) return false;
            }
        }
        return true;
    }


    public boolean isArrayOfSimples() {
        return
                getType().iterator().next() == Type.ARRAY &&
                        // has only one children with simple type(type of the children is made with only one "Type" with is simple)
                        areOnlySimples();
    }

    public ArrayList<JsonAbstract> getChildren() {
        return elements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        JsonComplex complex = (JsonComplex) o;
        return Objects.equals(elements, complex.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), elements);
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

        if (elements.size() > 0) {
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
