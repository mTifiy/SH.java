package com.shpp.p2p.cs.improved.mkalich.assignment10;

import java.util.HashMap;
import java.util.LinkedList;


/**
 * This class performs calculation of a mathematical expression.
 * It also stores the parsed early formulas in a hashMap.
 */
public class Assignment10Part1 {

    /**
     * Previously analyzed formulas are saved here
     */
    public static HashMap<String, LinkedList<Object>> savedFormulas = new HashMap<>();

    /**
     * This method is the entry point to the program
     * The method input receives a formula with arguments, as well as the arguments themselves
     * First, the formula is broken down into individual values (such as numbers, operators, arguments)
     * Then there is a conversion to reverse Polish notation already disassembled.
     * But if the formula has already been parsed before
     * then we just copy it from the HashMap.
     * Then, instead of arguments, we substitute numbers (If any) and perform the calculation.
     *
     * @param args Main method arguments
     */
    public static void main(String[] args) {
        // Here we refer to a class that splits a string into numbers, operators, arguments
        // assign result to array
        LinkedList<String> separateFormula = new SeparateNumbersAndOperators().separateNumbersAndOperators(args[0]);

        // The variable is assigned the result of the calculation of the class method
        // that checks the correctness of the formula
        boolean correctFormula = new CheckFormula().isCorrectFormula(separateFormula);

        if (correctFormula) {
            // Here we refer to a class that returns reverse polish notation from the passed array
            LinkedList<Object> parsePolishFormula = new Assignment10Part1().parsingOrOverwritingFormula(separateFormula);

            // Create a new array for the extended formula and copy the array into it, substituting the arguments
            LinkedList<Object> parsePolishFormulaToCalculate = replaceArguments(args, parsePolishFormula);

            // Refer to the class that evaluates the expression and returns the result
            Double answer = (Double) new Calculator().result(parsePolishFormulaToCalculate);
            // Display the result on the screen
            System.out.println(answer);
        }
    }

    /**
     * This method substitutes arguments into the cloned array
     * and return it.
     *
     * @param args main method arguments
     * @param parsePolishFormula reverse polish notation
     * @return copied array of Polish notation with argument replacement
     */
    private static LinkedList<Object> replaceArguments(String[] args, LinkedList<Object> parsePolishFormula) {
        LinkedList<Object> result = (LinkedList<Object>) parsePolishFormula.clone();
        for (int i = 1; i < args.length; i++) {
            LinkedList<String> arg = new SeparateNumbersAndOperators().separateNumbersAndOperators(args[i]);
            for (int j = 0; j < result.size(); j++)
                if (result.get(j).equals(arg.get(0)))
                    result.set(j, Double.parseDouble(arg.get(2)));
        }
        return result;
    }

    /**
     * This method checks if the formula has been parsed before, if so, it copies the saved formula
     * into an array, if not, parses the string and inserts a new wormula
     *
     * @param separateFormula array of operator and argument numbers
     * @return parsed reverse polish notation
     */
    private LinkedList<Object> parsingOrOverwritingFormula(LinkedList<String> separateFormula) {

        LinkedList<Object> parsePolishFormula;

        if (savedFormulas.containsKey(keyToFormula(separateFormula))) {
            parsePolishFormula = savedFormulas.get(keyToFormula(separateFormula));
            System.out.println("Hey, I am parsing this formula before!");
        } else {
            parsePolishFormula = new ReversePolishNotation().polishFormula(separateFormula);
            savedFormulas.put(keyToFormula(separateFormula), parsePolishFormula);
        }
        return parsePolishFormula;
    }

    /**
     * This method creates a key by which the parsed formula will be saved and searched
     *
     * @param separateFormula split formula with spaces removed
     * @return access key to a saved formula, or to save a formula
     */
    private static String keyToFormula(LinkedList<String> separateFormula) {
        StringBuilder result = new StringBuilder();
        for (String value : separateFormula)
            result.append(value);
        return result.toString();
    }
}