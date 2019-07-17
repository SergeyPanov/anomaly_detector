package com.greycortex.thesis.schema;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Single table from the
 */
public class ERDTable {
    private String name;

    private HashMap<String, String> columns;

    private List<ERDTable> oneToOne;

    private List<ERDTable> oneToMany;

    public ERDTable(String name, HashMap<String, String> columns) {
        this.name = name;
        this.columns = columns;
        this.oneToOne = new ArrayList<>();
        this.oneToMany = new ArrayList<>();
    }


    public ERDTable(String name) {
        this(name, new HashMap<>());
    }

    public HashMap<String, String> getColumns() {
        return columns;
    }

    public void setColumns(HashMap<String, String> columns) {
        this.columns = columns;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColumn(String name, String type) {
        columns.put(name, type);
    }

    public List<ERDTable> getOneToOne() {
        return oneToOne;
    }

    public void setOneToOne(List<ERDTable> oneToOne) {
        this.oneToOne = oneToOne;
    }

    public List<ERDTable> getOneToMany() {
        return oneToMany;
    }

    public void setOneToMany(List<ERDTable> oneToMany) {
        this.oneToMany = oneToMany;
    }
}
