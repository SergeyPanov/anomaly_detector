package com.greycortex.thesis.schema.meta;

import com.greycortex.thesis.schema.DBTemplates;
import com.greycortex.thesis.schema.DBTypes;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MetaColumn {
    private String tableColumn;
    private List<String> path;
    private int tableId;
    private String type;



    public MetaColumn(String tableColumn, String type, List<String> path, int tableId) {
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

    public void insert() {
        String statement = DBTemplates.getInsertStatement(DBTemplates.META_COLUMN_NAME, Arrays.asList("name", "type", "path", "table_FK"),
                Arrays.asList("'" + tableColumn + "'", "'" + type + "'",
                        "ARRAY [" + path.stream().filter(Objects::nonNull).map(e -> "'" + e + "'").collect(Collectors.joining(",")) + "]", String.valueOf(tableId)));
        System.out.println(statement);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetaColumn column = (MetaColumn) o;
        return Objects.equals(tableColumn, column.tableColumn) &&
                Objects.equals(path, column.path) &&
                Objects.equals(type, column.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableColumn, path, type);
    }
}
