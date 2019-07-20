package com.greycortex.thesis.trie;

import com.greycortex.thesis.json.JsonAbstract;
import com.greycortex.thesis.json.JsonComplex;
import com.greycortex.thesis.json.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Node {
    private JsonAbstract element;

    public Node(JsonAbstract element) {
        this.element = element;
    }

    public JsonAbstract getElement() {
        return element;
    }

    public void setElement(JsonAbstract element) {
        this.element = element;
    }

    public void add(int index, Node el) {
        if (element instanceof JsonComplex) {
            ((JsonComplex) element).add(index, el.getElement());
        }
    }

    public void add(Node el) {
        if (element instanceof JsonComplex) {
            ((JsonComplex) element).add(el.getElement());
        }
    }

    public void addAll(List<Node> nds) {
        if (element instanceof JsonComplex) {
            ((JsonComplex) element).
        }
    }

    public ArrayList<Node> getChildren() {
        ArrayList<Node> children = new ArrayList<>();

        if (element instanceof JsonComplex) {
            JsonComplex complex = (JsonComplex) element;
            complex.getChildren().forEach(child -> children.add(new Node(child)));
        }
        return children;
    }

    public String getName() {
        return element.getName();
    }

    public Set<Type> getType() {
        return element.getType();
    }

    public boolean isObject() {
        return element.isObject();
    }

    public boolean isMixed() {
        return element.isMixed();
    }

    public boolean isArray() {
        return element.isArray();
    }

    /**
     * @return True if the node is complex(ARRAY or OBJECT)
     */
    public boolean isComplex() {
        return element instanceof JsonComplex;
    }


    @Override
    public String toString() {
        return element.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(element, node.element);
    }

    @Override
    public int hashCode() {
        return Objects.hash(element);
    }
}
