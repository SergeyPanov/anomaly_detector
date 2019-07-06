package com.greycortex.thesis.trie;

import com.greycortex.thesis.json.JsonWrapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TrieTest {

    @Test
    public void simpleTreeCreationTest() throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse("{\n" +
                "  \"type\" : \"object\",\n" +
                "  \"properties\" : {\n" +
                "    \"numb1\" : {\n" +
                "      \"type\" : \"number\",\n" +
                "      \"minimum\" : 0\n" +
                "    },\n" +
                "    \"numb2\" : {\n" +
                "      \"type\" : \"number\",\n" +
                "      \"minimum\" : 0\n" +
                "    }\n" +
                "  },\n" +
                "  \"additionalProperties\" : false\n" +
                "}");

        Trie trie = new Trie(new JsonWrapper(object));
        Assert.assertEquals("{{name: numb1, type: number, min: 0, max: null}, {name: numb2, type: number, min: 0, max: null}}", trie.toString());
    }


    @Test
    public void simpleArrayTreeCreationTest() throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse("{\n" +
                "  \"type\" : \"object\",\n" +
                "  \"properties\" : {\n" +
                "    \"array\" : {\n" +
                "      \"type\" : \"array\",\n" +
                "      \"items\" : {\n" +
                "        \"type\" : \"integer\",\n" +
                "        \"maximum\" : 32767,\n" +
                "        \"minimum\" : 0\n" +
                "      }\n" +
                "    }\n" +
                "  },\n" +
                "  \"additionalProperties\" : false\n" +
                "}");

        Trie trie = new Trie((new JsonWrapper(object)));
    }


    @Test
    public void complexArrayTreeCreationTest() throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse("{\n" +
                "  \"type\" : \"object\",\n" +
                "  \"properties\" : {\n" +
                "    \"arr\" : {\n" +
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
                "    }\n" +
                "  },\n" +
                "  \"additionalProperties\" : false\n" +
                "}");

        Trie trie = new Trie((new JsonWrapper(object)));
    }


    @Test
    public void complexObjectCreationTest() throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse("{\n" +
                "  \"type\" : \"object\",\n" +
                "  \"properties\" : {\n" +
                "    \"simpleEl\" : {\n" +
                "      \"type\" : \"string\",\n" +
                "      \"minLength\" : 11,\n" +
                "      \"maxLength\" : 11\n" +
                "    },\n" +
                "    \"obj\" : {\n" +
                "      \"type\" : \"object\",\n" +
                "      \"properties\" : {\n" +
                "        \"el2\" : {\n" +
                "          \"type\" : \"string\",\n" +
                "          \"minLength\" : 10,\n" +
                "          \"maxLength\" : 10\n" +
                "        },\n" +
                "        \"el1\" : {\n" +
                "          \"type\" : \"string\",\n" +
                "          \"minLength\" : 3,\n" +
                "          \"maxLength\" : 3\n" +
                "        },\n" +
                "        \"el3\" : {\n" +
                "          \"type\" : \"number\",\n" +
                "          \"minimum\" : 0\n" +
                "        },\n" +
                "        \"ob\" : {\n" +
                "          \"type\" : \"object\",\n" +
                "          \"properties\" : {\n" +
                "            \"a\" : {\n" +
                "              \"type\" : \"integer\",\n" +
                "              \"maximum\" : 32767,\n" +
                "              \"minimum\" : 0\n" +
                "            },\n" +
                "            \"inob\" : {\n" +
                "              \"type\" : \"object\",\n" +
                "              \"properties\" : {\n" +
                "                \"b\" : {\n" +
                "                  \"type\" : \"integer\",\n" +
                "                  \"maximum\" : 32767,\n" +
                "                  \"minimum\" : 0\n" +
                "                }\n" +
                "              },\n" +
                "              \"additionalProperties\" : false\n" +
                "            }\n" +
                "          },\n" +
                "          \"additionalProperties\" : false\n" +
                "        }\n" +
                "      },\n" +
                "      \"additionalProperties\" : false\n" +
                "    },\n" +
                "    \"aotherSimple\" : {\n" +
                "      \"type\" : \"number\",\n" +
                "      \"minimum\" : 0\n" +
                "    }\n" +
                "  },\n" +
                "  \"additionalProperties\" : false\n" +
                "}");
        Trie trie = new Trie(new JsonWrapper(object));
    }

}