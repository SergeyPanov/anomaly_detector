package com.greycortex.thesis.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


public class JsonWrapperTest {

    @Test
    public void simpleWrapperKeySetTest() throws ParseException {

        JSONParser parser = new JSONParser();


        JSONObject object = (JSONObject) parser.parse("{\"type\": \"object\", \n" +
                "  \"key1\": \"value1\",\n" +
                "  \"innerObject\": {\n" +
                "    \"innerKey1\": \"innerValue1\"\n" +
                "  },\n" +
                "  \"array\": [\n" +
                "    {\n" +
                "      \"arElement\": \"arElementValue\"\n" +
                "    }\n" +
                "  ]\n" +
                "}");

        JsonWrapper wrapper = new JsonWrapper(object);
        Set set = wrapper.keySet();
        Assert.assertEquals( object.keySet(), set);
        Assert.assertEquals(Types.OBJECT, wrapper.getType());
    }


    @Test
    public void simpleValueTest() throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse("{\"type\": \"object\",\n" +
                "  \"key1\": \"value1\",\n" +
                "  \"key2\": 123\n" +
                "}");

        JsonWrapper wrapper = new JsonWrapper(object);

        Assert.assertEquals( object.keySet(), wrapper.keySet());
        Assert.assertEquals(object.get("key1"), wrapper.getString("key1"));
        Assert.assertEquals(object.get("key2"), wrapper.getNumber("key2"));
        Assert.assertEquals(Types.OBJECT, wrapper.getType());
    }




    @Test
    public void getDefaultStringTest() throws ParseException {
        JSONParser parser = new JSONParser();

        JSONObject object = (JSONObject) parser.parse(
                "{\"type\": \"object\", \n" +
                        "  \"key1\": \"value1\",\n" +
                        "  \"key2\": 123\n" +
                        "}"
        );
        JsonWrapper wrapper = new JsonWrapper(object);

        Assert.assertEquals( "value1", wrapper.getString("key1"));
        Assert.assertEquals("no-value", wrapper.getString("key3", "no-value"));
        Assert.assertEquals(Types.OBJECT, wrapper.getType());
    }

    @Test
    public void getDefaultNumberTest() throws ParseException {
        JSONParser parser = new JSONParser();

        JSONObject object = (JSONObject) parser.parse(
                "{\"type\": \"object\", \n" +
                        "  \"key1\": \"value1\",\n" +
                        "  \"key2\": 123\n" +
                        "}"
        );
        JsonWrapper wrapper = new JsonWrapper(object);
        Assert.assertEquals( 123l, wrapper.getNumber("key2"));
        Assert.assertEquals( -100, wrapper.getNumber("key3", -100));
        Assert.assertEquals(Types.OBJECT, wrapper.getType());
    }

    @Test
    public void simpleArrayTest() throws ParseException {
        JSONParser parser = new JSONParser();

        JSONObject object = (JSONObject) parser.parse("{\n" +
                "      \"type\" : \"array\",\n" +
                "      \"items\" : {\n" +
                "        \"type\" : [ \"object\", \"array\", \"string\", \"integer\" ],\n" +
                "        \"properties\" : {\n" +
                "          \"two\" : {\n" +
                "            \"type\" : \"integer\",\n" +
                "            \"maximum\" : 32767,\n" +
                "            \"minimum\" : 0\n" +
                "          },\n" +
                "          \"one\" : {\n" +
                "            \"type\" : \"integer\",\n" +
                "            \"maximum\" : 32767,\n" +
                "            \"minimum\" : 0\n" +
                "          },\n" +
                "          \"shit\" : {\n" +
                "            \"type\" : \"integer\",\n" +
                "            \"maximum\" : 32767,\n" +
                "            \"minimum\" : 0\n" +
                "          }\n" +
                "        },\n" +
                "        \"additionalProperties\" : false,\n" +
                "        \"items\" : {\n" +
                "          \"type\" : \"array\",\n" +
                "          \"items\" : {\n" +
                "            \"type\" : \"integer\",\n" +
                "            \"maximum\" : 32767,\n" +
                "            \"minimum\" : 0\n" +
                "          }\n" +
                "        },\n" +
                "        \"minLength\" : 3,\n" +
                "        \"maxLength\" : 9,\n" +
                "        \"maximum\" : 32767,\n" +
                "        \"minimum\" : 0\n" +
                "      }\n" +
                "    }");

        JsonWrapper wrapper = new JsonWrapper(object);

        wrapper.getSimpleArray("array");

        ArrayList<String> simples = new ArrayList<>(Arrays.asList("object", "array", "string", "integer"));

        for (int i = 0; i < simples.size(); i++) {
            Assert.assertEquals(simples.get(i), wrapper.getWrapped("items").getSimpleArray("type").get(i));
        }
        Assert.assertEquals(Types.ARRAY, wrapper.getType());
    }


    @Test
    public void complexArrayTest() throws ParseException {
        JSONParser parser = new JSONParser();

        JSONObject object = (JSONObject) parser.parse("{\"type\": \"array\", \"array\" : [ \"object\", \"array\", \"string\", \"integer\" ]}");

        JsonWrapper wrapper = new JsonWrapper(object);

        wrapper.getSimpleArray("array");

        ArrayList<String> simples = new ArrayList<>(Arrays.asList("object", "array", "string", "integer"));

        for (int i = 0; i < simples.size(); i++) {
            Assert.assertEquals(simples.get(i), wrapper.getSimpleArray("array").get(i));
        }
        Assert.assertEquals(Types.ARRAY, wrapper.getType());
    }
}