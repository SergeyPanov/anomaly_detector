package com.greycortex.thesis.trie;

import com.greycortex.thesis.json.JsonComplex;
import com.greycortex.thesis.json.JsonSimple;
import com.greycortex.thesis.json.JsonWrapper;
import com.greycortex.thesis.json.Type;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;


import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;


public class TreeTest {

    /**
     * {
     * "numb1": 123.2131,
     * "numb2": 123.321
     * }
     *
     * @throws ParseException
     */
    @Test
    public void simpleNumberTest() throws ParseException {
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
        Tree tree = new Tree(new JsonWrapper(object));

        Node root = new Node(new JsonComplex(null, null));

        JsonSimple smpl = new JsonSimple("numb1", new HashSet<>(Collections.singletonList(Type.NUMBER)));
        smpl.setMin(0L);


        Node numb1 = new Node(smpl);

        smpl = new JsonSimple("numb2", new HashSet<>(Collections.singletonList(Type.NUMBER)));
        smpl.setMin(0L);

        Node numb2 = new Node(smpl);

        root.add(0, numb2);
        root.add(0, numb1);

        Tree expectedTree = new Tree(root);

        Assert.assertEquals(expectedTree, tree);
    }


    /**
     * {
     * "array": [1,2,3,4]
     * }
     *
     * @throws ParseException
     */
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

        Tree tree = new Tree((new JsonWrapper(object)));


        Node root = new Node(new JsonComplex(null, null));
        Node arr = new Node(new JsonComplex("array", new HashSet<>(Collections.singletonList(Type.ARRAY))));

        JsonSimple items = new JsonSimple("array_items", new HashSet<>(Collections.singletonList(Type.INTEGER)));
        items.setMin(0L);
        items.setMax(32767L);


        arr.add(new Node(items));

        root.add(arr);
        Tree expectedTree = new Tree(root);

