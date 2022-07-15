package com.shpp.p2p.cs.improved.mkalich.assignment10;

import java.util.LinkedList;
import java.util.Stack;

/**
 * This class evaluates the mathematical expression of reverse * Polish notation
 */
public class Calculator extends ValueType {
    /**
     * This stack is used to calculate.
     * Digits from the array with reverse Polish notation are added to it one by one,
     * and if an operator is encountered, then calculations occur with a change in the stack.
     */
    private final Stack<Object> result = new Stack<>();

    /**
     * This method returns the result of calculating a mathematical expression
     * reverse polish notation.
     * Numbers are added to the stack in turn, if an operator is encountered, then the calculation takes place
     * with stack change
     *
     * @param polishFormula reverse polish notation
     * @return result of calculation
     */
    public Object result(LinkedList<Object> polishFormula) {

        for (Object value : polishFormula) {
            if (value.getClass().equals(Double.class)) result.add(value);
            else doTheCalculation((String) value);
        }
        return result.get(0);
    }

    /**
     * This method performs calculations on the stack.
     * when a string with an operator enters the method input, depending on the operator
     * the last 2 or 1 values are taken from the stack, after which calculations are performed on them,
     * and the result is pushed back onto the stack.
     *
     * @param operator A string with a mathematical operator.
     */
    private void doTheCalculation(String operator) {

        if (priorityOfOperator(operator) < 4) {

            double firstDigital = (double) result.pop();
            double secondDigital = (double) result.pop();

            switch (operator) {
                case "+" -> result.add(secondDigital + firstDigital);
                case "-" -> result.add(secondDigital - firstDigital);
                case "/" -> result.add(secondDigital / firstDigital);
                case "*" -> result.add(secondDigital * firstDigital);
                case "^" -> result.add(Math.pow(secondDigital, firstDigital));
            }
        } else {

            double firstDigital = (double) result.pop();

            switch (operator) {
                case "sin" -> result.add(Math.sin(firstDigital));
                case "cos" -> result.add(Math.cos(firstDigital));
                case "tan" -> result.add(Math.tan(firstDigital));
                case "atan" -> result.add(Math.atan(firstDigital));
                case "log10" -> result.add(Math.log10(firstDigital));
                case "log2" -> result.add(Math.log(firstDigital) / Math.log(2));
                case "sqrt" -> result.add(Math.sqrt(firstDigital));
            }
        }

    }

}
