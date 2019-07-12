package com.greycortex.thesis.json;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class JsonComplexTest {

    @Test
    public void equalsTest() {
        JsonComplex complex1 = new JsonComplex("complex", new HashSet<>(Arrays.asList(Type.STRING, Type.INTEGER, Type.ARRAY)));
        JsonComplex complex2 = new JsonComplex("complex", new HashSet<>(Arrays.asList(Type.STRING, Type.INTEGER, Type.ARRAY)));


        JsonSimple sm1 = new JsonSimple("simple_name", new HashSet<>(Collections.singletonList(Type.STRING)), "date");
        JsonSimple sm2 = new JsonSimple("simple_name", new HashSet<>(Collections.singletonList(Type.INTEGER)));

        JsonComplex arr = new JsonComplex("arr", new HashSet<>(Collections.singletonList(Type.INTEGER)));

        JsonSimple arrEl = new JsonSimple("el", new HashSet<>(Collections.singletonList(Type.INTEGER)));

        arr.add(arrEl);
        complex1.add(sm1);
        complex1.add(sm2);
        complex1.add(arr);


        ///////////////////////////////////
        sm1 = new JsonSimple("simple_name", new HashSet<>(Collections.singletonList(Type.STRING)), "date");
        sm2 = new JsonSimple("simple_name", new HashSet<>(Collections.singletonList(Type.INTEGER)));

        arr = new JsonComplex("arr", new HashSet<>(Collections.singletonList(Type.INTEGER)));

        arrEl = new JsonSimple("el", new HashSet<>(Collections.singletonList(Type.INTEGER)));
        arr.add(arrEl);
        complex2.add(sm1);
        complex2.add(sm2);
        complex2.add(arr);

        Assert.assertEquals(complex1, complex2);

    }


    @Test
    public void notEqualsDiffNameTest() {
        JsonComplex complex1 = new JsonComplex("complex", new HashSet<>(Arrays.asList(Type.STRING, Type.INTEGER, Type.ARRAY)));
        JsonComplex complex2 = new JsonComplex("complex", new HashSet<>(Arrays.asList(Type.STRING, Type.INTEGER, Type.ARRAY)));


        JsonSimple sm1 = new JsonSimple("simple_name", new HashSet<>(Collections.singletonList(Type.STRING)), "date");
        JsonSimple sm2 = new JsonSimple("simple_name", new HashSet<>(Collections.singletonList(Type.INTEGER)));

        JsonComplex arr = new JsonComplex("arr", new HashSet<>(Collections.singletonList(Type.INTEGER)));

        JsonSimple arrEl = new JsonSimple("el", new HashSet<>(Collections.singletonList(Type.INTEGER)));

        arr.add(arrEl);
        complex1.add(sm1);
        complex1.add(sm2);
        complex1.add(arr);


        ///////////////////////////////////
        sm1 = new JsonSimple("simple_name", new HashSet<>(Collections.singletonList(Type.STRING)), "date");
        sm2 = new JsonSimple("simple_name", new HashSet<>(Collections.singletonList(Type.INTEGER)));

        arr = new JsonComplex("arr", new HashSet<>(Collections.singletonList(Type.INTEGER)));

        arrEl = new JsonSimple("diff_name", new HashSet<>(Collections.singletonList(Type.INTEGER)));
        arr.add(arrEl);
        complex2.add(sm1);
        complex2.add(sm2);
        complex2.add(arr);

        Assert.assertNotEquals(complex1, complex2);

    }

    @Test
    public void notEqualsDiffTypeTest() {
        JsonComplex complex1 = new JsonComplex("complex", new HashSet<>(Arrays.asList(Type.STRING, Type.INTEGER, Type.ARRAY)));
        JsonComplex complex2 = new JsonComplex("complex", new HashSet<>(Arrays.asList(Type.STRING, Type.INTEGER, Type.ARRAY)));


        JsonSimple sm1 = new JsonSimple("simple_name", new HashSet<>(Collections.singletonList(Type.STRING)), "date");
        JsonSimple sm2 = new JsonSimple("simple_name", new HashSet<>(Collections.singletonList(Type.INTEGER)));

        JsonComplex arr = new JsonComplex("arr", new HashSet<>(Collections.singletonList(Type.INTEGER)));

        JsonSimple arrEl = new JsonSimple("el", new HashSet<>(Collections.singletonList(Type.INTEGER)));

        arr.add(arrEl);
        complex1.add(sm1);
        complex1.add(sm2);
        complex1.add(arr);


        ///////////////////////////////////
        sm1 = new JsonSimple("simple_name", new HashSet<>(Collections.singletonList(Type.STRING)), "date");
        sm2 = new JsonSimple("simple_name", new HashSet<>(Collections.singletonList(Type.INTEGER)));

        arr = new JsonComplex("arr", new HashSet<>(Collections.singletonList(Type.INTEGER)));

        arrEl = new JsonSimple("el", new HashSet<>(Collections.singletonList(Type.NUMBER)));
        arr.add(arrEl);
        complex2.add(sm1);
        complex2.add(sm2);
        complex2.add(arr);

        Assert.assertNotEquals(complex1, complex2);

    }

}