package com.greycortex.thesis.schema;

import com.greycortex.thesis.trie.Node;
import com.greycortex.thesis.trie.Tree;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Takes {@link com.greycortex.thesis.trie.Tree} and generate the {@link Schema} from the tree.
 */
public class SchemaGenerator {




    private static final String ARRAY_QUANTIFIER = "[]";

    private Stack<Pair<ERDTable, Node>> fstStack = new Stack<>();

    private List<ERDTable> schema = new ArrayList<>();

    /**
     * Iterative post order traverse
     * @param tree
     * @return
     */
    public Schema generateScheme(Tree tree) {
        fstStack = new Stack<>();

        ERDTable tbl = new ERDTable("root");
        tbl.addPath(new ArrayList<>());

        fstStack.push(new MutablePair<>(tbl, tree.getRoot()));

        List<String> path = new ArrayList<>();

        while (!fstStack.isEmpty()) {
            Pair<ERDTable, Node> currentRoot = fstStack.pop();

            ERDTable rootTable = currentRoot.getKey();
            Node root = currentRoot.getValue();

            path.add(root.getName());

            ArrayList<Node> children = root.getChildren();

            for (Node child :
                    children) {
                if (!child.isComplex()) {
                    // Simple value(STRING, NUMBER ...)
                    rootTable.setColumn(child.getName(), DBTypes.getEnum(child.getType().iterator().next().getValue()).toString());
                } else {
                    if (child.isArray()) {
                        child.getChildren().forEach(elems -> {

                            if (elems.isObject()) {
                                // Array of objects [OBJECT]
                                path.add(child.getName());

                                ERDTable manyToOneTbl = new ERDTable(elems.getName());
                                manyToOneTbl.addUniqueManyToOne(rootTable);
                                rootTable.addUniqueOneToMany(manyToOneTbl);
                                fstStack.push(new MutablePair<>(manyToOneTbl, elems));

                                manyToOneTbl.addPath(new ArrayList<>(path));
                                path.remove(path.size() - 1);

                            } else {
                                // Mixed Array, contains only simple types and objects !! DOES NOT CONSIDER INNER ARRAYS !!
                                if (elems.isMixed()) {
                                    // Mixed has the type [STRING, INTEGER, OBJECT...]
                                    for (Node el :
                                            elems.getChildren()) {
                                        if (!el.isComplex()) {

                                            rootTable.setColumn(el.getName() + "_" + el.getType().iterator().next().getValue(),
                                                    DBTypes.getEnum(el.getType().iterator().next().getValue()).toString());

                                            rootTable.setColumn(el.getName() + "_" + el.getType().iterator().next().getValue(),
                                                    DBTypes.getEnum(el.getType().iterator().next().getValue()).toString() + ARRAY_QUANTIFIER );
                                        } else {
                                            // Many-To-Many
                                            path.add(child.getName());

                                            ERDTable manyToOneTbl = new ERDTable(el.getName());
                                            manyToOneTbl.addUniqueManyToOne(rootTable);
                                            rootTable.addUniqueOneToMany(manyToOneTbl);
                                            fstStack.push(new MutablePair<>(manyToOneTbl, el));

                                            manyToOneTbl.addPath(new ArrayList<>(path));
                                            path.remove(path.size() - 1);
                                        }
                                    }
                                } else {
                                    // Just simple type [STRING | INTEGER...]
                                    rootTable.setColumn(elems.getName(),
                                            DBTypes.getEnum(elems.getType().iterator().next().getValue()).toString() + ARRAY_QUANTIFIER);
                                }
                            }
                        });
                    }
                    if (child.isObject()) {
                        // One-To-One relation
                        path.add(child.getName());

                        ERDTable oneToOneTbl = new ERDTable(child.getName());
                        oneToOneTbl.addUniqueOneToOne(rootTable);
                        rootTable.addUniqueOneToOne(oneToOneTbl);
                        fstStack.push(new MutablePair<>(oneToOneTbl, child));

                        oneToOneTbl.addPath(new ArrayList<>(path));
                        path.remove(path.size() - 1);
                    }
                }
            }
            schema.add(rootTable);
        }
//        mergeTables(schemaStack);
        inlineAllTables(schema);
        return new Schema(schema);
    }



