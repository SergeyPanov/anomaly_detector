package com.greycortex.thesis.trie;

import com.greycortex.thesis.json.*;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.json.simple.JSONObject;

import java.util.*;

public class Tree {
    private JsonWrapper object;

    private Node root;

    public Tree(JsonWrapper object) {
        this.object = object;
        root = new Node(new JsonComplex(null, null));
        init();
    }

    public Tree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    /**
     * If @type contains more than 1 element or OBJECT/ARRAY then the type is complex.
     *
     * @param type
     * @return
     */
    private boolean isSimpleType(Set<Type> type) {
        if (type.size() != 1) {
            return false;
        } else {
            return !(type.contains(Type.OBJECT) || type.contains(Type.ARRAY));
        }
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


        Stack<Pair<Node, Stack<Map.Entry>>> trieConstructStack = new Stack<>();   // First is root, second are children
        trieConstructStack.push(new MutablePair<>(root, inputStack));

        while (!trieConstructStack.isEmpty()) {

            Pair pr = trieConstructStack.pop();

            Node currentRoot = (Node) pr.getKey();

            Stack values = (Stack) pr.getValue();

            while (!values.isEmpty()) {

                Map.Entry inputNode = (Map.Entry) values.pop();
                String name = (String) inputNode.getKey();
                JsonWrapper value = new JsonWrapper((JSONObject) inputNode.getValue());

                if (isSimpleType(value.getType())) {

                    // Branch for simple object(string, number)
                    JsonSimple simpleElement = new JsonSimple(name, value.getType(), value.getString(SchemaKeys.FORMAT, null));

                    Type type = simpleElement.getType().iterator().next();

                    String maxK = type == Type.NUMBER ||
                            type == Type.INTEGER ? SchemaKeys.MAXIMUM : SchemaKeys.MAX_LENGTH;

                    String minK = type == Type.NUMBER ||
                            type == Type.INTEGER ? SchemaKeys.MINIMUM : SchemaKeys.MIN_LENGTH;

                    simpleElement.setMax(value.getNumber(maxK, null));
                    simpleElement.setMin(value.getNumber(minK, null));

                    currentRoot.add(0, new Node(simpleElement));

                } else {
                    Set<Type> complexType = value.getType();
                    // ARRAY/OBJECT are complex types
                    if (complexType.size() == 1) {
                        Type type = complexType.iterator().next();
                        switch (type) {
                            case OBJECT:
                                Node auxNode = new Node(new JsonComplex(name, value.getType()));
                                currentRoot.add(0, auxNode);
                                Stack<Map.Entry> nextInput = new Stack<>();
                                nextInput.addAll(value.getWrapped(SchemaKeys.PROPERTIES).entrySet());
                                trieConstructStack.push(new MutablePair<>(auxNode, nextInput));
                                break;
                            case ARRAY:
                                auxNode = new Node(new JsonComplex(name, value.getType()));
                                currentRoot.add(0, auxNode);
                                nextInput = new Stack<>();
                                Map.Entry<String, JSONObject> entry = new AbstractMap.SimpleEntry<>(name, value.getWrapped(SchemaKeys.ITEMS).getObject());
                                nextInput.add(entry);
                                trieConstructStack.push(new MutablePair<>(auxNode, nextInput));
                                break;
                            default:
                                break;
                        }
                    } else {
                        // Heterogeneous array
                        Node auxNode = new Node(new JsonComplex(name, value.getType()));
                        currentRoot.add(0, auxNode);
                        Stack<Map.Entry> nextInput = new Stack<>();

                        // Store simple parameters form complex object
                        if (complexType.contains(Type.INTEGER) ||
                                complexType.contains(Type.NUMBER) ||
                                complexType.contains(Type.BOOLEAN) ||
                                complexType.contains(Type.NULL)) {
                            JSONObject dummy = new JSONObject();

                            dummy.put(SchemaKeys.MINIMUM, value.getNumber(SchemaKeys.MINIMUM, null));
                            dummy.put(SchemaKeys.MAXIMUM, value.getNumber(SchemaKeys.MAXIMUM, null));
                            dummy.put(SchemaKeys.TYPE, Type.INTEGER.getValue());

                            Map.Entry<String, JSONObject> entry = new AbstractMap.SimpleEntry<>(name, dummy);

                            nextInput.add(entry);
                        }
                        if (complexType.contains(Type.STRING)) {
                            JSONObject dummy = new JSONObject();

                            dummy.put(SchemaKeys.MIN_LENGTH, value.getNumber(SchemaKeys.MIN_LENGTH, null));
                            dummy.put(SchemaKeys.MAX_LENGTH, value.getNumber(SchemaKeys.MAX_LENGTH, null));
                            dummy.put(SchemaKeys.TYPE, Type.STRING.getValue());

                            Map.Entry<String, JSONObject> entry = new AbstractMap.SimpleEntry<>(name, dummy);

                            nextInput.add(entry);
                        }

                        if (complexType.contains(Type.ARRAY)) {

                            JSONObject dummy = new JSONObject();

                            dummy.put(SchemaKeys.ITEMS, value.getWrapped(SchemaKeys.ITEMS).getObject());
                            dummy.put(SchemaKeys.TYPE, Type.ARRAY.getValue());

                            Map.Entry<String, JSONObject> entry = new AbstractMap.SimpleEntry<>(name, dummy);
                            nextInput.add(entry);
                        }


                        if (complexType.contains(Type.OBJECT)) {
                            JSONObject dummy = new JSONObject();

                            dummy.put(SchemaKeys.PROPERTIES, value.getWrapped(SchemaKeys.PROPERTIES).getObject());
                            dummy.put(SchemaKeys.TYPE, Type.OBJECT.getValue());

                            Map.Entry<String, JSONObject> entry = new AbstractMap.SimpleEntry<>(name, dummy);
                            nextInput.add(entry);
                        }
                        trieConstructStack.push(new MutablePair<>(auxNode, nextInput));

                    }
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tree tree = (Tree) o;
        return Objects.equals(root, tree.root);
    }

    @Override
    public int hashCode() {
        return Objects.hash(root);
    }

    @Override
    public String toString() {
        return root.toString();
    }
}
