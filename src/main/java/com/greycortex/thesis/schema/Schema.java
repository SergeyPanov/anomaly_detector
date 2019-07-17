package com.greycortex.thesis.schema;

import java.util.ArrayList;

/**
 * Schema is a list of tables.
 */
public class Schema {
    private ArrayList<ERDTable> tables = new ArrayList<>();

    public ArrayList<ERDTable> getTables() {
        return tables;
    }

    public void setTables(ArrayList<ERDTable> tables) {
        this.tables = tables;
    }

    public void addTable(ERDTable table) {
        tables.add(table);
    }
}
