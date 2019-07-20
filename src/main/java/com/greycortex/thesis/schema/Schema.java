package com.greycortex.thesis.schema;

import java.util.Stack;

/**
 * Schema is a list of tables.
 */
public class Schema {
    private Stack<ERDTable> tables;

    public Schema(Stack<ERDTable> tables) {
        this.tables = tables;
    }
}
