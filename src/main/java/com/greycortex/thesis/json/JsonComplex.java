package com.greycortex.thesis.json;

import com.greycortex.thesis.schema.ERDTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class JsonComplex extends JsonAbstract {
    private static final int ONLY_INDEX = 0;

    private List<JsonAbstract> elements;

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

    private boolean isSimpleArray() {
        return
                // This element is array(type is with only one "Type" which is "ARRAY")
                getType().size() == 1 &&
                        getType().iterator().next() == Type.ARRAY &&
                        // has only one children with simple type(type of the children is made with only one "Type" with is simple)
                        elements.size() == 1 &&
                        elements.get(ONLY_INDEX).getType().size() == 1 &&
                        elements.get(ONLY_INDEX).getType().iterator().next().isSimple();
    }

    private boolean isOneToOne() {
        return
                getType().size() == 1 &&
                getType().iterator().next() == Type.OBJECT;
    }

    private boolean isManyToMany() {
        return
                // This element is array(type is with only one "Type" which is "ARRAY")
                getType().size() == 1 &&
                        getType().iterator().next() == Type.ARRAY &&
                        // has only one children with simple type(type of the children is made with only one "Type" with is simple)
                        elements.size() == 1 &&
                        elements.get(ONLY_INDEX).getType().size() == 1 &&
                        !elements.get(ONLY_INDEX).getType().iterator().next().isSimple();
    }

    public ERDTable getTable() {

        ERDTable table = new ERDTable(getName());

        for (JsonAbstract el :
                elements) {

            if (el instanceof JsonSimple) {
                // If element is simple this is just columns in the output table
                table.setColumn(el.getName(), el.getType().iterator().next().getValue());
            } else {
                JsonComplex complex = (JsonComplex) el;
                if (complex.isSimpleArray()) {
                    table.setColumn(el.getName(), Type.ARRAY.getValue() + ((JsonComplex) el).get(ONLY_INDEX).getType().iterator().next());
                } else if (complex.isOneToOne()) {
                    // TODO: Finish this
                    System.out.println("One-To-One");
                } else if (complex.isManyToMany()) {
                    // TODO: Finish this
                    System.out.println("One-To-Many");
                }
            }
        }
        return table;
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
