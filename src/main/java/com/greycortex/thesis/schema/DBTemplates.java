package com.greycortex.thesis.schema;

import java.util.ArrayList;

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

}
