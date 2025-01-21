package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

public class InputHandler {
    private BufferedReader reader;
    private Options options;
    private char[][] contents;
    private CommandLine command;
    private final CommandLineParser parser;

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

                System.out.println("**** Reading the maze from file " + filename);

                this.reader = new BufferedReader(new FileReader(filename));
                String line;
                while ((line = this.reader.readLine()) != null) {
                    this.contents[lineCount] = line.toCharArray();
                    lineCount++;

                    for (int idx = 0; idx < line.length(); idx++) {
                        if (line.charAt(idx) == '#') {
                            System.out.print("WALL ");
                        } else if (line.charAt(idx) == ' ') {
                            System.out.print("PASS ");
                        }
                    }
                    System.out.print(System.lineSeparator());
                }
                this.reader.close();
            }

            else {
                System.out.println("Please enter a valid command.");
            }
        } catch(Exception e) {
            System.out.println("/!\\ An error has occured /!\\");
        }

        return this.contents;
    }    

    public boolean hasInstructionFlag() {
        return this.command.hasOption("p");
    }


}
