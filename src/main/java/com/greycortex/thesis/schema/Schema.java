package com.greycortex.thesis.schema;

import java.util.*;

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
        ArrayList<String> fkConstraints = new ArrayList<>();

        for (ERDTable table :
                tables) {
            ArrayList<String> fields = new ArrayList<>();

            for (Map.Entry entry :
                    table.getColumns().entrySet()) {
                fields.add((String) entry.getKey());
                fields.add((String) entry.getValue());
            }
            fields.addAll(Arrays.asList(DBTemplates.PK, "INTEGER PRIMARY KEY"));


            for (ERDTable parentTable:
                 table.getManyToOne()) {
                fields.add(parentTable.getName() + DBTemplates.FK_PREFIX);
                fields.add("INTEGER");
                fkConstraints.add(DBTemplates.createFKConstraint(table.getName(),  Collections.singletonList(parentTable.getName() + DBTemplates.FK_PREFIX), parentTable.getName(), Collections.singletonList(DBTemplates.PK)));
            }
            queries.add(DBTemplates.createTable(table.getName(), fields));

        }
        queries.addAll(fkConstraints);
        return queries;
    }
}
