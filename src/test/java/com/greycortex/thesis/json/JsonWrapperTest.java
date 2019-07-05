package com.greycortex.thesis.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Set;


public class JsonWrapperTest {

    @Test
    public void simpleWrapperKeySetTest() {

        JSONObject object = new JSONObject();
        JSONObject innerObject = new JSONObject();
        JSONArray arr = new JSONArray();
        JSONObject arrEL = new JSONObject();

        arrEL.put("arElement", "arElementValue");
        arr.add(arrEL);

        object.put("key1", "value1");

        innerObject.put("innerKey1", "innerValue1");

        object.put("innerObject", innerObject);
        object.put("array", arr);
        JsonWrapper wrapper = new JsonWrapper(object);
        Set set = wrapper.keySet();
        Assert.assertEquals(set, object.keySet());
    }


    @Test
    public void simpleValueTest() {
        JSONObject object = new JSONObject();
        object.put("key1", "value1");
        object.put("key2", 123);

        JsonWrapper wrapper = new JsonWrapper(object);

        Assert.assertEquals(wrapper.keySet(), object.keySet());
        Assert.assertEquals(wrapper.getString("key1"), (String) object.get("key1"));
        Assert.assertEquals(wrapper.getNumber("key2"), (Number) object.get("key2"));
    }



    @Test
    public void generalWrapperTest() {
        JSONObject object = new JSONObject();
        JSONObject innerObject = new JSONObject();
        JSONArray arr = new JSONArray();
        JSONObject arrEL = new JSONObject();

        arrEL.put("arElement", "arElementValue");
        arr.add(arrEL);

        object.put("key1", "value1");

        innerObject.put("innerKey1", "innerValue1");

        object.put("innerObject", innerObject);
        object.put("array", arr);

        JsonWrapper wrapper = new JsonWrapper(object);
        List<JsonWrapper> wrappers = wrapper.getArray("array");

        JSONArray array = (JSONArray) object.get("array");

        int index = 0;
        for (Object o :
                array) {
            if (o instanceof JSONObject) {
                Assert.assertEquals(wrappers.get(index).getObject(), (JSONObject)o);
            }
            index++;
        }

    }
}