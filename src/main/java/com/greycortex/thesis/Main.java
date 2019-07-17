package com.greycortex.thesis;


import com.greycortex.thesis.json.JsonWrapper;
import com.greycortex.thesis.trie.Tree;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Objects;

public class Main {


    public static void main(String[] args) {
        File folder = new File("/Users/sergeypanov/git/anomalydetector/src/main/resources/input");
        File[] listOfFiles = folder.listFiles();

        JSONParser parser = new JSONParser();

        for (File fl :
                Objects.requireNonNull(listOfFiles)) {
            if (fl.isFile()) {
                try (Reader reader = new FileReader(fl.getAbsoluteFile())){
                    JSONObject object = (JSONObject) parser.parse(reader);
                    JsonWrapper wrapper = new JsonWrapper(object);
                    Tree tree = new Tree(wrapper);
                } catch (ParseException | IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
