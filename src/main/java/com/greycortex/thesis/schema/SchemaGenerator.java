package com.greycortex.thesis.schema;

import com.greycortex.thesis.trie.Node;
import com.greycortex.thesis.trie.Tree;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Takes {@link com.greycortex.thesis.trie.Tree} and generate the {@link Schema} from the tree.
 */
public class SchemaGenerator {

    private Stack<Pair<ERDTable, Node>> fstStack = new Stack<>();

    private Stack<ERDTable> schemaStack = new Stack<>();

    /**
     * Iterative post order traverse
     * @param tree
     * @return
     */
    public Schema generateScheme(Tree tree) {
        fstStack = new Stack<>();
        fstStack.push(new MutablePair<>(new ERDTable("root"), tree.getRoot()));

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
                                manyToOneTbl.addManyToOne(rootTable);
                                rootTable.addOneToMany(manyToOneTbl);
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
                                            rootTable.setColumn(el.getName() + "_" + el.getType().iterator().next().getValue(), DBTypes.getEnum(el.getType().iterator().next().getValue()).toString());
                                        } else {
                                            // Many-To-Many
                                            path.add(child.getName());

                                            ERDTable manyToOneTbl = new ERDTable(el.getName());
                                            manyToOneTbl.addManyToOne(rootTable);
                                            rootTable.addOneToMany(manyToOneTbl);
                                            fstStack.push(new MutablePair<>(manyToOneTbl, el));

                                            manyToOneTbl.addPath(new ArrayList<>(path));
                                            path.remove(path.size() - 1);
                                        }
                                    }
                                } else {
                                    // Just simple type [STRING | INTEGER...]
                                    rootTable.setColumn(elems.getName(), DBTypes.getEnum(elems.getType().iterator().next().getValue()).toString());
                                }
                            }
                        });
                    }
                    if (child.isObject()) {
                        // One-To-One relation
                        path.add(child.getName());

                        ERDTable oneToOneTbl = new ERDTable(child.getName());
                        oneToOneTbl.addOneToOne(rootTable);
                        rootTable.addOneToOne(oneToOneTbl);
                        fstStack.push(new MutablePair<>(oneToOneTbl, child));

                        oneToOneTbl.addPath(new ArrayList<>(path));
                        path.remove(path.size() - 1);

                    }
                }
            }
            schemaStack.push(rootTable);
        }
//        mergeTables(schemaStack);
        return new Schema(schemaStack);
    }


    /**
     * TODO: merge paths
     * Merge the same tables.
     * Tables are the same if have the same column with the same types.
     * @param tables
     */
    private void mergeTables(Stack<ERDTable> tables) {

        List<ERDTable> forRemove = new ArrayList<>();

        for (ERDTable tbl :
                tables) {

            List<ERDTable> clones = tables.stream().filter(cl -> cl.equals(tbl)).collect(Collectors.toList());

            if (!forRemove.contains(tbl)) {

                for (ERDTable clone :
                        clones) {

                    if (clone != tbl) {
                        tbl.addOneToOne(clone.getOneToOne());
                        tbl.addOneToMany(clone.getOneToMany());
                        tbl.addManyToOne(clone.getManyToOne());
                        tbl.setName(tbl.getName() + "_" + clone.getName());

                        clone.getOneToOne().forEach(tl -> tl.replaceTable(clone, tbl));
                        clone.getOneToMany().forEach(tl -> tl.replaceTable(clone, tbl));
                        clone.getManyToOne().forEach(tl -> tl.replaceTable(clone, tbl));

                        forRemove.add(clone);
                    }
                }
            }

        }


        for (ERDTable t :
                forRemove) {
            tables.removeIf(el -> el.getName().equals(t.getName()));
        }

    }

}
