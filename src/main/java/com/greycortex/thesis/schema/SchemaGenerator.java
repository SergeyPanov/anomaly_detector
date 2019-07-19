package com.greycortex.thesis.schema;

import com.greycortex.thesis.trie.Node;
import com.greycortex.thesis.trie.Tree;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Takes {@link com.greycortex.thesis.trie.Tree} and generate the {@link Scheme} from the tree.
 */
public abstract class SchemaGenerator {

    public static Scheme generateScheme(Tree tree) {

        Stack<Pair<ERDTable, Node>> fstStack = new Stack<>();
        Stack<Pair<ERDTable, Node>> sndStack = new Stack<>();

        fstStack.push(new MutablePair<>(new ERDTable(null), tree.getRoot()));

        while (!fstStack.isEmpty()) {

            Pair<ERDTable, Node> currentRoot = fstStack.pop();
            sndStack.push(currentRoot);

            ERDTable nodeTable = currentRoot.getKey();
            Node root = currentRoot.getValue();

            ArrayList<Node> children = root.getChildren();

            for (Node child :
                    children) {
                if (!child.isComplex()) {
                    // Simple value(STRING, NUMBER ...)
                    nodeTable.setColumn(child.getName(), child.getType().iterator().next().getValue());
                } else {
                    if (child.isArray()) {
                        child.getChildren().forEach(elems -> {
                            if (elems.isObject()) {
                                ERDTable manyToOneTbl = new ERDTable(elems.getName());
                                manyToOneTbl.addManyToOne(nodeTable);
                                fstStack.push(new MutablePair<>(manyToOneTbl, elems));
                            } else {
                                // Mixed Array, contains only simple types and objects !! DOES NOT CONSIDER INNER ARRAYS !!
                                for (Node el:
                                        elems.getChildren()) {
                                    if (!el.isComplex()) {
                                        nodeTable.setColumn(el.getName() + "_" + el.getType().iterator().next().getValue(), el.getType().iterator().next().getValue());
                                    } else {
                                        // Many-To-Many
                                        ERDTable manyToOneTbl = new ERDTable(el.getName());
                                        manyToOneTbl.addManyToOne(nodeTable);
                                        fstStack.push(new MutablePair<>(manyToOneTbl, el));
                                    }
                                }
                            }
                        });
                    }

                    if (child.isObject()) {
                        // One-To-One relation
                        ERDTable oneToOneTbl = new ERDTable(child.getName());
                        nodeTable.addOneToOne(oneToOneTbl);
                        fstStack.push(new MutablePair<>(oneToOneTbl, child));
                        nodeTable.setColumn(child.getName(), child.getType().iterator().next().getValue());
                    }
                }
            }
        }
        return null;
    }
}
