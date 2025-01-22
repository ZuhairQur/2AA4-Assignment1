package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InputHandler {
    private BufferedReader reader;
    private Options options;
    private char[][] contents;
    private CommandLine command;
    private final CommandLineParser parser;
    private static final Logger logger = LogManager.getLogger();

    public InputHandler() {
        this.options = new Options();
        this.parser = new DefaultParser();
    }

    public void setOptions() {
        this.options.addOption("i", "input", true, "File containing maze");
        this.options.addOption("p", "instructions", true, "Path to follow through maze");
    }

    public char[][] readInput(String[] args) {
        try {

            this.command = parser.parse(options, args);
            if (this.command.hasOption("i")) {
                String filename = this.command.getOptionValue("i");

                Path path = Path.of(filename);
                this.contents = new char[(int) Files.lines(path).count()][];
                int lineCount = 0;

                logger.info("**** Reading the maze from file " + filename);

                this.reader = new BufferedReader(new FileReader(filename));
                String line;
                while ((line = this.reader.readLine()) != null) {
                    this.contents[lineCount] = line.toCharArray();
                    

                    for (int idx = 0; idx < line.length(); idx++) {
                        if (line.charAt(idx) == '#') {
                            System.out.print("WALL ");
                        } else if (line.charAt(idx) == ' ') {
                            System.out.print("PASS ");
                        }
                    }

                    // Handling the edge case where a line is empty (i.e. all pass)
                    if (line.length() == 0) {
                        String emptyLine = "";
                        for (int i = 0; i < contents[0].length; i++) {
                            System.out.print("PASS ");
                            emptyLine += " ";
                        }
                        this.contents[lineCount] = emptyLine.toCharArray();
                    }

                    lineCount++;
                    System.out.print(System.lineSeparator());
                }
                this.reader.close();
            }

            else {
                logger.warn("Please enter a valid command.");
            }
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }

        return this.contents;
    }    

    public boolean hasInstructionFlag() {
        return this.command.hasOption("p");
    }


}
