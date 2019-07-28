package com.greycortex.thesis.schema.meta;

import com.greycortex.thesis.schema.DBTemplates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MetaTable {

    //TODO: DB connection

    private int id = -1;

    private String name;

    private List<MetaColumn> columns = new ArrayList<>();

    private int fk = -1;


    private List<MetaTable> oneToManyTables = new ArrayList<>();

    private boolean inserted = false;


    public List<MetaTable> getOneToManyTables() {
        return oneToManyTables;
    }

    public List<MetaColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<MetaColumn> columns) {
        this.columns = columns;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    protected void incID(){
        id++;
    }
    public void insert() {
        String statement;
        if (id == -1) {
            statement = DBTemplates.getInsertStatement(DBTemplates.META_TABLE_NAME, Collections.singletonList("name"), Collections.singletonList("'" + name + "'"));
        } else {
            statement = DBTemplates.getInsertStatement(DBTemplates.META_TABLE_NAME, Arrays.asList("name", "table_FK"), Arrays.asList("'" + name + "'", String.valueOf(fk)));
        }


        System.out.println(statement);
        this.incID();

        for (MetaTable mToTbl :
                oneToManyTables) {
            mToTbl.setFk(id);

            //TODO: remove this
            mToTbl.incID();
        }

        for (MetaColumn column:
             columns) {
            column.setTableId(id);
            column.insert();
        }
    }

    public int getFk() {
        return fk;
    }

    public void setFk(int fk) {
        this.fk = fk;
    }
}
