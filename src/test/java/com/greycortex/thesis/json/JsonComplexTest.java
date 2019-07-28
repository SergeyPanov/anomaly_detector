package com.greycortex.thesis.json;



import com.greycortex.thesis.trie.Tree;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class JsonComplexTest {

    /**
     * Same
     * @throws ParseException
     */
    @Test
    public void equalsTest() throws ParseException {
        JsonComplex c1 = new JsonComplex("cmplx", new HashSet<>(Collections.singletonList(Type.OBJECT)));

        JsonSimple s1 = new JsonSimple("simpl1", new HashSet<>(Collections.singletonList(Type.INTEGER)));
        JsonSimple s2 = new JsonSimple("simpl2", new HashSet<>(Collections.singletonList(Type.INTEGER)));

        c1.add(s1);
        c1.add(s2);

        JsonComplex c2 = new JsonComplex("cmplx", new HashSet<>(Collections.singletonList(Type.OBJECT)));

        JsonSimple ss1 = new JsonSimple("simpl1", new HashSet<>(Collections.singletonList(Type.INTEGER)));
        JsonSimple ss2 = new JsonSimple("simpl2", new HashSet<>(Collections.singletonList(Type.INTEGER)));

        c2.add(ss1);
        c2.add(ss2);

        Assert.assertEquals(c1, c2);
    }


    /**
     * Different names: simpl VS simpl1
     * @throws ParseException
     */
    @Test
    public void notEqualsTest() throws ParseException {
        JsonComplex c1 = new JsonComplex("cmplx", new HashSet<>(Collections.singletonList(Type.OBJECT)));

        JsonSimple s1 = new JsonSimple("simpl", new HashSet<>(Collections.singletonList(Type.INTEGER)));
        JsonSimple s2 = new JsonSimple("simpl2", new HashSet<>(Collections.singletonList(Type.INTEGER)));

        c1.add(s1);
        c1.add(s2);

        JsonComplex c2 = new JsonComplex("cmplx", new HashSet<>(Collections.singletonList(Type.OBJECT)));

        JsonSimple ss1 = new JsonSimple("simpl1", new HashSet<>(Collections.singletonList(Type.INTEGER)));
        JsonSimple ss2 = new JsonSimple("simpl2", new HashSet<>(Collections.singletonList(Type.INTEGER)));

        c2.add(ss1);
        c2.add(ss2);

        Assert.assertNotEquals(c1, c2);
    }



    @Test
    public void equalsArraysTest() throws ParseException {
        JsonComplex c1 = new JsonComplex("cmplx", new HashSet<>(Collections.singletonList(Type.ARRAY)));

        JsonSimple s1 = new JsonSimple("simpl1", new HashSet<>(Collections.singletonList(Type.INTEGER)));
        JsonSimple s2 = new JsonSimple("simpl2", new HashSet<>(Collections.singletonList(Type.STRING)));

        c1.add(s1);
        c1.add(s2);

        JsonComplex c2 = new JsonComplex("cmplx", new HashSet<>(Collections.singletonList(Type.ARRAY)));

        JsonSimple ss1 = new JsonSimple("simpl1", new HashSet<>(Collections.singletonList(Type.INTEGER)));
        JsonSimple ss2 = new JsonSimple("simpl2", new HashSet<>(Collections.singletonList(Type.STRING)));

        c2.add(ss1);
        c2.add(ss2);

        Assert.assertEquals(c1, c2);
    }

    /**
     * Different types: INTEGER VS BOOLEAN
     * @throws ParseException
     */
    @Test
    public void notEqualsArraysTest() throws ParseException {
        JsonComplex c1 = new JsonComplex("cmplx", new HashSet<>(Collections.singletonList(Type.ARRAY)));

        JsonSimple s1 = new JsonSimple("simpl1", new HashSet<>(Collections.singletonList(Type.INTEGER)));
        JsonSimple s2 = new JsonSimple("simpl2", new HashSet<>(Collections.singletonList(Type.STRING)));

        c1.add(s1);
        c1.add(s2);

        JsonComplex c2 = new JsonComplex("cmplx", new HashSet<>(Collections.singletonList(Type.ARRAY)));

        JsonSimple ss1 = new JsonSimple("simpl1", new HashSet<>(Collections.singletonList(Type.BOOLEAN)));
        JsonSimple ss2 = new JsonSimple("simpl2", new HashSet<>(Collections.singletonList(Type.STRING)));

        c2.add(ss1);
        c2.add(ss2);

        Assert.assertNotEquals(c1, c2);
    }

    /**
     * Same
     * @throws ParseException
     */
    @Test
    public void equalsObjectsTest() throws ParseException {
        JsonComplex c1 = new JsonComplex("cmplx", new HashSet<>(Collections.singletonList(Type.OBJECT)));
        JsonComplex arr1 = new JsonComplex("arr", new HashSet<>(Collections.singletonList(Type.ARRAY)));

        JsonSimple s1 = new JsonSimple("simpl1", new HashSet<>(Collections.singletonList(Type.INTEGER)));
        JsonSimple s2 = new JsonSimple("simpl2", new HashSet<>(Collections.singletonList(Type.STRING)));

        JsonSimple s3 = new JsonSimple("items", new HashSet<>(Collections.singletonList(Type.INTEGER)));
        arr1.add(s3);


        c1.add(s1);
        c1.add(s2);
        c1.add(arr1);

        JsonComplex c2 = new JsonComplex("cmplx", new HashSet<>(Collections.singletonList(Type.OBJECT)));
        JsonComplex arr2 = new JsonComplex("arr", new HashSet<>(Collections.singletonList(Type.ARRAY)));

        JsonSimple ss1 = new JsonSimple("simpl1", new HashSet<>(Collections.singletonList(Type.INTEGER)));
        JsonSimple ss2 = new JsonSimple("simpl2", new HashSet<>(Collections.singletonList(Type.STRING)));

        JsonSimple ss3 = new JsonSimple("items", new HashSet<>(Collections.singletonList(Type.INTEGER)));
        arr2.add(ss3);

        c2.add(ss1);
        c2.add(ss2);
        c2.add(arr2);

        Assert.assertEquals(c1, c2);
    }

    /**
     * Different elements in array.
     * @throws ParseException
     */
    @Test
    public void notEqualsObjectsTest() throws ParseException {
        JsonComplex c1 = new JsonComplex("cmplx", new HashSet<>(Collections.singletonList(Type.OBJECT)));
        JsonComplex arr1 = new JsonComplex("arr", new HashSet<>(Collections.singletonList(Type.ARRAY)));

        JsonSimple s1 = new JsonSimple("simpl1", new HashSet<>(Collections.singletonList(Type.INTEGER)));
        JsonSimple s2 = new JsonSimple("simpl2", new HashSet<>(Collections.singletonList(Type.STRING)));

        JsonSimple s3 = new JsonSimple("items", new HashSet<>(Collections.singletonList(Type.INTEGER)));

        arr1.add(s3);


        c1.add(s1);
        c1.add(s2);
        c1.add(s3);

        JsonComplex c2 = new JsonComplex("cmplx", new HashSet<>(Collections.singletonList(Type.OBJECT)));
        JsonComplex arr2 = new JsonComplex("arr", new HashSet<>(Collections.singletonList(Type.ARRAY)));

        JsonSimple ss1 = new JsonSimple("simpl1", new HashSet<>(Collections.singletonList(Type.INTEGER)));
        JsonSimple ss2 = new JsonSimple("simpl2", new HashSet<>(Collections.singletonList(Type.STRING)));

        JsonSimple ss3 = new JsonSimple("items", new HashSet<>(Collections.singletonList(Type.STRING)));

        c2.add(ss1);
        c2.add(ss2);
        c2.add(ss3);

        Assert.assertNotEquals(c1, c2);
    }
}
