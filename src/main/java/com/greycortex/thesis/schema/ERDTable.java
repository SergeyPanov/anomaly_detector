package com.greycortex.thesis.schema;


import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Single table from the
 */
public class ERDTable {
    private String name;

    private List<List<String>> paths;

    private HashMap<String, String> columns;

    private List<ERDTable> oneToOne;

    private List<ERDTable> manyToOne;

    private List<ERDTable> oneToMany;


    public ERDTable(String name, HashMap<String, String> columns) {
        this.name = name;
        this.columns = columns;
        this.oneToOne = new ArrayList<>();
        this.manyToOne = new ArrayList<>();
        this.oneToMany = new ArrayList<>();
        this.paths = new Stack<>();
    }

    public List<List<String>> getPaths() {
        return paths;
    }

    public ERDTable(String name) {
        this(name, new HashMap<>());
    }

    public HashMap<String, String> getColumns() {
        return columns;
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

    public List<ERDTable> getManyToOne() {
        return manyToOne;
    }

    public List<ERDTable> getOneToMany() {
        return oneToMany;
    }

    public void addPath(List<String> pth) {
        paths.add(pth);
    }

    private void addAllUnique(List<ERDTable> where, List<ERDTable> what) {
        for (ERDTable tb :
                what) {
            //            if (where.stream().noneMatch(el -> el.equals(tb) && el.getName().equals(tb.getName()))) {
            if (where.stream().noneMatch(el -> el.equals(tb) )) {
                where.add(tb);
            }
        }
    }

    public void addUniqueOneToOne(ERDTable tb) {
        addAllUnique(oneToOne, Collections.singletonList(tb));
    }

    public void addUniqueOneToOne(List<ERDTable> tbls) {
        addAllUnique(oneToOne, tbls);
    }

    public void addUniqueManyToOne(ERDTable tb) {
        addAllUnique(manyToOne, Collections.singletonList(tb));
    }

    public void addUniqueManyToOne(List<ERDTable> tbls) {
        addAllUnique(manyToOne, tbls);
    }

    public void addUniqueOneToMany(ERDTable tb) {
        addAllUnique(oneToMany, Collections.singletonList(tb));
    }
    public void addUniqueOneToMany(List<ERDTable> tbls) {
        addAllUnique(oneToMany, tbls);
    }




    /**
     * Instead of fst set snd
     *
     * @param fst
     * @param snd
     */
//    public void replaceTable(ERDTable fst, ERDTable snd) {
//
//        if (oneToOne.removeIf(el -> el == fst))
//            addAllUnique(oneToOne, Collections.singletonList(snd));
//
//
//        if (oneToMany.removeIf(el -> el == fst))
//            addAllUnique(oneToMany, Collections.singletonList(snd));
//
//
//        if (manyToOne.removeIf(el -> el == fst))
//            addAllUnique(manyToOne, Collections.singletonList(snd));
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ERDTable erdTable = (ERDTable) o;
        return Objects.equals(paths, erdTable.paths) &&
                Objects.equals(columns, erdTable.columns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paths, columns);
    }
}
