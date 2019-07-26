package com.greycortex.thesis.schema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Holds templates for postgres entities creation.
 */
public abstract class DBTemplates {

    public final static String FK_PREFIX = "_FK";
    public final static String PK = "_PK";

    private final static String TAB = "\t";

    private final static String NEW_TABLE =
            "CREATE TABLE %s\n" +
                    "(\n" +
                    "%s\n" +
                    ");";

    private final static String FK_CONSTRAINT = "ALTER TABLE %s ADD CONSTRAINT %s_fk FOREIGN KEY (%s) REFERENCES %s (%s);";


    public static String createTable(String tableName, ArrayList<String > args) {
        StringBuilder fields = new StringBuilder();
        String delimiter = ",\n";
        for (int i = 0; i < args.size(); i += 2) {
            fields
                    .append(TAB)
                    .append(args.get(i))
                    .append(" ")
                    .append(args.get(i + 1))
                    .append(delimiter);
        }
        fields.delete(fields.length() - delimiter.length(), fields.length());
        return String.format(NEW_TABLE, tableName, fields);
    }

    public static String createFKConstraint(String childTable, List<String> childFK, String targetTable, List<String> parentPK) {
        StringBuilder childCols = new StringBuilder();
        StringBuilder parentCols = new StringBuilder();

        String delimiter = ",";

        childCols.append(String.join(delimiter, childFK));
        if (childFK.size() > 1)childCols.delete(childCols.length() - delimiter.length(), childCols.length());

        parentCols.append(String.join(delimiter, parentPK));
        if(parentPK.size() > 1) parentCols.delete(parentCols.length() - delimiter.length(), parentCols.length());

        return String.format(FK_CONSTRAINT, childTable, targetTable, childCols, targetTable, parentCols);
    }

}
