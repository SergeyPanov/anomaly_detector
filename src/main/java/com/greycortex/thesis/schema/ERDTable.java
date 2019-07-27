package com.greycortex.thesis.schema;



import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

/**
 * Single table from the
 */
public class ERDTable {

    private String name;

    private List<String> basePath;

    private Map<Pair<String, String>, List<String>> columns;

    private List<ERDTable> oneToOne;

    private List<ERDTable> manyToOne;

    private List<ERDTable> oneToMany;

    private boolean insertedMeta = false;



    public ERDTable(String name) {
        this.setName(name);
        this.oneToOne = new ArrayList<>();
        this.manyToOne = new ArrayList<>();
        this.oneToMany = new ArrayList<>();
        this.columns = new HashMap<>();
        this.basePath = new ArrayList<>();
    }

    public boolean isInsertedMeta() {
        return insertedMeta;
    }

    public void setInsertedMeta(boolean insertedMeta) {
        this.insertedMeta = insertedMeta;
    }


    public List<String> getBasePath() {
        return basePath;
    }

    public void setBasePath(List<String> basePath) {
        this.basePath = basePath;
    }


    public List<String> getPathForElement(String element) {
        List<String> path = new ArrayList<>(basePath);
        path.add(element);
        return path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Pair<String, String>, List<String>> getColumns() {
        return columns;
    }

    public void setNewColumn(String columnName, String type, List<String> path) {
        this.columns.put(new ImmutablePair<>(columnName, type), path);
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


    private void addAllUnique(List<ERDTable> where, List<ERDTable> what) {
        for (ERDTable tb :
                what) {
            if (where.stream().noneMatch(el -> el.equals(tb) && el.getName().equals(tb.getName()))) {
//            if (where.stream().noneMatch(el -> el.equals(tb) )) {
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
        return Objects.equals(basePath, erdTable.basePath) &&
                Objects.equals(columns, erdTable.columns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basePath, columns);
    }
}
