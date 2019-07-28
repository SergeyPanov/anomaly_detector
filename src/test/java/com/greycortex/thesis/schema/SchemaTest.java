package com.greycortex.thesis.schema;

import com.greycortex.thesis.json.JsonWrapper;
import com.greycortex.thesis.trie.Tree;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SchemaTest {

    private static JSONObject object1;
    private static JSONObject object2;
    private static JSONObject object3;
    private static JSONObject object4;
    private static JSONObject object5;
    private static JSONObject objectSMB;
    private static JSONObject object6;
    private static JSONObject object7;

    @Before
    public void prepare() throws ParseException {
        JSONParser parser = new JSONParser();
        object1 = (JSONObject) parser.parse(
                "{\n" +
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
                        "          },\n" +
                        "          \"innStr\" : {\n" +
                        "            \"type\" : \"string\",\n" +
                        "            \"minLength\" : 3,\n" +
                        "            \"maxLength\" : 3\n" +
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
                        "    },\n" +
                        "    \"mixArray\" : {\n" +
                        "      \"type\" : \"array\",\n" +
                        "      \"items\" : {\n" +
                        "        \"type\" : [ \"object\", \"string\", \"integer\" ],\n" +
                        "        \"properties\" : {\n" +
                        "          \"shit\" : {\n" +
                        "            \"type\" : \"integer\",\n" +
                        "            \"maximum\" : 32767,\n" +
                        "            \"minimum\" : 0\n" +
                        "          }\n" +
                        "        },\n" +
                        "        \"additionalProperties\" : false,\n" +
                        "        \"minLength\" : 1,\n" +
                        "        \"maxLength\" : 1,\n" +
                        "        \"maximum\" : 32767,\n" +
                        "        \"minimum\" : 0\n" +
                        "      }\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"additionalProperties\" : false\n" +
                        "}\n"
        );

        object2 = (JSONObject) parser.parse(
                "{\n" +
                        "  \"type\" : \"object\",\n" +
                        "  \"properties\" : {\n" +
                        "    \"id\" : {\n" +
                        "      \"type\" : \"integer\",\n" +
                        "      \"maximum\" : 32767,\n" +
                        "      \"minimum\" : 0\n" +
                        "    },\n" +
                        "    \"ob\" : {\n" +
                        "      \"type\" : \"object\",\n" +
                        "      \"properties\" : {\n" +
                        "        \"arr\" : {\n" +
                        "          \"type\" : \"array\",\n" +
                        "          \"items\" : {\n" +
                        "            \"type\" : \"integer\",\n" +
                        "            \"maximum\" : 32767,\n" +
                        "            \"minimum\" : 0\n" +
                        "          }\n" +
                        "        },\n" +
                        "        \"obs\" : {\n" +
                        "          \"type\" : \"array\",\n" +
                        "          \"items\" : {\n" +
                        "            \"type\" : \"object\",\n" +
                        "            \"properties\" : {\n" +
                        "              \"a\" : {\n" +
                        "                \"type\" : \"integer\",\n" +
                        "                \"maximum\" : 32767,\n" +
                        "                \"minimum\" : 0\n" +
                        "              },\n" +
                        "              \"otherOb\" : {\n" +
                        "                \"type\" : \"object\",\n" +
                        "                \"properties\" : {\n" +
                        "                  \"field\" : {\n" +
                        "                    \"type\" : \"integer\",\n" +
                        "                    \"maximum\" : 32767,\n" +
                        "                    \"minimum\" : 0\n" +
                        "                  }\n" +
                        "                },\n" +
                        "                \"additionalProperties\" : false\n" +
                        "              }\n" +
                        "            },\n" +
                        "            \"additionalProperties\" : false\n" +
                        "          }\n" +
                        "        }\n" +
                        "      },\n" +
                        "      \"additionalProperties\" : false\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"additionalProperties\" : false\n" +
                        "}\n"
        );

        object3 = (JSONObject) parser.parse(
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

        object4 = (JSONObject) parser.parse(
                "{\n" +
                        "  \"type\" : \"object\",\n" +
                        "  \"properties\" : {\n" +
                        "    \"arrObs1\" : {\n" +
                        "      \"type\" : \"array\",\n" +
                        "      \"items\" : {\n" +
                        "        \"type\" : \"object\",\n" +
                        "        \"properties\" : {\n" +
                        "          \"inn1\" : {\n" +
                        "            \"type\" : \"integer\",\n" +
                        "            \"maximum\" : 32767,\n" +
                        "            \"minimum\" : 0\n" +
                        "          }\n" +
                        "        },\n" +
                        "        \"additionalProperties\" : false\n" +
                        "      }\n" +
                        "    },\n" +
                        "    \"ob\" : {\n" +
                        "      \"type\" : \"object\",\n" +
                        "      \"properties\" : {\n" +
                        "        \"arrObs1\" : {\n" +
                        "          \"type\" : \"array\",\n" +
                        "          \"items\" : {\n" +
                        "            \"type\" : \"object\",\n" +
                        "            \"properties\" : {\n" +
                        "              \"inn1\" : {\n" +
                        "                \"type\" : \"integer\",\n" +
                        "                \"maximum\" : 32767,\n" +
                        "                \"minimum\" : 0\n" +
                        "              }\n" +
                        "            },\n" +
                        "            \"additionalProperties\" : false\n" +
                        "          }\n" +
                        "        }\n" +
                        "      },\n" +
                        "      \"additionalProperties\" : false\n" +
                        "    },\n" +
                        "    \"arrObs2\" : {\n" +
                        "      \"type\" : \"array\",\n" +
                        "      \"items\" : {\n" +
                        "        \"type\" : \"object\",\n" +
                        "        \"properties\" : {\n" +
                        "          \"inn1\" : {\n" +
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
                        "}\n"
        );

        object5 = (JSONObject) parser.parse(
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
                        "          \"idd\" : {\n" +
                        "            \"type\" : \"integer\",\n" +
                        "            \"maximum\" : 32767,\n" +
                        "            \"minimum\" : 0\n" +
                        "          },\n" +
                        "          \"str\" : {\n" +
                        "            \"type\" : \"string\",\n" +
                        "            \"minLength\" : 1,\n" +
                        "            \"maxLength\" : 1\n" +
                        "          }\n" +
                        "        },\n" +
                        "        \"additionalProperties\" : false\n" +
                        "      }\n" +
                        "    },\n" +
                        "    \"ob\" : {\n" +
                        "      \"type\" : \"object\",\n" +
                        "      \"properties\" : {\n" +
                        "        \"anotherId\" : {\n" +
                        "          \"type\" : \"integer\",\n" +
                        "          \"maximum\" : 32767,\n" +
                        "          \"minimum\" : 0\n" +
                        "        },\n" +
                        "        \"anotherArray\" : {\n" +
                        "          \"type\" : \"array\",\n" +
                        "          \"items\" : {\n" +
                        "            \"type\" : \"object\",\n" +
                        "            \"properties\" : {\n" +
                        "              \"idd\" : {\n" +
                        "                \"type\" : \"integer\",\n" +
                        "                \"maximum\" : 32767,\n" +
                        "                \"minimum\" : 0\n" +
                        "              },\n" +
                        "              \"str\" : {\n" +
                        "                \"type\" : \"string\",\n" +
                        "                \"minLength\" : 1,\n" +
                        "                \"maxLength\" : 1\n" +
                        "              }\n" +
                        "            },\n" +
                        "            \"additionalProperties\" : false\n" +
                        "          }\n" +
                        "        }\n" +
                        "      },\n" +
                        "      \"additionalProperties\" : false\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"additionalProperties\" : false\n" +
                        "}\n"
        );

        objectSMB = (JSONObject) parser.parse(
                "{\n" +
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
                        "}\n" +
                        "\n"
        );
        object6 = (JSONObject) parser.parse(
                "{\n" +
                        "  \"type\" : \"object\",\n" +
                        "  \"properties\" : {\n" +
                        "    \"id\" : {\n" +
                        "      \"type\" : \"integer\",\n" +
                        "      \"maximum\" : 32767,\n" +
                        "      \"minimum\" : 0\n" +
                        "    },\n" +
                        "    \"arr\" : {\n" +
                        "      \"type\" : \"array\",\n" +
                        "      \"items\" : {\n" +
                        "        \"type\" : \"object\",\n" +
                        "        \"properties\" : {\n" +
                        "          \"id\" : {\n" +
                        "            \"type\" : \"integer\",\n" +
                        "            \"maximum\" : 32767,\n" +
                        "            \"minimum\" : 0\n" +
                        "          }\n" +
                        "        },\n" +
                        "        \"additionalProperties\" : false\n" +
                        "      }\n" +
                        "    },\n" +
                        "    \"ob\" : {\n" +
                        "      \"type\" : \"object\",\n" +
                        "      \"properties\" : {\n" +
                        "        \"id\" : {\n" +
                        "          \"type\" : \"integer\",\n" +
                        "          \"maximum\" : 32767,\n" +
                        "          \"minimum\" : 0\n" +
                        "        }\n" +
                        "      },\n" +
                        "      \"additionalProperties\" : false\n" +
                        "    },\n" +
                        "    \"obOb\" : {\n" +
                        "      \"type\" : \"object\",\n" +
                        "      \"properties\" : {\n" +
                        "        \"id\" : {\n" +
                        "          \"type\" : \"integer\",\n" +
                        "          \"maximum\" : 32767,\n" +
                        "          \"minimum\" : 0\n" +
                        "        },\n" +
                        "        \"oob\" : {\n" +
                        "          \"type\" : \"object\",\n" +
                        "          \"properties\" : {\n" +
                        "            \"id\" : {\n" +
                        "              \"type\" : \"integer\",\n" +
                        "              \"maximum\" : 32767,\n" +
                        "              \"minimum\" : 0\n" +
                        "            }\n" +
                        "          },\n" +
                        "          \"additionalProperties\" : false\n" +
                        "        }\n" +
                        "      },\n" +
                        "      \"additionalProperties\" : false\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"additionalProperties\" : false\n" +
                        "}\n" +
                        "\n"
        );
        object7 = (JSONObject) parser.parse(
                "{\n" +
                        "  \"type\" : \"object\",\n" +
                        "  \"properties\" : {\n" +
                        "    \"id1\" : {\n" +
                        "      \"type\" : \"integer\",\n" +
                        "      \"maximum\" : 32767,\n" +
                        "      \"minimum\" : 0\n" +
                        "    },\n" +
                        "    \"ob1\" : {\n" +
                        "      \"type\" : \"object\",\n" +
                        "      \"properties\" : {\n" +
                        "        \"id2\" : {\n" +
                        "          \"type\" : \"integer\",\n" +
                        "          \"maximum\" : 32767,\n" +
                        "          \"minimum\" : 0\n" +
                        "        },\n" +
                        "        \"ob2\" : {\n" +
                        "          \"type\" : \"object\",\n" +
                        "          \"properties\" : {\n" +
                        "            \"id3\" : {\n" +
                        "              \"type\" : \"integer\",\n" +
                        "              \"maximum\" : 32767,\n" +
                        "              \"minimum\" : 0\n" +
                        "            },\n" +
                        "            \"ob3\" : {\n" +
                        "              \"type\" : \"object\",\n" +
                        "              \"properties\" : {\n" +
                        "                \"id4\" : {\n" +
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
                        "    }\n" +
                        "  },\n" +
                        "  \"additionalProperties\" : false\n" +
                        "}\n"
        );
    }

    private void printSchema(List<String> schema) {
        schema.forEach(System.out::println);
    }

    /**
     * {
     *   "id": 1,
     *   "num": 1,
     *   "str1": "string",
     *   "arrNums": [1,2,3,4],
     *   "arrStrs": ["1", "2"],
     *   "mixArray": [1,"1",  {"shit":  100}],
     *   "ob": {
     *     "id": 1,
     *     "num1": 1
     *   },
     *   "arrOb": [{"inn":  1}, {"inn":  2}, {"inn":  1, "innStr":  "str"}]
     * }
     * @throws ParseException
     */
    @Test
    public void generateScheme1Test() {
        Tree tree = new Tree(new JsonWrapper(object1));
        Schema schema = (new SchemaGenerator()).generateScheme(tree);
        printSchema(schema.getSQLSchema());
    }


    /**
     * {
     *   "id": 1,
     *   "ob": {
     *     "arr": [
     *       1,
     *       23,
     *       4
     *     ],
     *     "obs": [
     *       {
     *         "a": 1
     *       },
     *       {
     *         "a": 2
     *       },
     *       {
     *         "a": 3,
     *         "otherOb": {
     *           "field": 1
     *         }
     *       }
     *     ]
     *   }
     * }
     * @throws ParseException
     */
    @Test
    public void generateScheme2Test() {
        Tree tree = new Tree(new JsonWrapper(object2));
        Schema schema = (new SchemaGenerator()).generateScheme(tree);
        printSchema(schema.getSQLSchema());
    }

    @Test
    public void generateScheme3Test() {
        Tree tree = new Tree(new JsonWrapper(object3));
        Schema schema = (new SchemaGenerator()).generateScheme(tree);
        printSchema(schema.getSQLSchema());
    }

    @Test
    public void generateScheme4Test() {
        Tree tree = new Tree(new JsonWrapper(object4));
        Schema schema = (new SchemaGenerator()).generateScheme(tree);
        printSchema(schema.getSQLSchema());
    }


    @Test
    public void generateScheme5Test() {
        Tree tree = new Tree(new JsonWrapper(object5));
        Schema schema = (new SchemaGenerator()).generateScheme(tree);
        printSchema(schema.getSQLSchema());
    }


    @Test
    public void generateSchemeSMBTest() {
        Tree tree = new Tree(new JsonWrapper(objectSMB));
        Schema schema = (new SchemaGenerator()).generateScheme(tree);
        printSchema(schema.getSQLSchema());
    }

    @Test
    public void generateScheme6Test() {
        Tree tree = new Tree(new JsonWrapper(object6));
        Schema schema = (new SchemaGenerator()).generateScheme(tree);
        printSchema(schema.getSQLSchema());
    }


    @Test
    public void generateScheme7Test() {
        Tree tree = new Tree(new JsonWrapper(object7));
        Schema schema = (new SchemaGenerator()).generateScheme(tree);
        printSchema(schema.getSQLSchema());
    }
}