package com.greycortex.thesis.schema;

import com.greycortex.thesis.trie.Node;
import com.greycortex.thesis.trie.Tree;
import org.apache.commons.lang3.tuple.ImmutablePair;
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

    private Stack<Pair<ERDTable, Node>> fstStack = new Stack<>();

    private List<Pair<ERDTable, Node>> tbls = new ArrayList<>();

    private Stack<ERDTable> schemaStack = new Stack<>();


    /**
     * If "tbl" is not in the "stack" -> push it
     * else find this table in the stack and insert into found table all relations from the "tbl"
     *
     * @param stack where to insert
     * @param tbl   what to insert
     */
    private void pushOrUpdate(Stack<ERDTable> stack, ERDTable tbl) {

        if (stack.stream().noneMatch(el -> el.equals(tbl))) {
            stack.push(tbl);
        } else {
            ERDTable found = stack.stream().filter(el -> el.equals(tbl)).findFirst().orElse(tbl);
            found.addAllWithoutDuplicatesOneToOne(tbl.getOneToOne());
            found.addAllWithoutDuplicatesManyToOne(tbl.getManyToOne());
            found.setName(found.getName() + "_" + tbl.getName());
        }
    }


    public Schema generateScheme(Tree tree) {
        fstStack = new Stack<>();
        fstStack.push(new MutablePair<>(new ERDTable("root"), tree.getRoot()));
        while (!fstStack.isEmpty()) {
            Pair<ERDTable, Node> currentRoot = fstStack.pop();

            tbls.add(currentRoot);

            ERDTable rootTable = currentRoot.getKey();
            Node root = currentRoot.getValue();

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
                                ERDTable manyToOneTbl = new ERDTable(elems.getName());
                                manyToOneTbl.addManyToOne(rootTable);
                                fstStack.push(new MutablePair<>(manyToOneTbl, elems));
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
                                            ERDTable manyToOneTbl = new ERDTable(el.getName());
                                            manyToOneTbl.addManyToOne(rootTable);
                                            fstStack.push(new MutablePair<>(manyToOneTbl, el));
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
                        ERDTable oneToOneTbl = new ERDTable(root.getName() + "_" + child.getName());
                        oneToOneTbl.addOneToOne(rootTable);
                        fstStack.push(new MutablePair<>(oneToOneTbl, child));
                    }
                }
            }
//            pushOrUpdate(schemaStack, currentRoot.getKey());
            schemaStack.push(currentRoot.getKey());
        }

        return new Schema(schemaStack);
    }

}
