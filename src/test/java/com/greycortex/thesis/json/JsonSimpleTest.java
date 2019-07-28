package com.greycortex.thesis.json;


import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;



public class JsonSimpleTest {

    @Test
    public void equalTest() {
        JsonSimple sm1 = new JsonSimple("simple", new HashSet<>(Collections.singletonList(Type.INTEGER)), null);
        JsonSimple sm2 = new JsonSimple("simple", new HashSet<>(Collections.singletonList(Type.INTEGER)), null);
        Assert.assertEquals(sm1, sm2);
    }

    @Test
    public void notEqualTest() {
        JsonSimple sm1 = new JsonSimple("simple", new HashSet<>(Collections.singletonList(Type.STRING)), null);
        JsonSimple sm2 = new JsonSimple("simple", new HashSet<>(Collections.singletonList(Type.INTEGER)), null);
        Assert.assertNotEquals(sm1, sm2);
    }

}
