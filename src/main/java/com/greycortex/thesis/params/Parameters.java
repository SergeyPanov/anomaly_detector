package com.greycortex.thesis.params;


import org.apache.commons.cli.*;

/**
 * Just parse input parameters
 */
public class Parameters {

    private final Options options = new Options();

    private final String path;
    private final boolean isHelp;

    public Parameters(String[] args) throws ParseException {
        Option schema = new Option("s", "schema", true, "Path to JSON schema");

        Option help = new Option("h", "help", false, "Print this message");
        options.addOption(schema);
        options.addOption(help);

        CommandLineParser parser = new DefaultParser();
        CommandLine line = parser.parse(options, args);

        path = line.getOptionValue("schema");
        isHelp = line.hasOption("help");

    }

    public void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("Usage", options);
    }

    public String getPath() {
        return path;
    }

    public boolean isHelp() {
        return isHelp;
    }
}