    private void mergeUnique(List<ERDTable> list1, List<ERDTable> list2) {

        for (ERDTable tbl :
                list2) {
            if (!list1.contains(tbl)) {
                list1.add(tbl);
            }
        }
    }


    /**
     * Inline tables in One-To-One relation.
     * Remove inlined tables from the @tables
     * @param tables list of tables.
     */
    private void inlineAllTables(List<ERDTable> tables) {

        int index = 0;
        int length = tables.size();
        while (index < length) {
            ERDTable fst = tables.get(index);

            List<ERDTable> toProcess = new ArrayList<>(fst.getOneToOne());

            for (int i = 0; i < toProcess.size(); i++) {

                ERDTable snd = toProcess.get(i);
                inlineTables(fst, snd);
                tables.remove(snd);
                mergeUnique(toProcess, fst.getOneToOne());
            }
            index++;
            length = tables.size();

        }
    }
    /**
     * Inline @snd table into fst table.
     * Steps for inline:
     *  1. Copy all columns from the @snd table into @fst table TODO: What if meet the same column names and types????
     *  2. Copy all paths from the @snd table into @fst table
     *  3. Update relation pointers.
     * @param fst
     * @param snd
     * @return
     */
    private void inlineTables(ERDTable fst, ERDTable snd) {

        fst.setName(fst.getName() + "_" + snd.getName());

        // Copy all columns of snd table into fst table
        for (Map.Entry entry:
             snd.getColumns().entrySet()) {
            fst.setColumn( (String) entry.getKey(), (String) entry.getValue());
        }

        // Add paths from snd table into fst table(metadata for insert)
        for (List<String> pt:
             snd.getPaths()) {
            fst.addPath(new ArrayList<>(pt));
        }

        /*
        Updated relation pointers:
         */
        // Remove
        fst.getOneToOne().remove(snd);
        snd.getOneToOne().remove(fst);

        // Add all One-To-Many relations from the snd table into fst table
        fst.addUniqueOneToMany(snd.getOneToMany());
        // Re-point Many-To-One relations of the snd table on fst table
        for (ERDTable mto:
            snd.getManyToOne()) {

            mto.getOneToMany().remove(snd);
            mto.addUniqueOneToMany(fst);
        }

        // Add all Many-To-One relations from the snd table into fst table
        fst.addUniqueManyToOne(snd.getManyToOne());
        // Re-point One-To-Many relations of the snd table on fst table
        for (ERDTable otm:
             snd.getOneToMany()) {

            otm.getManyToOne().remove(snd);
            otm.addUniqueManyToOne(fst);
        }

        // Add all One-To-One relations from snd to fst
        fst.addUniqueOneToOne(snd.getOneToOne());
        // Re-point One-To-One relations of the snd table on fst table
        for (ERDTable otm:
                snd.getOneToOne()) {

            otm.getOneToOne().remove(snd);
            otm.addUniqueOneToOne(fst);
        }
    }

    /**
     * TODO: merge paths
     * TODO: Do we need this????
     * Merge the same tables.
     * Tables are the same if have the same column with the same types.
     * @param tables
     */
//    private void mergeTables(Stack<ERDTable> tables) {
//
//        List<ERDTable> forRemove = new ArrayList<>();
//
//        for (ERDTable tbl :
//                tables) {
//
//            List<ERDTable> clones = tables.stream().filter(cl -> cl.equals(tbl)).collect(Collectors.toList());
//
//            if (!forRemove.contains(tbl)) {
//
//                for (ERDTable clone :
//                        clones) {
//
//                    if (clone != tbl) {
//                        tbl.addUniqueOneToOne(clone.getOneToOne());
//                        tbl.addUniqueOneToMany(clone.getOneToMany());
//                        tbl.addUniqueManyToOne(clone.getManyToOne());
//                        tbl.setName(tbl.getName() + "_" + clone.getName());
//
//                        clone.getOneToOne().forEach(tl -> tl.replaceTable(clone, tbl));
//                        clone.getOneToMany().forEach(tl -> tl.replaceTable(clone, tbl));
//                        clone.getManyToOne().forEach(tl -> tl.replaceTable(clone, tbl));
//
//                        forRemove.add(clone);
//                    }
//                }
//            }
//
//        }
//
//
//        for (ERDTable t :
//                forRemove) {
//            tables.removeIf(el -> el.getName().equals(t.getName()));
//        }
//
//    }

}
