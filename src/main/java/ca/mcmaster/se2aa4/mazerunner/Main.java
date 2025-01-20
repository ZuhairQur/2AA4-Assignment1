package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Options options = new Options();

        options.addOption("i", "input", true, "Input file");

        CommandLineParser parser = new DefaultParser();
        logger.info("** Starting Maze Runner");
        
        try {

            CommandLine command = parser.parse(options, args);
            
            logger.info("**** Computing path");

            if (command.hasOption("i")) {
                logger.info("**** Reading the maze from file " + args[1]);
                BufferedReader reader = new BufferedReader(new FileReader(args[1]));
                String line;
                while ((line = reader.readLine()) != null) {
                    for (int idx = 0; idx < line.length(); idx++) {
                        if (line.charAt(idx) == '#') {
                            System.out.print("WALL ");
                        } else if (line.charAt(idx) == ' ') {
                            System.out.print("PASS ");
                        }
                    }
                    System.out.print(System.lineSeparator());
                }
                reader.close();
            }

            else {
                logger.info("Please enter a valid command.");
            }
        } catch(Exception e) {
            System.out.println("HI");
            logger.error("/!\\ An error has occured /!\\");
        }

        logger.warn("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}
