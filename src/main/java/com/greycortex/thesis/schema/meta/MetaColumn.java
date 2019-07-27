package com.greycortex.thesis.schema.meta;

import com.greycortex.thesis.schema.DBTypes;

import java.util.List;

public class MetaColumn {
    private String tableColumn;
    private List<String> path;
    private int tableId;
    private String type;

    public MetaColumn(String tableColumn, List<String> path, int tableId, String type) {
        this.tableColumn = tableColumn;
        this.path = path;
        this.tableId = tableId;
        this.type = type;
    }

    public String getTableColumn() {
        return tableColumn;
    }

    public void setTableColumn(String tableColumn) {
        this.tableColumn = tableColumn;
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
