package com.greycortex.thesis.trie;

import com.greycortex.thesis.json.*;
import javafx.util.Pair;
import org.json.simple.JSONObject;

import java.util.Map;
import java.util.Set;
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

        Stack<Pair<JsonComplex, Set<Map.Entry> >> newStack = new Stack<>();   // First is root, second is

        newStack.push(new Pair<>(root, properties.entrySet()));

        // Trie stack
        Stack<JsonAbstract> trieStack = new Stack<>();
        trieStack.push(root);

        // Stack of values
        Stack<Map.Entry> inputStack = new Stack<>();
        inputStack.addAll(properties.entrySet());

        while (!inputStack.isEmpty()) {

            Pair pr = newStack.pop();

            // Value node
            Map.Entry inputNode = inputStack.pop();
            String name = (String) inputNode.getKey();
            JsonWrapper value = new JsonWrapper((JSONObject) inputNode.getValue());
            //////////////////////////////////////////////////////////////////////

            // Sub-root
            JsonComplex schemaNode = (JsonComplex) trieStack.pop();
            //////////////////////////////////////////////////////////////////////


            if (value.getType() == Types.STRING || value.getType() == Types.NUMBER || value.getType() == Types.INTEGER) {
                // Branch for simple object(string, number)
                JsonSimple simpleElement = new JsonSimple(name, value.getString(SchemaKeys.TYPE));

                String maxK = Types.valueOf(value.getString(SchemaKeys.TYPE).toUpperCase()) == Types.NUMBER ||
                        Types.valueOf(value.getString(SchemaKeys.TYPE).toUpperCase()) == Types.INTEGER ? SchemaKeys.MAXIMUM : SchemaKeys.MAX_LENGTH;

                String minK = Types.valueOf(value.getString(SchemaKeys.TYPE).toUpperCase()) == Types.NUMBER ||
                        Types.valueOf(value.getString(SchemaKeys.TYPE).toUpperCase()) == Types.INTEGER ? SchemaKeys.MINIMUM : SchemaKeys.MIN_LENGTH;

                simpleElement.setMax(value.getNumber(maxK, null));
                simpleElement.setMin(value.getNumber(minK, null));

                // Add element to result trie
                schemaNode.add(0, simpleElement);

                // Add sub-trie back to stack
                trieStack.push(schemaNode);
            } else {
                Types type = value.getType();
                switch (type) {
                    case OBJECT:
                        JsonComplex auxNode = new JsonComplex(name, value.getString(SchemaKeys.TYPE));
                        schemaNode.add(0, auxNode);
                        trieStack.push(schemaNode); // Push back root
                        trieStack.push(auxNode);
                        inputStack.addAll(value.getWrapped(SchemaKeys.PROPERTIES).entrySet());

                }
            }

        }

    }


    @Override
    public String toString() {
        return root.toString();
    }
}
