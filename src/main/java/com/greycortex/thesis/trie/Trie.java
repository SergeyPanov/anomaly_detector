package com.greycortex.thesis.trie;

import com.greycortex.thesis.json.JsonWrapper;

import java.util.Stack;

public class Trie {
    private JsonWrapper object;

    private TrieNode root;

    public Trie(JsonWrapper object) {
        this.object = object;
        root = new TrieNode();
        init();
    }

    private void init() {

        Stack<String> stack = new Stack<>();


    }
}
