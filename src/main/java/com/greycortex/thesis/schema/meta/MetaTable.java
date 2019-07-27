package com.greycortex.thesis.schema.meta;

import java.util.ArrayList;
import java.util.List;

public class MetaTable {

    private int id = -1;
    private String name;
    private int tableFK;
    private List<MetaColumn> columns = new ArrayList<>();
    private List<MetaTable> childTables = new ArrayList<>();

    public List<MetaColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<MetaColumn> columns) {
        this.columns = columns;
    }

    public int getTableFK() {
        return tableFK;
    }

    public void setTableFK(int tableFK) {
        this.tableFK = tableFK;
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
}
