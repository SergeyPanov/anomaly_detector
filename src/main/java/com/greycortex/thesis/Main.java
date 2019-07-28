package com.greycortex.thesis;


import com.greycortex.thesis.json.JsonWrapper;
import com.greycortex.thesis.params.Parameters;
import com.greycortex.thesis.schema.Schema;
import com.greycortex.thesis.schema.SchemaGenerator;
import com.greycortex.thesis.trie.Tree;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class Main {


    public static void main(String[] args) throws org.apache.commons.cli.ParseException, IOException, ParseException {

        Parameters parameters = new Parameters(args);

        if (parameters.isHelp()) {
            parameters.printHelp();
            return;
        }

        File schemaFile = new File(parameters.getPath());

        if (schemaFile.exists()) {

            String readSchema = new String(Files.readAllBytes(Paths.get(parameters.getPath())));


            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(readSchema);

            JsonWrapper wrapper = new JsonWrapper(object);
            Tree tree = new Tree(wrapper);
            SchemaGenerator generator = new SchemaGenerator();
            Schema erdSchema = generator.generateScheme(tree);

            for (String query :
                    erdSchema.getSQLSchema()) {
                System.out.println(query);
            }


        }
    }

}
