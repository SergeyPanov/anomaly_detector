package com.greycortex.thesis.schema;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;


public class ERDTableTest {

    @Test
    public void equalTables() {
        ERDTable tbl1 = new ERDTable("tbl1");
        tbl1.setColumn("c1", "integer");
        tbl1.setColumn("c2", "integer");
        tbl1.setColumn("c3", "string");

        ERDTable tbl2 = new ERDTable("tbl2");
        tbl2.setColumn("c1", "integer");
        tbl2.setColumn("c2", "integer");
        tbl2.setColumn("c3", "string");

        assertEquals(tbl1, tbl2);
    }


    @Test
    public void notEqualTables() {
        ERDTable tbl1 = new ERDTable("tbl1");
        tbl1.setColumn("c1", "integer");
        tbl1.setColumn("c2", "integer");
        tbl1.setColumn("c3", "string");

        ERDTable tbl2 = new ERDTable("tbl2");
        tbl2.setColumn("c1", "integer");
        tbl2.setColumn("c2", "integer");
        tbl2.setColumn("c3", "integer");

        assertNotEquals(tbl1, tbl2);
    }
}