        Assert.assertEquals(expectedTree, tree);
    }


    /**
     * {
     * "arr": [
     * 111,
     * [
     * 111,
     * 222,
     * [
     * 222,
     * [
     * 111,
     * "aaa"
     * ]
     * ]
     * ]
     * ]
     * }
     *
     * @throws ParseException
     */
    @Test
    public void arrayOfArraysTest() throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(
                "{\n" +
                        "  \"type\" : \"object\",\n" +
                        "  \"properties\" : {\n" +
                        "    \"arr\" : {\n" +
                        "      \"type\" : \"array\",\n" +
                        "      \"items\" : {\n" +
                        "        \"type\" : [ \"array\", \"integer\" ],\n" +
                        "        \"items\" : {\n" +
                        "          \"type\" : [ \"array\", \"integer\" ],\n" +
                        "          \"items\" : {\n" +
                        "            \"type\" : [ \"array\", \"integer\" ],\n" +
                        "            \"items\" : {\n" +
                        "              \"type\" : [ \"string\", \"integer\" ],\n" +
                        "              \"minLength\" : 3,\n" +
                        "              \"maxLength\" : 3,\n" +
                        "              \"maximum\" : 32767,\n" +
                        "              \"minimum\" : 0\n" +
                        "            },\n" +
                        "            \"maximum\" : 32767,\n" +
                        "            \"minimum\" : 0\n" +
                        "          },\n" +
                        "          \"maximum\" : 32767,\n" +
                        "          \"minimum\" : 0\n" +
                        "        },\n" +
                        "        \"maximum\" : 32767,\n" +
                        "        \"minimum\" : 0\n" +
                        "      }\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"additionalProperties\" : false\n" +
                        "}"
        );

        Tree tree = new Tree(new JsonWrapper(object));

        JsonComplex arr = new JsonComplex("arr", new HashSet<>(Collections.singletonList(Type.ARRAY)));

        JsonComplex arrItems = new JsonComplex("arr_items", new HashSet<>(Arrays.asList(Type.INTEGER, Type.ARRAY)));
        JsonSimple arrItemsInt = new JsonSimple("arr_items", new HashSet<>(Collections.singletonList(Type.INTEGER)));
        arrItemsInt.setMin(0L);
        arrItemsInt.setMax(32767L);
        JsonComplex arrItemsArr = new JsonComplex("arr_items", new HashSet<>(Collections.singletonList(Type.ARRAY)));
        arrItems.add(arrItemsInt);
        arrItems.add(arrItemsArr);



        JsonComplex arrItemsItems = new JsonComplex("arr_items_items", new HashSet<>(Arrays.asList(Type.INTEGER, Type.ARRAY)));
        JsonSimple arrItemsItemsInt = new JsonSimple("arr_items_items", new HashSet<>(Collections.singletonList(Type.INTEGER)));
        arrItemsItemsInt.setMin(0L);
        arrItemsItemsInt.setMax(32767L);
        JsonComplex arrItemsItemsArr = new JsonComplex("arr_items_items", new HashSet<>(Arrays.asList(Type.INTEGER, Type.ARRAY)));
        arrItemsItems.add(arrItemsItemsInt);
        arrItemsItems.add(arrItemsItemsArr);

        JsonComplex arrItemsItemsItems = new JsonComplex("arr_items_items_items", new HashSet<>(Arrays.asList(Type.INTEGER, Type.ARRAY)));
        JsonSimple arrItemsItemsItemsInt = new JsonSimple("arr_items_items_items", new HashSet<>(Collections.singletonList(Type.INTEGER)));
        arrItemsItemsItemsInt.setMin(0L);
        arrItemsItemsItemsInt.setMax(32767L);
        JsonComplex arrItemsItemsItemsArr = new JsonComplex("arr_items_items_items", new HashSet<>(Arrays.asList(Type.INTEGER, Type.ARRAY)));
        arrItemsItemsItems.add(arrItemsItemsItemsInt);
        arrItemsItemsItems.add(arrItemsItemsItemsArr);



        JsonComplex arrItemsItemsItemsItems = new JsonComplex("arr_items_items_items_items", new HashSet<>(Arrays.asList(Type.INTEGER, Type.STRING)));
        JsonSimple arrItemsItemsItemsItemsInt = new JsonSimple("arr_items_items_items_items", new HashSet<>(Collections.singletonList(Type.INTEGER)));
        arrItemsItemsItemsItemsInt.setMin(0L);
        arrItemsItemsItemsItemsInt.setMax(32767L);
        JsonSimple arrItemsItemsItemsItemsStr = new JsonSimple("arr_items_items_items_items", new HashSet<>(Collections.singletonList(Type.STRING)));
        arrItemsItemsItemsItemsStr.setMin(3L);
        arrItemsItemsItemsItemsStr.setMax(3L);

        arrItemsItemsItemsItems.add(arrItemsItemsItemsItemsInt);
        arrItemsItemsItemsItems.add(arrItemsItemsItemsItemsStr);

        Node arrItemsItemsItemsItemsNd = new Node(arrItemsItemsItemsItems);
        arrItemsItemsItemsItemsNd.add(new Node(arrItemsItemsItemsItemsInt));
        arrItemsItemsItemsItemsNd.add(new Node(arrItemsItemsItemsItemsStr));

        Assert.assertEquals("{name: null, type: null, {name: arr, type: [ARRAY], {name: items, type: [INTEGER, ARRAY], {name: items, type: [INTEGER], min: 0, max: 32767, format: null}, {name: items, type: [ARRAY], {name: items, type: [INTEGER, ARRAY], {name: items, type: [INTEGER], min: 0, max: 32767, format: null}, {name: items, type: [ARRAY], {name: items, type: [INTEGER, ARRAY], {name: items, type: [INTEGER], min: 0, max: 32767, format: null}, {name: items, type: [ARRAY], {name: items, type: [INTEGER, STRING], {name: items, type: [INTEGER], min: 0, max: 32767, format: null}, {name: items, type: [STRING], min: 3, max: 3, format: null}}}}}}}}}}", tree.toString());
    }

    /**
     * {
     * "start_time": "2019-05-07T14:47:04.000429+02:00",
     * "end_time": "2019-05-07T14:48:05.636545+02:00",
     * "src_ip_addr": "192.168.137.250",
     * "dst_ip_addr": "192.168.137.5",
     * "src_domains": null,
     * "dst_domains": [
     * "win-server2019.gc.local"
     * ],
     * "dst_port": 445,
     * "service": "SMB",
     * "apps": null,
     * "src_pktcnt": 11,
     * "dst_pktcnt": 10,
     * "src_octets": 1490,
     * "dst_octets": 1650,
     * "src_app": [
     * {"new":true,"id":1,"tree_id":0,"session_id":0,"command":"SMB2_COMMAND_KEEPALIVE"},
     * {"new":true,"id":2,"tree_id":1,"session_id":70378407854117,"command":"SMB2_COMMAND_CREATE","filename":"<share_root>","disposition":"FILE_OPEN","access":"normal"},
     * {"new":true,"id":3,"tree_id":1,"session_id":70378407854117,"command":"SMB2_COMMAND_GET_INFO","get_info":{"class":"FILE_INFO","info_level":"0x12","fuid":"00004cc4-0010-0000-0001-000000000010"}},
     * {"new":true,"id":4,"tree_id":1,"session_id":70378407854117,"command":"SMB2_COMMAND_CREATE","filename":"<share_root>","disposition":"FILE_OPEN","access":"normal"},
     * {"new":true,"id":5,"tree_id":1,"session_id":70378407854117,"command":"SMB2_COMMAND_GET_INFO","get_info":{"class":"0x2","info_level":"0x7","fuid":"ffffffff-ffff-ffff-ffff-ffffffffffff"}},
     * {"new":true,"id":6,"tree_id":1,"session_id":70378407854117,"command":"SMB2_COMMAND_CLOSE"},
     * {"new":true,"id":7,"tree_id":1,"session_id":70378407854117,"command":"SMB2_COMMAND_TREE_DISCONNECT"},
     * {"new":true,"id":8,"tree_id":0,"session_id":70378407854117,"command":"SMB2_COMMAND_SESSION_LOGOFF"}
     * ],
     * "dst_app": [
     * {"new":true,"id":1,"tree_id":0,"session_id":0,"dialect":"unknown","status":"STATUS_SUCCESS","status_code":"0x0"},
     * {"new":true,"id":2,"tree_id":1,"session_id":70378407854117,"created":1550655519,"accessed":1557222021,"modified":1557222021,"changed":1557222021,"size":4096,"dialect":"unknown","status":"STATUS_SUCCESS","status_code":"0x0","fuid":"00004cc4-0010-0000-0001-000000000010"},
     * {"new":true,"id":3,"tree_id":1,"session_id":70378407854117,"dialect":"unknown","status":"STATUS_SUCCESS","status_code":"0x0","get_info":{"allocation_size":131951291195371385,"eof":132016956217536501,"link_count":3819745269,"delete_pending":true,"is_directory":true}},
     * {"new":true,"id":4,"tree_id":1,"session_id":70378407854117,"created":1550655519,"accessed":1557222021,"modified":1557222021,"changed":1557222021,"size":4096,"dialect":"unknown","status":"STATUS_SUCCESS","status_code":"0x0","fuid":"00004cc5-0010-0000-0005-000000000010"},
     * {"new":true,"id":5,"tree_id":1,"session_id":70378407854117,"dialect":"unknown","status":"STATUS_SUCCESS","status_code":"0x0","get_info":{"allocation_size":8247551,"eof":1478150,"link_count":1478150,"delete_pending":false,"is_directory":false}},
     * {"new":true,"id":6,"tree_id":1,"session_id":70378407854117,"dialect":"unknown","status":"STATUS_SUCCESS","status_code":"0x0"},
     * {"new":true,"id":7,"tree_id":1,"session_id":70378407854117,"dialect":"unknown","status":"STATUS_SUCCESS","status_code":"0x0"},
     * {"new":true,"id":8,"tree_id":0,"session_id":70378407854117,"dialect":"unknown","status":"STATUS_SUCCESS","status_code":"0x0"}
     * ]
     * }
     *
     * @throws ParseException
     */
    @Test
    public void smbTest() throws ParseException {
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

        Tree tree = new Tree((new JsonWrapper(object)));
        Assert.assertEquals("{name: null, type: null, {name: src_ip_addr, type: [STRING], min: 15, max: 15, format: ipv4}, {name: dst_octets, type: [INTEGER], min: 0, max: 32767, format: null}, {name: end_time, type: [STRING], min: 32, max: 32, format: date-time}, {name: src_domains, type: [NULL], min: null, max: null, format: null}, {name: start_time, type: [STRING], min: 32, max: 32, format: date-time}, {name: src_octets, type: [INTEGER], min: 0, max: 32767, format: null}, {name: src_app, type: [ARRAY], {name: items, type: [OBJECT], {name: new, type: [BOOLEAN], min: null, max: null, format: null}, {name: disposition, type: [STRING], min: 9, max: 9, format: null}, {name: filename, type: [STRING], min: 12, max: 12, format: null}, {name: access, type: [STRING], min: 6, max: 6, format: null}, {name: tree_id, type: [INTEGER], min: 0, max: 32767, format: null}, {name: session_id, type: [INTEGER], min: 0, max: 9223372036854775807, format: null}, {name: get_info, type: [OBJECT], {name: info_level, type: [STRING], min: 3, max: 4, format: null}, {name: fuid, type: [STRING], min: 36, max: 36, format: uuid}, {name: class, type: [STRING], min: 3, max: 9, format: null}}, {name: id, type: [INTEGER], min: 0, max: 32767, format: null}, {name: command, type: [STRING], min: 18, max: 28, format: null}}}, {name: dst_pktcnt, type: [INTEGER], min: 0, max: 32767, format: null}, {name: dst_ip_addr, type: [STRING], min: 13, max: 15, format: ipv4}, {name: service, type: [STRING], min: 3, max: 3, format: null}, {name: dst_port, type: [INTEGER], min: 0, max: 32767, format: null}, {name: dst_domains, type: [ARRAY], {name: items, type: [STRING], min: 23, max: 23, format: null}}, {name: dst_app, type: [ARRAY], {name: items, type: [OBJECT], {name: new, type: [BOOLEAN], min: null, max: null, format: null}, {name: status_code, type: [STRING], min: 3, max: 3, format: null}, {name: dialect, type: [STRING], min: 7, max: 7, format: null}, {name: tree_id, type: [INTEGER], min: 0, max: 32767, format: null}, {name: created, type: [INTEGER], min: 0, max: 2147483647, format: null}, {name: session_id, type: [INTEGER], min: 0, max: 9223372036854775807, format: null}, {name: accessed, type: [INTEGER], min: 0, max: 2147483647, format: null}, {name: size, type: [INTEGER], min: 0, max: 32767, format: null}, {name: modified, type: [INTEGER], min: 0, max: 2147483647, format: null}, {name: fuid, type: [STRING], min: 36, max: 36, format: uuid}, {name: get_info, type: [OBJECT], {name: is_directory, type: [BOOLEAN], min: null, max: null, format: null}, {name: delete_pending, type: [BOOLEAN], min: null, max: null, format: null}, {name: link_count, type: [INTEGER], min: 0, max: 9223372036854775807, format: null}, {name: eof, type: [INTEGER], min: 0, max: 9223372036854775807, format: null}, {name: allocation_size, type: [INTEGER], min: 0, max: 9223372036854775807, format: null}}, {name: id, type: [INTEGER], min: 0, max: 32767, format: null}, {name: changed, type: [INTEGER], min: 0, max: 2147483647, format: null}, {name: status, type: [STRING], min: 14, max: 14, format: null}}}, {name: src_pktcnt, type: [INTEGER], min: 0, max: 32767, format: null}, {name: apps, type: [NULL], min: null, max: null, format: null}}", tree.toString());
    }

    /**
     * {
     * "obj":{
     * "el1": "fst",
     * "el2": "2019-06-26",
     * "el3": 4.5,
     * "ob": {
     * "a": 1,
     * "inob":{
     * "b": 1
     * }
     * }
     * }
     * }
     *
     * @throws ParseException
     */
    @Test
    public void simpleObjectTest() throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(
                "{\n" +
                        "  \"type\" : \"object\",\n" +
                        "  \"properties\" : {\n" +
                        "    \"obj\" : {\n" +
                        "      \"type\" : \"object\",\n" +
                        "      \"properties\" : {\n" +
                        "        \"el1\" : {\n" +
                        "          \"type\" : \"string\",\n" +
                        "          \"minLength\" : 3,\n" +
                        "          \"maxLength\" : 3\n" +
                        "        },\n" +
                        "        \"el2\" : {\n" +
                        "          \"type\" : \"string\",\n" +
                        "          \"minLength\" : 10,\n" +
                        "          \"maxLength\" : 10\n" +
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
                        "    }\n" +
                        "  },\n" +
                        "  \"additionalProperties\" : false\n" +
                        "}");

        Tree tree = new Tree(new JsonWrapper(object));
        Assert.assertEquals("{name: null, type: null, {name: obj, type: [OBJECT], {name: ob, type: [OBJECT], {name: a, type: [INTEGER], min: 0, max: 32767, format: null}, {name: inob, type: [OBJECT], {name: b, type: [INTEGER], min: 0, max: 32767, format: null}}}, {name: el2, type: [STRING], min: 10, max: 10, format: null}, {name: el1, type: [STRING], min: 3, max: 3, format: null}, {name: el3, type: [NUMBER], min: 0, max: null, format: null}}}", tree.toString());
    }


    /**
     * {
     * "simpleEl": "simpleValue",
     * "obj":{
     * "el2": "2019-06-26",
     * "el1": "fst",
     * "el3": 4.5,
     * "ob": {
     * "a": 1,
     * "inob":{
     * "b": 1
     * }
     * }
     * },
     * "aotherSimple": 1.3
     * }
     *
     * @throws ParseException
     */
    @Test
    public void complexObjectCreationTest() throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(
                "{\n" +
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
        Tree tree = new Tree(new JsonWrapper(object));
        Assert.assertEquals("{name: null, type: null, {name: obj, type: [OBJECT], {name: ob, type: [OBJECT], {name: a, type: [INTEGER], min: 0, max: 32767, format: null}, {name: inob, type: [OBJECT], {name: b, type: [INTEGER], min: 0, max: 32767, format: null}}}, {name: el2, type: [STRING], min: 10, max: 10, format: null}, {name: el1, type: [STRING], min: 3, max: 3, format: null}, {name: el3, type: [NUMBER], min: 0, max: null, format: null}}, {name: aotherSimple, type: [NUMBER], min: 0, max: null, format: null}, {name: simpleEl, type: [STRING], min: 11, max: 11, format: null}}", tree.toString());
    }

    /**
     * {
     * "obarray": [
     * {
     * "el1": 1
     * },
     * {
     * "el2": 2,
     * "el1": 3,
     * "el3": 333
     * }
     * ]
     * }
     *
     * @throws ParseException
     */
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

        Tree tree = new Tree(new JsonWrapper(object));
        Assert.assertEquals("{name: null, type: null, {name: obarray, type: [ARRAY], {name: items, type: [OBJECT], {name: el2, type: [INTEGER], min: 0, max: 32767, format: null}, {name: el1, type: [INTEGER], min: 0, max: 32767, format: null}, {name: el3, type: [INTEGER], min: 0, max: 32767, format: null}}}}", tree.toString());
    }


    /**
     * {
     * "arr": [
     * 1,
     * 2,
     * 3.2,
     * "fst",
     * "asdasdasd",
     * [
     * [
     * 1
     * ]
     * ],
     * {
     * "asdasd": 1.3
     * },
     * {
     * "one": 2,
     * "two": {
     * "inn": 1
     * }
     * },
     * {
     * "two": 2
     * },
     * {
     * "shit": 1
     * },
     * [
     * 1,
     * 2,
     * 3.2,
     * "fst",
     * "asdasdasd",
     * [
     * [
     * 1
     * ],
     * 1
     * ],
     * {
     * "asdasd": 1.3
     * },
     * {
     * "one": 2,
     * "two": {
     * "inn": 1
     * }
     * },
     * {
     * "two": 2
     * },
     * {
     * "shit": 1
     * }
     * ],
     * null,
     * false,
     * true
     * ]
     * }
     *
     * @throws ParseException
     */
    @Test
    public void megaHeterogeneousTest() throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(
                "{\n" +
                        "  \"type\" : \"object\",\n" +
                        "  \"properties\" : {\n" +
                        "    \"arr\" : {\n" +
                        "      \"type\" : \"array\",\n" +
                        "      \"items\" : {\n" +
                        "        \"type\" : [ \"number\", \"string\", \"null\", \"object\", \"array\", \"boolean\" ],\n" +
                        "        \"properties\" : {\n" +
                        "          \"one\" : {\n" +
                        "            \"type\" : \"integer\",\n" +
                        "            \"maximum\" : 32767,\n" +
                        "            \"minimum\" : 0\n" +
                        "          },\n" +
                        "          \"two\" : {\n" +
                        "            \"type\" : [ \"object\", \"integer\" ],\n" +
                        "            \"properties\" : {\n" +
                        "              \"inn\" : {\n" +
                        "                \"type\" : \"integer\",\n" +
                        "                \"maximum\" : 32767,\n" +
                        "                \"minimum\" : 0\n" +
                        "              }\n" +
                        "            },\n" +
                        "            \"additionalProperties\" : false,\n" +
                        "            \"maximum\" : 32767,\n" +
                        "            \"minimum\" : 0\n" +
                        "          },\n" +
                        "          \"asdasd\" : {\n" +
                        "            \"type\" : \"number\",\n" +
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
                        "          \"type\" : [ \"object\", \"array\", \"string\", \"number\" ],\n" +
                        "          \"properties\" : {\n" +
                        "            \"one\" : {\n" +
                        "              \"type\" : \"integer\",\n" +
                        "              \"maximum\" : 32767,\n" +
                        "              \"minimum\" : 0\n" +
                        "            },\n" +
                        "            \"two\" : {\n" +
                        "              \"type\" : [ \"object\", \"integer\" ],\n" +
                        "              \"properties\" : {\n" +
                        "                \"inn\" : {\n" +
                        "                  \"type\" : \"integer\",\n" +
                        "                  \"maximum\" : 32767,\n" +
                        "                  \"minimum\" : 0\n" +
                        "                }\n" +
                        "              },\n" +
                        "              \"additionalProperties\" : false,\n" +
                        "              \"maximum\" : 32767,\n" +
                        "              \"minimum\" : 0\n" +
                        "            },\n" +
                        "            \"asdasd\" : {\n" +
                        "              \"type\" : \"number\",\n" +
                        "              \"minimum\" : 0\n" +
                        "            },\n" +
                        "            \"shit\" : {\n" +
                        "              \"type\" : \"integer\",\n" +
                        "              \"maximum\" : 32767,\n" +
                        "              \"minimum\" : 0\n" +
                        "            }\n" +
                        "          },\n" +
                        "          \"additionalProperties\" : false,\n" +
                        "          \"items\" : {\n" +
                        "            \"type\" : [ \"array\", \"integer\" ],\n" +
                        "            \"items\" : {\n" +
                        "              \"type\" : \"integer\",\n" +
                        "              \"maximum\" : 32767,\n" +
                        "              \"minimum\" : 0\n" +
                        "            },\n" +
                        "            \"maximum\" : 32767,\n" +
                        "            \"minimum\" : 0\n" +
                        "          },\n" +
                        "          \"minLength\" : 3,\n" +
                        "          \"maxLength\" : 9,\n" +
                        "          \"minimum\" : 0\n" +
                        "        },\n" +
                        "        \"minLength\" : 3,\n" +
                        "        \"maxLength\" : 9,\n" +
                        "        \"minimum\" : 0\n" +
                        "      }\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"additionalProperties\" : false\n" +
                        "}"
        );
        Tree tree = new Tree(new JsonWrapper(object));
        Assert.assertEquals("{name: null, type: null, {name: arr, type: [ARRAY], {name: items, type: [NULL, OBJECT, NUMBER, ARRAY, STRING, BOOLEAN], {name: items, type: [INTEGER], min: 0, max: null, format: null}, {name: items, type: [STRING], min: 3, max: 9, format: null}, {name: items, type: [ARRAY], {name: items, type: [OBJECT, NUMBER, ARRAY, STRING], {name: items, type: [INTEGER], min: 0, max: null, format: null}, {name: items, type: [STRING], min: 3, max: 9, format: null}, {name: items, type: [ARRAY], {name: items, type: [INTEGER, ARRAY], {name: items, type: [INTEGER], min: 0, max: 32767, format: null}, {name: items, type: [ARRAY], {name: items, type: [INTEGER], min: 0, max: 32767, format: null}}}}, {name: items, type: [OBJECT], {name: shit, type: [INTEGER], min: 0, max: 32767, format: null}, {name: one, type: [INTEGER], min: 0, max: 32767, format: null}, {name: asdasd, type: [NUMBER], min: 0, max: null, format: null}, {name: two, type: [OBJECT, INTEGER], {name: two, type: [INTEGER], min: 0, max: 32767, format: null}, {name: two, type: [OBJECT], {name: inn, type: [INTEGER], min: 0, max: 32767, format: null}}}}}}, {name: items, type: [OBJECT], {name: shit, type: [INTEGER], min: 0, max: 32767, format: null}, {name: one, type: [INTEGER], min: 0, max: 32767, format: null}, {name: asdasd, type: [NUMBER], min: 0, max: null, format: null}, {name: two, type: [OBJECT, INTEGER], {name: two, type: [INTEGER], min: 0, max: 32767, format: null}, {name: two, type: [OBJECT], {name: inn, type: [INTEGER], min: 0, max: 32767, format: null}}}}}}}", tree.toString());
    }


    @Test
    public void singleBitTest() throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(
                "{\n" +
                        "  \"type\" : \"object\",\n" +
                        "  \"properties\" : {\n" +
                        "    \"nl\" : {\n" +
                        "      \"type\" : \"null\"\n" +
                        "    },\n" +
                        "    \"bl\" : {\n" +
                        "      \"type\" : \"boolean\"\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"additionalProperties\" : false\n" +
                        "}"
        );

        Tree tree = new Tree(new JsonWrapper(object));
        Assert.assertEquals("{name: null, type: null, {name: bl, type: [BOOLEAN], min: null, max: null, format: null}, {name: nl, type: [NULL], min: null, max: null, format: null}}", tree.toString());
    }

}