/**
 * File: InstructionCleaner.java
 * Author: Zuhair Qureshi
 * Description: This class has static methods that process and clean strings 
 * of walking instructions by compressing or expanding them.
 * Specifically, it: provides functionality to factorize walking instructions 
 * by grouping repeated steps, removes unnecessary whitespace for streamlined
 * instruction formatting, and prepares walking instructions for efficient 
 * parsing and execution by Walker objects.
 */


package ca.mcmaster.se2aa4.mazerunner;

public class InstructionCleaner {
    

    public static String getFactoredInstructions(String unfactoredInstructions) {
        StringBuilder trimmedInstructions = new StringBuilder();
        
        // removing all whitespace, except single space at the end
        trimmedInstructions
        .append(unfactoredInstructions
        .replaceAll("\\s+",""))
        .append(" ");
        
        StringBuilder factoredInstructions = new StringBuilder("");
        

        int factorCounter = 0;

        for (int i = 0; i < trimmedInstructions.length(); i++) {
            char currentInstruction = trimmedInstructions.charAt(i);

            if (currentInstruction == 'F') {
                factorCounter++;
            } else {
                if (factorCounter > 0) {
                    factoredInstructions
                    .append(factorCounter)
                    .append("F")
                    .append(" ");
                    factorCounter = 0;
                }
                
                factoredInstructions
                .append(currentInstruction)
                .append(" ");
            }
        }

        return factoredInstructions.toString();
    }

    public String getUnfactoredInstructions() {
        // will be filled in with the opposite process of getFactoredInstructions
        return null;
    }

}
