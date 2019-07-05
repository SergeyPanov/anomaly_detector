package com.greycortex.thesis.json;

import com.sun.istack.internal.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class JsonWrapper {
    private final JSONObject object;

    public JsonWrapper(JSONObject object) {
        this.object = object;
    }

    public JSONObject getObject() {
        return object;
    }

    public JsonWrapper getWrapped(String name) {
        return object.get(name) == null ? null : new JsonWrapper((JSONObject) object.get(name));
    }
    public List<JsonWrapper> getArray(String name) {
        List<JsonWrapper> wrappers = new ArrayList<>();

        JSONArray arr = (JSONArray) object.get(name);

        if (arr == null) return wrappers;

        for (Object o :
                arr) {
            if (o instanceof JSONObject) {
                wrappers.add(new JsonWrapper((JSONObject)o));
            }
        }
        return wrappers;
    }

    public Set keySet() {
        return object.keySet();
    }

    public String getString(String name) {
        return  (String)object.get(name);
    }

    public Number getNumber(String name) {
        return (Number) object.get(name);
    }
    @Override
    public String toString() {
        return object.toString();
    }
}
