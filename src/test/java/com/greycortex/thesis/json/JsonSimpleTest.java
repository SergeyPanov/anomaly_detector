package com.greycortex.thesis.json;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;

public class JsonSimpleTest {

    @Test
    public void equalsTest() {
        JsonSimple sm1 = new JsonSimple("simple_name", new HashSet<>(Collections.singletonList(Type.STRING)), "date");
        JsonSimple sm2 = new JsonSimple("simple_name", new HashSet<>(Collections.singletonList(Type.STRING)), "date");

        Assert.assertEquals(sm1, sm2);
    }


    @Test
    public void notEqualsDiffTypesTest() {
        JsonSimple sm1 = new JsonSimple("simple_name", new HashSet<>(Collections.singletonList(Type.INTEGER)));
        JsonSimple sm2 = new JsonSimple("simple_name", new HashSet<>(Collections.singletonList(Type.STRING)), "date");

        Assert.assertNotEquals(sm1, sm2);
    }

    @Test
    public void notEqualsDiffNamesTest() {
        JsonSimple sm1 = new JsonSimple("simple_name", new HashSet<>(Collections.singletonList(Type.STRING)), "date");
        JsonSimple sm2 = new JsonSimple("diff_simple_name", new HashSet<>(Collections.singletonList(Type.STRING)), "date");

        Assert.assertNotEquals(sm1, sm2);
    }

}