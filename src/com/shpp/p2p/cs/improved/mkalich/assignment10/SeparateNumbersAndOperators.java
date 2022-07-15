package com.shpp.p2p.cs.improved.mkalich.assignment10;

import java.util.LinkedList;

/**
 * This class breaks a string into separate elements such as operator, number, argument
 * and returns an array of these values
 */
public class SeparateNumbersAndOperators extends ValueType {
    /**
     * Array into which number operators and arguments are entered
     */
    private final LinkedList<String> dividedFormula = new LinkedList<>();
    /**
     * This line is entered instead of a unary minus
     */
    private final String UNARY_MINUS = "UNARY";
    /**
     * This line is entered instead of a unary minus before the argument
     */
    private final String UNARY_MINUS_BEFORE_LETTER = "UNARY_BEFORE_LETTER";

    /**
     * This method splits the string into separate elements such as operator, number, argument
     * and returns an array of these values
     *
     * @param args main method argument
     * @return an array of number operators and arguments that creates a mathematical expression
     */
    public LinkedList<String> separateNumbersAndOperators(String args) {
        // Split string into number operators and arguments
        breakString(args);
        // Adjust the minus operator
        correctMinusOperator();
        // Designate unary minus
        correctOperators();
        // Create a new array for the target version
        LinkedList<String> finalDividedFormula = new LinkedList<>();
        // Adjust unary minus
        correctUnaryOperators(finalDividedFormula);
        // Return an array
        return finalDividedFormula;
    }

    /**
     * This method replaces unary minus with "-1 *"
     * and writes it to the final version
     * If the argument is preceded by a unary minus, it is additionally taken in parentheses
     *
     * @param finalDividedFormula Final array of formulas to return
     */
    private void correctUnaryOperators(LinkedList<String> finalDividedFormula) {
        for (int i = 0; i < dividedFormula.size(); i++) {
            if (dividedFormula.get(i).equals(UNARY_MINUS)) {
                finalDividedFormula.add("-1");
                finalDividedFormula.add("*");
            } else if (dividedFormula.get(i).equals(UNARY_MINUS_BEFORE_LETTER)) {
                finalDividedFormula.add("(");
                finalDividedFormula.add("-1");
                finalDividedFormula.add("*");
                finalDividedFormula.add(dividedFormula.get(i + 1));
                finalDividedFormula.add(")");
                i++;
            } else {
                finalDividedFormula.add(dividedFormula.get(i));
            }
        }
    }

    /**
     * This method corrects the Log10 and log 2 statements
     * If the operator is preceded by a value of 2 or 10, it assigns it to the operator
     */
    private void correctOperators() {
        if (dividedFormula.size() > 3)
            for (int i = 0; i < dividedFormula.size(); i++) {
                if (dividedFormula.get(i).equals("log") && dividedFormula.get(i + 1).equals("10")) {
                    dividedFormula.set(i, "log10");
                    dividedFormula.remove(i + 1);
                    i--;
                } else if (dividedFormula.get(i).equals("log") && dividedFormula.get(i + 1).equals("2")) {
                    dividedFormula.set(i, "log2");
                    dividedFormula.remove(i + 1);
                    i--;
                }
            }
    }

    /**
     * This method defines a unary minus and marks it with a string constant for further processing
     * or assign it to a number
     */
    private void correctMinusOperator() {
        if (dividedFormula.get(0).equals("-")) {
            dividedFormula.set(0, UNARY_MINUS);
        }
        for (int i = 0; i < dividedFormula.size() - 2; i++) {
            if (dividedFormula.get(i).equals(")"))
                continue;
            if (isOperatorMinusDigital(i)) {
                dividedFormula.set(i + 2, "-" + dividedFormula.get(i + 2));
                dividedFormula.remove(i + 1);
                i--;
            }
            if (isOperatorMinusOperator(i)) {
                dividedFormula.set(i + 1, UNARY_MINUS);
            }
            if (isOperatorMinusLetter(i)) {
                dividedFormula.set(i + 1, UNARY_MINUS_BEFORE_LETTER);
            }
        }
    }

    /**
     * This method returns true if the values in the array are arranged as follows:
     * operator - minus - number
     *
     * @param index the index of the value to be checked in the formula
     * @return true or false
     */
    private boolean isOperatorMinusDigital(int index) {
        return isOperator(dividedFormula.get(index)) &&
                dividedFormula.get(index + 1).equals("-") &&
                isDigital(dividedFormula.get(index + 2));
    }

    /**
     * This method returns true if the values in the array are arranged as follows:
     * operator - minus - operator
     *
     * @param index the index of the value to be checked in the formula
     * @return true or false
     */
    private boolean isOperatorMinusOperator(int index) {
        return isOperator(dividedFormula.get(index)) &&
                dividedFormula.get(index + 1).equals("-") &&
                isOperator(dividedFormula.get(index + 2));
    }

    /**
     * This method returns true if the values in the array are arranged as follows:
     *operator-minus-operator
     *
     * @param index the index of the value to be checked in the formula
     * @return true or false
     */
    private boolean isOperatorMinusLetter(int index) {
        return isOperator(dividedFormula.get(index)) &&
                dividedFormula.get(index + 1).equals("-") &&
                isLetter(dividedFormula.get(index + 2));
    }

    /**
     * This method removes all spaces from the formula string, replaces two minuses with a plus,
     * and replaces , with .
     * then splits the string into an array of type char.
     * Characters are retrieved one by one from the character array.
     * If the character is likely to be a number, it is wrapped in the "digital" string, and so on until the first
     * character which cannot belong to a number.
     * after which the string "digital" is transferred to the array and reset to zero, the same with
     * letters. All characters are entered into the array as a separate value.
     *
     * @param formula formula from main arguments
     */
    private void breakString(String formula) {

        formula = formula.replaceAll(" ", "");
        formula = formula.replaceAll(",", ".");
        formula = formula.replaceAll("--", "+");

        StringBuilder digital = new StringBuilder();
        StringBuilder letter = new StringBuilder();
        boolean isDigital = true;
        char[] ch = formula.toCharArray();

        for (char symbol : ch) {

            if (canBeDigital(symbol)) {
                if (isDigital) {
                    dividedFormula.add(letter.toString());
                    letter = new StringBuilder();
                }
                isDigital = true;
                digital.append(symbol);
            } else if (isLetter(symbol)) {
                if (isDigital) {
                    dividedFormula.add(digital.toString());
                    digital = new StringBuilder();
                }
                isDigital = false;
                letter.append(symbol);
            } else {
                addDigitalAndLetter(digital.toString(), letter.toString());
                dividedFormula.add(symbol + "");
                digital = new StringBuilder();
                letter = new StringBuilder();
            }
        }
        addDigitalAndLetter(digital.toString(), letter.toString());
        removeEmptyElement();
    }

    /**
     * This method transfers strings with letters and numbers to an array if they are not empty
     *
     * @param digital string containing a numeric value
     * @param letter a string containing a literal value
     */
    private void addDigitalAndLetter(String digital, String letter) {
        if (!letter.isEmpty()) dividedFormula.add(letter);
        if (!digital.isEmpty()) dividedFormula.add(digital);
    }

    /**
     * This method removes empty elements from the array with the formula
     */
    private void removeEmptyElement() {
        for (int i = 0; i < dividedFormula.size(); i++) {
            if (dividedFormula.get(i).isEmpty()) {
                dividedFormula.remove(i);
                i--;
            }
        }
    }
}