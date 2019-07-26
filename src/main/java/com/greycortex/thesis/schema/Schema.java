package com.greycortex.thesis.schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

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
//    public ArrayList<String> getAll() {
//        ArrayList<String> queries = new ArrayList<>();
//        while (!tables.empty()) {
//
//            ERDTable top = tables.pop();
//
//            ArrayList<String> fields = new ArrayList<>();
//
//            for (Map.Entry entry :
//                    top.getColumns().entrySet()) {
//                fields.add((String) entry.getKey());
//                fields.add((String) entry.getValue());
//            }
//            queries.add(DBTemplates.createTable(top.getName(), fields));
//        }
//        return queries;
//    }
}
