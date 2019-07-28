package com.greycortex.thesis.schema;

import com.greycortex.thesis.schema.meta.MetaColumn;
import com.greycortex.thesis.schema.meta.MetaTable;
import org.apache.commons.lang3.tuple.ImmutablePair;

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
     * @return list of queries for schema creation
     */
    public ArrayList<String> getSQLSchema() {
        ArrayList<String> fkConstraints = new ArrayList<>();

        ArrayList<String> queries = new ArrayList<>(Arrays.asList(DBTemplates.META_TABLES_TABLE, DBTemplates.META_COLUMNS_TABLE));

        HashMap<ERDTable, MetaTable> prepared = new HashMap<>();
        for (ERDTable table :
                tables) {
            ArrayList<String> fields = new ArrayList<>();

            MetaTable meta = prepared.get(table);
            if (meta == null) {
                meta = new MetaTable();
                prepared.put(table, meta);
            }
            meta.setName(table.getName());

            for (ERDTable oTmERD :
                    table.getOneToMany()) {
                MetaTable oTmMeta = new MetaTable();
                prepared.put(oTmERD, oTmMeta);
                meta.getOneToManyTables().add(oTmMeta);
            }

            // Compose columns
            for (Map.Entry entry :
                    table.getColumns().entrySet()) {
                ImmutablePair<String, String> column = (ImmutablePair<String, String>) entry.getKey();

                fields.add(column.getKey());
                fields.add(column.getValue());

                MetaColumn c = new MetaColumn(column.getKey(),  column.getValue(), (List<String>) entry.getValue(), meta.getId());
                meta.getColumns().add(c);
            }

            fields.addAll(Arrays.asList(DBTemplates.PK, "INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY"));
            // Generate FK constraints
            for (ERDTable parentTable :
                    table.getManyToOne()) {
                fields.add(parentTable.getName() + DBTemplates.FK_PREFIX);
                fields.add("INTEGER");
                fkConstraints.add(DBTemplates.createFKConstraint(table.getName(), Collections.singletonList(parentTable.getName() + DBTemplates.FK_PREFIX), parentTable.getName(), Collections.singletonList(DBTemplates.PK)));
            }
            queries.add(DBTemplates.createTable(table.getName(), fields));
        }

        queries.addAll(fkConstraints);
        for (MetaTable meta :
                prepared.values()) {
            meta.insert();
        }
        return queries;
    }
}
