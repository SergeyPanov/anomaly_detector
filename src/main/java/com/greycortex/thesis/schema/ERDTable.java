package com.greycortex.thesis.schema;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * Single table from the
 */
public class ERDTable {
    private String name;

    private HashMap<String, String> columns;

    private ArrayList<ERDTable> oneToOne;

    private ArrayList<ERDTable> manyToOne;

    public ERDTable(String name, HashMap<String, String> columns) {
        this.name = name;
        this.columns = columns;
        this.oneToOne = new ArrayList<>();
        this.manyToOne = new ArrayList<>();
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

    public ArrayList<ERDTable> getOneToOne() {
        return oneToOne;
    }

    public ArrayList<ERDTable> getManyToOne() {
        return manyToOne;
    }

    public void addOneToOne(ERDTable oneToOne) {
        this.oneToOne.add(oneToOne);
    }

    public void addManyToOne(ERDTable oneToOne) {
        this.manyToOne.add(oneToOne);
    }
}
