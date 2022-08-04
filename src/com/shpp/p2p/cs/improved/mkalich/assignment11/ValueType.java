package com.shpp.p2p.cs.improved.mkalich.assignment11;

/**
 * This class stores methods that are used in several classes at once.
 * All methods return information about the string that is passed to the method
 */
public class ValueType {

    /**
     * @param value String
     * @return is there a number in the string?
     */
    public boolean isDigital(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @param symbol string
     * @return is the string a letter
     */
    public boolean isLetter(String symbol) {
        if (symbol.length() > 1) return false;
        return Character.isLetter(symbol.toCharArray()[0]);
    }

    /**
     * @param symbol symbol
     * @return is a character a letter
     */
    public boolean isLetter(char symbol) {
        return Character.isLetter(symbol);
    }

    /**
     * @param value string
     * @return is the string a statement
     */
    public boolean isOperator(String value) {
        String[] allOperators = {
                "+", "-", "/", "*", "^", "sin", "cos", "tan", "atan", "log10", "log2", "sqrt", "(", ")", "="};
        for (String s : allOperators)
            if (value.equals(s)) return true;
        return false;
    }

    /**
     * This method checks if a character is related to a number
     * @param symbol string
     * @return is related to number
     */
    public boolean canBeDigital(char symbol) {
        if (symbol == '.') return true;
        return Character.isDigit(symbol);
    }

    /**
     * This method returns the operator's precedence level
     * @param operator operator
     * @return priority level
     */
    public int priorityOfOperator(String operator) {
        return switch (operator) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            case "^" -> 3;
            case "sin", "cos", "tan", "atan", "log10", "log2", "sqrt" -> 4;
            default -> 0;
        };
    }
}