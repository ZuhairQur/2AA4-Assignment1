package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        inputHandler.setOptions();
        logger.info("** Starting Maze Runner");
        char[][] contents = inputHandler.readInput(args);
        logger.info("Shortest path: FFFF");
        logger.info("** End of MazeRunner");

        Maze maze = new Maze(contents);
        maze.setStartEndCoords();


        // Options options = new Options();

        // options.addOption("i", "input", true, "Input file");

        // CommandLineParser parser = new DefaultParser();
        // logger.info("** Starting Maze Runner");
        
        // try {

        //     CommandLine command = parser.parse(options, args);
        //     logger.info("**** Computing path");

        //     if (command.hasOption("i")) {
        //         String filename = command.getOptionValue("i");

        //         logger.info("**** Reading the maze from file " + filename);

        //         BufferedReader reader = new BufferedReader(new FileReader(filename));
        //         String line;
        //         while ((line = reader.readLine()) != null) {
        //             for (int idx = 0; idx < line.length(); idx++) {
        //                 if (line.charAt(idx) == '#') {
        //                     System.out.print("WALL ");
        //                 } else if (line.charAt(idx) == ' ') {
        //                     System.out.print("PASS ");
        //                 }
        //             }
        //             System.out.print(System.lineSeparator());
        //         }
        //         reader.close();
        //     }

        //     else {
        //         logger.info("Please enter a valid command.");
        //     }
        // } catch(Exception e) {
        //     logger.error("/!\\ An error has occured /!\\");
        // }

        // logger.warn("PATH NOT COMPUTED");
        // logger.info("** End of MazeRunner");
    }
}
