package com.shpp.p2p.cs.improved.mkalich.assignment10;

import java.util.LinkedList;

/**
 * This class checks the correct input of the formula
 * The number of brackets, the sequence of operators, and the value types are checked for compliance.
 */
public class CheckFormula extends ValueType {

    /**
     * Variable that counts the number of open and closed brackets
     */
    private int ParenthesesBrackets = 0;
    /**
     * Variable counts the number of binary operators in a row
     */
    private int numberOfBinaryOperatorsInRow = 0;
    /**
     * variable counts the number of unary operators in a row
     */
    private int numberOfUnaryOperatorsInRow = 0;

    /**
     * This method checks the formula for correct input
     * If the formula does not match the requirement, return false
     *
     * @param separateFormula Array of values that make up the formula
     * @return is the formula valid
     */
    public boolean isCorrectFormula(LinkedList<String> separateFormula) {
        // Loop through each element of the array
        for (String value : separateFormula) {
            // if there is a number - the counters are reset
            if (isDigital(value) || isLetter(value)) {
                numberOfBinaryOperatorsInRow = 0;
                numberOfUnaryOperatorsInRow = 0;
                continue;
            }
            // The first 2 if statements make sure the number of open brackets matches the number
            // closed
            if (value.equals("(")) {
                ParenthesesBrackets++;
                continue;
            }
            if (value.equals(")")) {
                ParenthesesBrackets--;
                continue;
            }

            if (isUnknownFormulaComponent(value)) return false;

            if (isTwoBinaryOperatorsInRow(value)) return false;

            if (isAfterUnaryOperatorComesBinary(value)) return false;

        }
        if (ParenthesesBrackets != 0) {
            System.out.println("Invalid formula, number of parentheses does not converge");
            return false;
        }
        return true;
    }


    /**
     * If an operator with priority 4 comes to the input, the counter of unary operators increases
     * If a binary operator comes immediately after a unary operator, the method returns true
     * @param value operator
     * @return whether the binary operator is immediately after the unary operator
     */
    private boolean isAfterUnaryOperatorComesBinary(String value) {
        if (priorityOfOperator(value) == 4) numberOfUnaryOperatorsInRow++;
        if (numberOfUnaryOperatorsInRow == 1 && (priorityOfOperator(value) < 4 && priorityOfOperator(value) > 0)) {
            System.out.println("Invalid formula, after unary operator comes binary");
            return true;
        }
        return false;
    }

    /**
     * If a binary operator comes to the input (Priorities 1, 2, 3), the counter of binary operators increases
     * If there are 2 binary operators in a row, returns true
     * @param value operator
     * @return whether there are 2 binary operators in a row
     */
    private boolean isTwoBinaryOperatorsInRow(String value) {
        if (priorityOfOperator(value) < 4 && priorityOfOperator(value) > 0) numberOfBinaryOperatorsInRow++;
        else numberOfBinaryOperatorsInRow = 0;

        if (numberOfBinaryOperatorsInRow > 1) {
            System.out.println("Wrong formula, you have 2 binary operators in a row");
            return true;
        }
        return false;
    }

    /**
     * method returns true if the number of brackets does not match
     * @param value operator
     * @return Does the number of parentheses match?
     */
    private boolean isUnknownFormulaComponent(String value) {
        if (!isDigital(value) && !isLetter(value) && !isOperator(value)) {
            System.out.println("Unknown Formula Component: " + value);
            return true;
        }
        return false;
    }


}
