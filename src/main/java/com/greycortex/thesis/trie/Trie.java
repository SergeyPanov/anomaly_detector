package com.greycortex.thesis.trie;

import com.greycortex.thesis.json.*;
import javafx.util.Pair;
import org.json.simple.JSONObject;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

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
                    JsonSimple simpleElement = new JsonSimple(name, value.getType());

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
                            JsonComplex auxNode = new JsonComplex(name, value.getType());
                            currentRoot.add(0, auxNode);
                            Stack<Map.Entry> nextInput = new Stack<>();
                            nextInput.addAll(value.getWrapped(SchemaKeys.PROPERTIES).entrySet());
                            trieConstructStack.push(new Pair<>(auxNode, nextInput));
                            break;

                        case ARRAY:
                            auxNode = new JsonComplex(name, value.getType());
                            currentRoot.add(0, auxNode);
                            nextInput = new Stack<>();
                            Map.Entry<String, JSONObject> entry = new AbstractMap.SimpleEntry<>(SchemaKeys.ITEMS, value.getWrapped(SchemaKeys.ITEMS).getObject());
                            nextInput.add(entry);
                            trieConstructStack.push(new Pair<>(auxNode, nextInput));
                            break;

                        case ARRAY_COMPLEX:
                            auxNode = new JsonComplex(name, value.getType());
                            currentRoot.add(0, auxNode);
                            JSONObject dummy = new JSONObject();
                            nextInput = new Stack<>();

                            List<String> tps = value.getSimpleArray(SchemaKeys.TYPE).stream().map(String::toLowerCase).collect(Collectors.toList());
                            if (tps.contains(Types.STRING.getValue())) {

                                dummy.put(SchemaKeys.MIN_LENGTH, value.getNumber(SchemaKeys.MIN_LENGTH, null));
                                dummy.put(SchemaKeys.MAX_LENGTH, value.getNumber(SchemaKeys.MAX_LENGTH, null));
                                dummy.put(SchemaKeys.TYPE, Types.STRING.getValue());

                                entry = new AbstractMap.SimpleEntry<>(name + "_" + "string", dummy);

                                nextInput.add(entry);
                            }


                            if (tps.contains(Types.INTEGER.getValue())) {
                                dummy = new JSONObject();

                                dummy.put(SchemaKeys.MINIMUM, value.getNumber(SchemaKeys.MINIMUM, null));
                                dummy.put(SchemaKeys.MAXIMUM, value.getNumber(SchemaKeys.MAXIMUM, null));
                                dummy.put(SchemaKeys.TYPE, Types.INTEGER.getValue());

                                entry = new AbstractMap.SimpleEntry<>(name + "_" + "integer", dummy);

                                nextInput.add(entry);
                            }

                            if (tps.contains(Types.NUMBER.getValue())) {
                                dummy = new JSONObject();

                                dummy.put(SchemaKeys.MINIMUM, value.getNumber(SchemaKeys.MINIMUM, null));
                                dummy.put(SchemaKeys.MAXIMUM, value.getNumber(SchemaKeys.MAXIMUM, null));
                                dummy.put(SchemaKeys.TYPE, Types.NUMBER.getValue());

                                entry = new AbstractMap.SimpleEntry<>(name + "_" + "number", dummy);

                                nextInput.add(entry);
                            }

                            if (tps.contains(Types.OBJECT.getValue())) {
                                dummy = new JSONObject();

                                dummy.put(SchemaKeys.PROPERTIES, value.getWrapped(SchemaKeys.PROPERTIES).getObject());
                                dummy.put(SchemaKeys.TYPE, Types.OBJECT.getValue());

                                entry = new AbstractMap.SimpleEntry<>(name + "_" + "object", dummy);
                                nextInput.add(entry);
                            }

                            if (tps.contains(Types.ARRAY.getValue())) {
                                dummy = new JSONObject();

                                dummy.put(SchemaKeys.ITEMS, value.getWrapped(SchemaKeys.ITEMS).getObject());
                                dummy.put(SchemaKeys.TYPE, Types.ARRAY.getValue());

                                entry = new AbstractMap.SimpleEntry<>(name + "_" + "array", dummy);
                                nextInput.add(entry);
                            }
                            trieConstructStack.push(new Pair<>(auxNode, nextInput));
                            break;
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
