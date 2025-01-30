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
    

    /**
     * Factors a string of walking instructions by grouping consecutive instructions.
     * 
     * This method removes all whitespace from the input string, and then 
     * processes the instructions to factor sequences of consecutive instructions.
     * Each sequence of one or more consecutive instructions is replaced with a number representing
     * the count followed by that instruction.
     *
     * @param unfactoredInstructions the original string of walking instructions
     * @return a string with factored walking instructions
     */

    public static String getFactoredInstructions(String unfactoredInstructions) {
        StringBuilder trimmedInstructions = new StringBuilder();
        
        // removing all whitespace, except padding space at the end
        trimmedInstructions
        .append(unfactoredInstructions
        .replaceAll("\\s+",""))
        .append(" ");

        StringBuilder factoredInstructions = new StringBuilder("");
        

        int factorCounter = 1;

        for (int i = 1; i < trimmedInstructions.length(); i++) {
            char currentInstruction = trimmedInstructions.charAt(i);
            char previousInstruction = trimmedInstructions.charAt(i - 1);
            
            if (currentInstruction == previousInstruction) {
                factorCounter++;
            } else {
                String instruction = previousInstruction + " ";
                
                if (factorCounter > 1) {
                    instruction = factorCounter + instruction;
                } 
                
                factorCounter = 1;
                
                factoredInstructions.append(instruction);
            }
        }

        return factoredInstructions.toString();
    }


    /**
     * Expands a string of walking instructions by replacing factored 'F' instructions
     * with that many 'F' instructions.
     * 
     * This method takes a string of factored instructions, removes all whitespace, and
     * processes the instructions to expand sequences of 'F' instructions.
     * Each number followed by 'F' instruction is replaced with the that number
     * of 'F' instructions. Other characters are appended as is.
     *
     * @param factoredInstructions the string of factored walking instructions
     * @return a string with unfactored walking instructions
     */
    public static String getUnfactoredInstructions(String factoredInstructions) {
        StringBuilder trimmedInstructions = new StringBuilder();
        StringBuilder unfactoredInstructions = new StringBuilder();

        
        trimmedInstructions
        .append(factoredInstructions
        .replaceAll("\\s+",""))
        .append(" "); // padding to avoid index out of bounds

        for (int i = 0; i < trimmedInstructions.length() - 1; i++) {
            char currentInstruction = trimmedInstructions.charAt(i);
            char nextInstruction = trimmedInstructions.charAt(i+1);

            // Unfactoring the instruction based on factor provided
            if (Character.isDigit(currentInstruction) && Character.isAlphabetic(nextInstruction)) {
                for (int j = 0; j < Character.getNumericValue(currentInstruction); j++) {
                    unfactoredInstructions.append(nextInstruction);
                }
                i += 1; // skip the next instruction, as it has already been processed
            } else {
                unfactoredInstructions.append(currentInstruction);
            } 
        }
        
        return unfactoredInstructions.toString();
    }

}
