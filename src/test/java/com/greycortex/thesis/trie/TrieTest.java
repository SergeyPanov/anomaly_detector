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
        JSONObject object = (JSONObject) parser.parse(" {\n" +
                "  \"type\" : \"object\",\n" +
                "  \"properties\" : {\n" +
                "    \"start_time\" : {\n" +
                "      \"type\" : \"string\",\n" +
                "      \"format\" : \"date-time\",\n" +
                "      \"minLength\" : 32,\n" +
                "      \"maxLength\" : 32\n" +
                "    },\n" +
                "    \"src_app\" : {\n" +
                "      \"type\" : \"array\",\n" +
                "      \"items\" : {\n" +
                "        \"type\" : \"object\",\n" +
                "        \"properties\" : {\n" +
                "          \"session_id\" : {\n" +
                "            \"type\" : \"integer\",\n" +
                "            \"maximum\" : 9223372036854775807,\n" +
                "            \"minimum\" : 0\n" +
                "          },\n" +
                "          \"tree_id\" : {\n" +
                "            \"type\" : \"integer\",\n" +
                "            \"maximum\" : 32767,\n" +
                "            \"minimum\" : 0\n" +
                "          },\n" +
                "          \"disposition\" : {\n" +
                "            \"type\" : \"string\",\n" +
                "            \"minLength\" : 9,\n" +
                "            \"maxLength\" : 9\n" +
                "          },\n" +
                "          \"get_info\" : {\n" +
                "            \"type\" : \"object\",\n" +
                "            \"properties\" : {\n" +
                "              \"class\" : {\n" +
                "                \"type\" : \"string\",\n" +
                "                \"minLength\" : 3,\n" +
                "                \"maxLength\" : 9\n" +
                "              },\n" +
                "              \"info_level\" : {\n" +
                "                \"type\" : \"string\",\n" +
                "                \"minLength\" : 3,\n" +
                "                \"maxLength\" : 4\n" +
                "              },\n" +
                "              \"fuid\" : {\n" +
                "                \"type\" : \"string\",\n" +
                "                \"format\" : \"uuid\",\n" +
                "                \"minLength\" : 36,\n" +
                "                \"maxLength\" : 36\n" +
                "              }\n" +
                "            },\n" +
                "            \"additionalProperties\" : false\n" +
                "          },\n" +
                "          \"command\" : {\n" +
                "            \"type\" : \"string\",\n" +
                "            \"minLength\" : 18,\n" +
                "            \"maxLength\" : 28\n" +
                "          },\n" +
                "          \"id\" : {\n" +
                "            \"type\" : \"integer\",\n" +
                "            \"maximum\" : 32767,\n" +
                "            \"minimum\" : 0\n" +
                "          },\n" +
                "          \"filename\" : {\n" +
                "            \"type\" : \"string\",\n" +
                "            \"minLength\" : 12,\n" +
                "            \"maxLength\" : 12\n" +
                "          },\n" +
                "          \"new\" : {\n" +
                "            \"type\" : \"boolean\"\n" +
                "          },\n" +
                "          \"access\" : {\n" +
                "            \"type\" : \"string\",\n" +
                "            \"minLength\" : 6,\n" +
                "            \"maxLength\" : 6\n" +
                "          }\n" +
                "        },\n" +
                "        \"additionalProperties\" : false\n" +
                "      }\n" +
                "    },\n" +
                "    \"end_time\" : {\n" +
                "      \"type\" : \"string\",\n" +
                "      \"format\" : \"date-time\",\n" +
                "      \"minLength\" : 32,\n" +
                "      \"maxLength\" : 32\n" +
                "    },\n" +
                "    \"dst_pktcnt\" : {\n" +
                "      \"type\" : \"integer\",\n" +
                "      \"maximum\" : 32767,\n" +
                "      \"minimum\" : 0\n" +
                "    },\n" +
                "    \"service\" : {\n" +
                "      \"type\" : \"string\",\n" +
                "      \"minLength\" : 3,\n" +
                "      \"maxLength\" : 3\n" +
                "    },\n" +
                "    \"dst_port\" : {\n" +
                "      \"type\" : \"integer\",\n" +
                "      \"maximum\" : 32767,\n" +
                "      \"minimum\" : 0\n" +
                "    },\n" +
                "    \"dst_domains\" : {\n" +
                "      \"type\" : \"array\",\n" +
                "      \"items\" : {\n" +
                "        \"type\" : \"string\",\n" +
                "        \"minLength\" : 23,\n" +
                "        \"maxLength\" : 23\n" +
                "      }\n" +
                "    },\n" +
                "    \"src_ip_addr\" : {\n" +
                "      \"type\" : \"string\",\n" +
                "      \"format\" : \"ipv4\",\n" +
                "      \"minLength\" : 15,\n" +
                "      \"maxLength\" : 15\n" +
                "    },\n" +
                "    \"src_pktcnt\" : {\n" +
                "      \"type\" : \"integer\",\n" +
                "      \"maximum\" : 32767,\n" +
                "      \"minimum\" : 0\n" +
                "    },\n" +
                "    \"dst_octets\" : {\n" +
                "      \"type\" : \"integer\",\n" +
                "      \"maximum\" : 32767,\n" +
                "      \"minimum\" : 0\n" +
                "    },\n" +
                "    \"dst_app\" : {\n" +
                "      \"type\" : \"array\",\n" +
                "      \"items\" : {\n" +
                "        \"type\" : \"object\",\n" +
                "        \"properties\" : {\n" +
                "          \"session_id\" : {\n" +
                "            \"type\" : \"integer\",\n" +
                "            \"maximum\" : 9223372036854775807,\n" +
                "            \"minimum\" : 0\n" +
                "          },\n" +
                "          \"size\" : {\n" +
                "            \"type\" : \"integer\",\n" +
                "            \"maximum\" : 32767,\n" +
                "            \"minimum\" : 0\n" +
                "          },\n" +
                "          \"tree_id\" : {\n" +
                "            \"type\" : \"integer\",\n" +
                "            \"maximum\" : 32767,\n" +
                "            \"minimum\" : 0\n" +
                "          },\n" +
                "          \"get_info\" : {\n" +
                "            \"type\" : \"object\",\n" +
                "            \"properties\" : {\n" +
                "              \"eof\" : {\n" +
                "                \"type\" : \"integer\",\n" +
                "                \"maximum\" : 9223372036854775807,\n" +
                "                \"minimum\" : 0\n" +
                "              },\n" +
                "              \"allocation_size\" : {\n" +
                "                \"type\" : \"integer\",\n" +
                "                \"maximum\" : 9223372036854775807,\n" +
                "                \"minimum\" : 0\n" +
                "              },\n" +
                "              \"is_directory\" : {\n" +
                "                \"type\" : \"boolean\"\n" +
                "              },\n" +
                "              \"link_count\" : {\n" +
                "                \"type\" : \"integer\",\n" +
                "                \"maximum\" : 9223372036854775807,\n" +
                "                \"minimum\" : 0\n" +
                "              },\n" +
                "              \"delete_pending\" : {\n" +
                "                \"type\" : \"boolean\"\n" +
                "              }\n" +
                "            },\n" +
                "            \"additionalProperties\" : false\n" +
                "          },\n" +
                "          \"status_code\" : {\n" +
                "            \"type\" : \"string\",\n" +
                "            \"minLength\" : 3,\n" +
                "            \"maxLength\" : 3\n" +
                "          },\n" +
                "          \"changed\" : {\n" +
                "            \"type\" : \"integer\",\n" +
                "            \"maximum\" : 2147483647,\n" +
                "            \"minimum\" : 0\n" +
                "          },\n" +
                "          \"accessed\" : {\n" +
                "            \"type\" : \"integer\",\n" +
                "            \"maximum\" : 2147483647,\n" +
                "            \"minimum\" : 0\n" +
                "          },\n" +
                "          \"modified\" : {\n" +
                "            \"type\" : \"integer\",\n" +
                "            \"maximum\" : 2147483647,\n" +
                "            \"minimum\" : 0\n" +
                "          },\n" +
                "          \"id\" : {\n" +
                "            \"type\" : \"integer\",\n" +
                "            \"maximum\" : 32767,\n" +
                "            \"minimum\" : 0\n" +
                "          },\n" +
                "          \"fuid\" : {\n" +
                "            \"type\" : \"string\",\n" +
                "            \"format\" : \"uuid\",\n" +
                "            \"minLength\" : 36,\n" +
                "            \"maxLength\" : 36\n" +
                "          },\n" +
                "          \"status\" : {\n" +
                "            \"type\" : \"string\",\n" +
                "            \"minLength\" : 14,\n" +
                "            \"maxLength\" : 14\n" +
                "          },\n" +
                "          \"new\" : {\n" +
                "            \"type\" : \"boolean\"\n" +
                "          },\n" +
                "          \"dialect\" : {\n" +
                "            \"type\" : \"string\",\n" +
                "            \"minLength\" : 7,\n" +
                "            \"maxLength\" : 7\n" +
                "          },\n" +
                "          \"created\" : {\n" +
                "            \"type\" : \"integer\",\n" +
                "            \"maximum\" : 2147483647,\n" +
                "            \"minimum\" : 0\n" +
                "          }\n" +
                "        },\n" +
                "        \"additionalProperties\" : false\n" +
                "      }\n" +
                "    },\n" +
                "    \"src_domains\" : {\n" +
                "      \"type\" : \"null\"\n" +
                "    },\n" +
                "    \"dst_ip_addr\" : {\n" +
                "      \"type\" : \"string\",\n" +
                "      \"format\" : \"ipv4\",\n" +
                "      \"minLength\" : 13,\n" +
                "      \"maxLength\" : 15\n" +
                "    },\n" +
                "    \"apps\" : {\n" +
                "      \"type\" : \"null\"\n" +
                "    },\n" +
                "    \"src_octets\" : {\n" +
                "      \"type\" : \"integer\",\n" +
                "      \"maximum\" : 32767,\n" +
                "      \"minimum\" : 0\n" +
                "    }\n" +
                "  },\n" +
                "  \"additionalProperties\" : false\n" +
                "}\n");

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


    @Test
    public void arrayOfObjectsTest() throws ParseException {
        JSONParser parser = new JSONParser();

        JSONObject object = (JSONObject) parser.parse("{\n" +
                "  \"type\" : \"object\",\n" +
                "  \"properties\" : {\n" +
                "    \"obarray\" : {\n" +
                "      \"type\" : \"array\",\n" +
                "      \"items\" : {\n" +
                "        \"type\" : \"object\",\n" +
                "        \"properties\" : {\n" +
                "          \"el2\" : {\n" +
                "            \"type\" : \"integer\",\n" +
                "            \"maximum\" : 32767,\n" +
                "            \"minimum\" : 0\n" +
                "          },\n" +
                "          \"el1\" : {\n" +
                "            \"type\" : \"integer\",\n" +
                "            \"maximum\" : 32767,\n" +
                "            \"minimum\" : 0\n" +
                "          },\n" +
                "          \"el3\" : {\n" +
                "            \"type\" : \"integer\",\n" +
                "            \"maximum\" : 32767,\n" +
                "            \"minimum\" : 0\n" +
                "          }\n" +
                "        },\n" +
                "        \"additionalProperties\" : false\n" +
                "      }\n" +
                "    }\n" +
                "  },\n" +
                "  \"additionalProperties\" : false\n" +
                "}");

        Trie trie = new Trie(new JsonWrapper(object));
    }

}