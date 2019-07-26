package com.greycortex.thesis.schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Schema is a list of tables.
 */
public class Schema {
    private List<ERDTable> tables;

    public Schema(List<ERDTable> tables) {
        this.tables = tables;
    }


    /**
     * TODO: Rename this.
     *
     * @return list of queries for schema creation
     */
    public ArrayList<String> getSQLSchema() {
        ArrayList<String> queries = new ArrayList<>();
        ArrayList<String> constraints = new ArrayList<>();

        for (ERDTable table :
                tables) {
            ArrayList<String> fields = new ArrayList<>();

            for (Map.Entry entry :
                    table.getColumns().entrySet()) {
                fields.add((String) entry.getKey());
                fields.add((String) entry.getValue());
            }
            queries.add(DBTemplates.createTable(table.getName(), fields));
        }
        return queries;
    }
}
