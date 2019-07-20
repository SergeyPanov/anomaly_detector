package com.greycortex.thesis.schema;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class DBTemplatesTest {

    @Test
    public void tableCreation1Test() {
        String tbl = DBTemplates.createTable("name", new ArrayList<>(Arrays.asList("id", "INTEGER", "name", "VARCHAR", "date", "DATE", "num", "NUMERIC")));
        System.out.println(tbl);
    }

}
