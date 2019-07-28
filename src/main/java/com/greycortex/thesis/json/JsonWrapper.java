package com.greycortex.thesis.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;


public class JsonWrapper {
    private final JSONObject object;

    private final Set<Type> type = new HashSet<>();

    public JsonWrapper(JSONObject object) {
        this.object = object;
        if (object.get(SchemaKeys.TYPE) == null) {
            type.add(Type.OBJECT);
        } else if (object.get(SchemaKeys.TYPE) instanceof JSONArray) {
            // Array can contain multiple types
            JSONArray tps = (JSONArray) object.get(SchemaKeys.TYPE);
            for (Object tp : tps) {
                type.add(Type.getEnum(((String) tp)));
            }
        } else {
            type.add(Type.getEnum(((String) object.get(SchemaKeys.TYPE))));
        }
    }

    public Set<Type> getType() {
        return type;
    }


    public JSONObject getObject() {
        return object;
    }

    public JsonWrapper getWrapped(String name) {
        return object.get(name) == null ? null : new JsonWrapper((JSONObject) object.get(name));
    }

    public List<JsonWrapper> getComplexArray(String name) {
        List<JsonWrapper> wrappers = new ArrayList<>();

        JSONArray arr = (JSONArray) object.get(name);

        if (arr == null) return wrappers;

        for (Object o :
                arr) {
            if (o instanceof JSONObject) {
                wrappers.add(new JsonWrapper((JSONObject) o));
            }
        }
        return wrappers;
    }

    public List<String> getSimpleArray(String name) {
        List<String> strings = new ArrayList<>();

        JSONArray arr = (JSONArray) object.get(name);

        if (arr == null) return strings;

        for (Object o :
                arr) {
            if (o instanceof String) {
                strings.add((String) o);
            }
        }
        return strings;
    }

    public Set keySet() {
        return object.keySet();
    }

    public String getString(String name) {
        return (String) object.get(name);
    }

    public String getString(String name, String defaultValue) {
        return getString(name) == null ? defaultValue : getString(name);
    }

    public Number getNumber(String name) {
        return (Number) object.get(name);
    }

    public Number getNumber(String name, Number defaultValue) {
        return getNumber(name) == null ? defaultValue : getNumber(name);
    }

    public Set<Map.Entry> entrySet() {
        return object.entrySet();
    }

    @Override
    public String toString() {
        return object.toString();
    }
}
