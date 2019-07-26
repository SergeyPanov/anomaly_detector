package com.greycortex.thesis.schema;

import com.greycortex.thesis.json.JsonWrapper;
import com.greycortex.thesis.trie.Tree;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SchemaTest {

    @Test
    public void generateSchemaTest() throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(
                "{\n" +
                        "  \"type\" : \"object\",\n" +
                        "  \"properties\" : {\n" +
                        "    \"id\" : {\n" +
                        "      \"type\" : \"integer\",\n" +
                        "      \"maximum\" : 32767,\n" +
                        "      \"minimum\" : 0\n" +
                        "    },\n" +
                        "    \"arrObs\" : {\n" +
                        "      \"type\" : \"array\",\n" +
                        "      \"items\" : {\n" +
                        "        \"type\" : \"object\",\n" +
                        "        \"properties\" : {\n" +
                        "          \"innerNum\" : {\n" +
                        "            \"type\" : \"integer\",\n" +
                        "            \"maximum\" : 32767,\n" +
                        "            \"minimum\" : 0\n" +
                        "          },\n" +
                        "          \"innerStr\" : {\n" +
                        "            \"type\" : \"string\",\n" +
                        "            \"minLength\" : 6,\n" +
                        "            \"maxLength\" : 6\n" +
                        "          },\n" +
                        "          \"otherObs\" : {\n" +
                        "            \"type\" : \"array\",\n" +
                        "            \"items\" : {\n" +
                        "              \"type\" : \"object\",\n" +
                        "              \"properties\" : {\n" +
                        "                \"id\" : {\n" +
                        "                  \"type\" : \"integer\",\n" +
                        "                  \"maximum\" : 32767,\n" +
                        "                  \"minimum\" : 0\n" +
                        "                },\n" +
                        "                \"diff\" : {\n" +
                        "                  \"type\" : \"object\",\n" +
                        "                  \"properties\" : {\n" +
                        "                    \"arr\" : {\n" +
                        "                      \"type\" : \"array\",\n" +
                        "                      \"items\" : {\n" +
                        "                        \"type\" : \"integer\",\n" +
                        "                        \"maximum\" : 32767,\n" +
                        "                        \"minimum\" : 0\n" +
                        "                      }\n" +
                        "                    }\n" +
                        "                  },\n" +
                        "                  \"additionalProperties\" : false\n" +
                        "                }\n" +
                        "              },\n" +
                        "              \"additionalProperties\" : false\n" +
                        "            }\n" +
                        "          }\n" +
                        "        },\n" +
                        "        \"additionalProperties\" : false\n" +
                        "      }\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"additionalProperties\" : false\n" +
                        "}\n"
        );

        Tree tree = new Tree(new JsonWrapper(object));

        Schema schema = (new SchemaGenerator()).generateScheme(tree);

    }

}