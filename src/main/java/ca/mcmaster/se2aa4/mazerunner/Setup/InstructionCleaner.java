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

package ca.mcmaster.se2aa4.mazerunner.Setup;

public class InstructionCleaner {
    

    /**
     * Factors a string of walking instructions by grouping consecutive instructions.
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
            
            // Counts the number of consecutive instructions
            if (currentInstruction == previousInstruction) {
                factorCounter++;
            } else {

                // Appends the factored instruction string
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
     * This method takes a string of factored instructions, removes all whitespace, and
     * processes the instructions to expand sequences of canonical ('F', 'L', 'R') instructions.
     * @param factoredInstructions the string of factored walking instructions
     * @return a string with unfactored walking instructions
     */
    public static String getUnfactoredInstructions(String factoredInstructions) {
        StringBuilder trimmedInstructions = new StringBuilder();
        StringBuilder factorDigits = new StringBuilder();
        int factorValue = 0;
        StringBuilder unfactoredInstructions = new StringBuilder();

        trimmedInstructions
        .append(factoredInstructions
        .replaceAll("\\s+",""))
        .append(" "); // padding to avoid index out of bounds

        // Iterate through space-removed unfactoredString
        for (int i = 0; i < trimmedInstructions.length() - 1; i++) {
            char currentInstruction = trimmedInstructions.charAt(i);
            
            // Store factor digits until reaching instruction to which it applies
            if (Character.isDigit(currentInstruction)) {
                factorDigits.append(currentInstruction);
            } else {
                
                // Convert the factor string to an int
                for (int k = 0; k < factorDigits.length(); k++) {
                    int placeValue = factorDigits.length() - k - 1;
                    int factorDigit = Character.getNumericValue(factorDigits.charAt(placeValue));
                    factorValue += (int)(Math.pow(10, k) * factorDigit);
                }
                
                unfactoredInstructions.append(currentInstruction);
                
                for (int j = 0; j < factorValue - 1; j++) {
                    unfactoredInstructions.append(currentInstruction);
                }
                
                // Reset factor digit string for next encounter of a factor
                factorDigits.setLength(0);
                factorValue = 0;
            }
        }
        
        return unfactoredInstructions.toString();
    }

}
