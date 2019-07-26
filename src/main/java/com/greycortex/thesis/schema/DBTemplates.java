package com.greycortex.thesis.schema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Holds templates for postgres entities creation.
 */
public abstract class DBTemplates {

    private final static String TAB = "\t";

    private final static String NEW_TABLE =
            "CREATE TABLE %s\n" +
                    "(\n" +
                    "%s\n" +
                    ");";

    private final static String PK_CONSTRAINT = "ALTER TABLE %s ADD PRIMARY KEY (%s);";


    public static String createTable(String tableName, ArrayList<String > args) {
        StringBuilder fields = new StringBuilder();
        String delimiter = ",\n";
        args.addAll(Arrays.asList("_ID", "INTEGER"));
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

    public static String createPKConstraint(String tableName, List<String> columns) {
        StringBuilder cols = new StringBuilder();
        String delimiter = ",";

        for (String c:
             columns) {
            cols
                    .append(c)
                    .append(delimiter);
        }
        cols.delete(cols.length() - delimiter.length(), delimiter.length());
        return String.format(PK_CONSTRAINT, tableName, cols);
    }

}
