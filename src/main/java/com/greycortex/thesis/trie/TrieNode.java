package com.greycortex.thesis.trie;

import com.greycortex.thesis.json.JsonAbstract;

public class TrieNode {
    private JsonAbstract element;

    public void setElement(JsonAbstract element) {
        this.element = element;
    }

    public JsonAbstract getElement() {
        return element;
    }
}
