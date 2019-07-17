package com.greycortex.thesis.json;



import com.greycortex.thesis.trie.Tree;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

public class JsonComplexTest {
    /**
     * {
     *   "id": 1,
     *   "num": 1,
     *   "str1": "string",
     *   "arr": [1,2,3,4],
     *   "ob": {
     *     "id": 1,
     *     "num1": 1
     *   }
     * }
     * @throws ParseException
     */
    @Test
    public void getTableTest() throws ParseException {
        JSONParser parser =  new JSONParser();
        JSONObject object = (JSONObject) parser.parse("{\n" +
                "  \"type\" : \"object\",\n" +
                "  \"properties\" : {\n" +
                "    \"str1\" : {\n" +
                "      \"type\" : \"string\",\n" +
                "      \"minLength\" : 6,\n" +
                "      \"maxLength\" : 6\n" +
                "    },\n" +
                "    \"arrOb\" : {\n" +
                "      \"type\" : \"array\",\n" +
                "      \"items\" : {\n" +
                "        \"type\" : \"object\",\n" +
                "        \"properties\" : {\n" +
                "          \"inn\" : {\n" +
                "            \"type\" : \"integer\",\n" +
                "            \"maximum\" : 32767,\n" +
                "            \"minimum\" : 0\n" +
                "          }\n" +
                "        },\n" +
                "        \"additionalProperties\" : false\n" +
                "      }\n" +
                "    },\n" +
                "    \"num\" : {\n" +
                "      \"type\" : \"integer\",\n" +
                "      \"maximum\" : 32767,\n" +
                "      \"minimum\" : 0\n" +
                "    },\n" +
                "    \"id\" : {\n" +
                "      \"type\" : \"integer\",\n" +
                "      \"maximum\" : 32767,\n" +
                "      \"minimum\" : 0\n" +
                "    },\n" +
                "    \"ob\" : {\n" +
                "      \"type\" : \"object\",\n" +
                "      \"properties\" : {\n" +
                "        \"id\" : {\n" +
                "          \"type\" : \"integer\",\n" +
                "          \"maximum\" : 32767,\n" +
                "          \"minimum\" : 0\n" +
                "        },\n" +
                "        \"num1\" : {\n" +
                "          \"type\" : \"integer\",\n" +
                "          \"maximum\" : 32767,\n" +
                "          \"minimum\" : 0\n" +
                "        }\n" +
                "      },\n" +
                "      \"additionalProperties\" : false\n" +
                "    },\n" +
                "    \"arrStrs\" : {\n" +
                "      \"type\" : \"array\",\n" +
                "      \"items\" : {\n" +
                "        \"type\" : \"string\",\n" +
                "        \"minLength\" : 1,\n" +
                "        \"maxLength\" : 1\n" +
                "      }\n" +
                "    },\n" +
                "    \"arrNums\" : {\n" +
                "      \"type\" : \"array\",\n" +
                "      \"items\" : {\n" +
                "        \"type\" : \"integer\",\n" +
                "        \"maximum\" : 32767,\n" +
                "        \"minimum\" : 0\n" +
                "      }\n" +
                "    }\n" +
                "  },\n" +
                "  \"additionalProperties\" : false\n" +
                "}\n" +
                "\n");


        Tree tree = new Tree(new JsonWrapper(object));

        tree.getRoot().getTable();

    }
}
