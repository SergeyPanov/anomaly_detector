package com.greycortex.thesis.trie;

import com.greycortex.thesis.json.*;
import javafx.util.Pair;
import org.json.simple.JSONObject;

import java.util.Map;
import java.util.Stack;

public class Trie {
    private JsonWrapper object;

    private JsonComplex root;

    public Trie(JsonWrapper object) {
        this.object = object;
        root = new JsonComplex();
        init();
    }


    /**
     * Construct trie.
     */
    private void init() {

        JsonWrapper properties = object.getWrapped(SchemaKeys.PROPERTIES);
        if (properties == null) {
            root = null;
            return;
        }

        // Stack of values
        Stack<Map.Entry> inputStack = new Stack<>();
        inputStack.addAll(properties.entrySet());


        Stack<Pair<JsonComplex, Stack<Map.Entry>>> trieConstructStack = new Stack<>();   // First is root, second are children
        trieConstructStack.push(new Pair<>(root, inputStack));

        while (!trieConstructStack.isEmpty()) {

            Pair pr = trieConstructStack.pop();

            JsonComplex currentRoot = (JsonComplex) pr.getKey();

            Stack values = (Stack) pr.getValue();

            while (!values.isEmpty()) {

                Map.Entry inputNode = (Map.Entry) values.pop();
                String name = (String) inputNode.getKey();
                JsonWrapper value = new JsonWrapper((JSONObject) inputNode.getValue());

                if (value.getType() == Types.STRING || value.getType() == Types.NUMBER || value.getType() == Types.INTEGER) {

                    // Branch for simple object(string, number)
                    JsonSimple simpleElement = new JsonSimple(name, value.getString(SchemaKeys.TYPE));

                    String maxK = Types.valueOf(value.getString(SchemaKeys.TYPE).toUpperCase()) == Types.NUMBER ||
                            Types.valueOf(value.getString(SchemaKeys.TYPE).toUpperCase()) == Types.INTEGER ? SchemaKeys.MAXIMUM : SchemaKeys.MAX_LENGTH;

                    String minK = Types.valueOf(value.getString(SchemaKeys.TYPE).toUpperCase()) == Types.NUMBER ||
                            Types.valueOf(value.getString(SchemaKeys.TYPE).toUpperCase()) == Types.INTEGER ? SchemaKeys.MINIMUM : SchemaKeys.MIN_LENGTH;

                    simpleElement.setMax(value.getNumber(maxK, null));
                    simpleElement.setMin(value.getNumber(minK, null));

                    currentRoot.add(0, simpleElement);

                } else {
                    Types type = value.getType();
                    switch (type) {
                        case OBJECT:
                            JsonComplex auxNode = new JsonComplex(name, value.getString(SchemaKeys.TYPE));
                            currentRoot.add(0, auxNode);
                            Stack<Map.Entry> nextInput = new Stack<>();
                            nextInput.addAll(value.getWrapped(SchemaKeys.PROPERTIES).entrySet());
                            trieConstructStack.push(new Pair<>(auxNode, nextInput));

                    }
                }
            }
        }

        return;

    }


    @Override
    public String toString() {
        return root.toString();
    }
}
