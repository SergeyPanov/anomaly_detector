package com.greycortex.thesis.trie;


import com.greycortex.thesis.json.JsonComplex;
import com.greycortex.thesis.json.JsonSimple;
import com.greycortex.thesis.json.Type;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;


public class NodeTest {

    @Test
    public void equalsTest() {
        Node nd1 = new Node(new JsonSimple("simple", new HashSet<>(Collections.singletonList(Type.INTEGER)), null));
        Node nd2 = new Node(new JsonSimple("simple", new HashSet<>(Collections.singletonList(Type.INTEGER)), null));

        Assert.assertEquals(nd1, nd2);
    }

    @Test
    public void notEqualsTest() {
        Node nd1 = new Node(new JsonSimple("simple", new HashSet<>(Collections.singletonList(Type.STRING)), null));
        Node nd2 = new Node(new JsonSimple("simple", new HashSet<>(Collections.singletonList(Type.INTEGER)), null));

        Assert.assertNotEquals(nd1, nd2);
    }

//    @Test
//    public void equalsObjectsTest() {
//        JsonComplex c1 = new JsonComplex("cmplx", new HashSet<>(Collections.singletonList(Type.OBJECT)));
//        JsonComplex arr1 = new JsonComplex("arr", new HashSet<>(Collections.singletonList(Type.ARRAY)));
//
//        JsonSimple s1 = new JsonSimple("simpl1", new HashSet<>(Collections.singletonList(Type.INTEGER)));
//        JsonSimple s2 = new JsonSimple("simpl2", new HashSet<>(Collections.singletonList(Type.STRING)));
//
//        JsonSimple s3 = new JsonSimple("items", new HashSet<>(Collections.singletonList(Type.INTEGER)));
//
//        arr1.add(s3);
//
//
//        c1.add(s1);
//        c1.add(s2);
//        c1.add(s3);
//
//        JsonComplex c2 = new JsonComplex("cmplx", new HashSet<>(Collections.singletonList(Type.OBJECT)));
//        JsonComplex arr2 = new JsonComplex("arr", new HashSet<>(Collections.singletonList(Type.ARRAY)));
//
//        JsonSimple ss1 = new JsonSimple("simpl1", new HashSet<>(Collections.singletonList(Type.INTEGER)));
//        JsonSimple ss2 = new JsonSimple("simpl2", new HashSet<>(Collections.singletonList(Type.STRING)));
//
//        JsonSimple ss3 = new JsonSimple("items", new HashSet<>(Collections.singletonList(Type.STRING)));
//
//        c2.add(ss1);
//        c2.add(ss2);
//        c2.add(ss3);
//
//        Assert.assertNotEquals(c1, c2);
//
//    }

}
