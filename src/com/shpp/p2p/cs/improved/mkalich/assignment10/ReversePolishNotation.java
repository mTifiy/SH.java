package com.shpp.p2p.cs.improved.mkalich.assignment10;

import java.util.LinkedList;
import java.util.Stack;

/**
 * This class translates a mathematical expression into reverse Polish notation
 */
public class ReversePolishNotation extends ValueType {
    /**
     * An array in which to enter a mathematical expression in the format of reverse Polish notation
     */
    private final LinkedList<Object> polishFormula = new LinkedList<>();
    /**
     * Operators are pushed onto this stack.
     * After that, in the correct order, they are transferred to the array with reverse Polish notation.
     * This stack is used to order statements
     */
    private final Stack<String> operators = new Stack<>();

    /**
     * This method processes the math expression and translates it
     * to change Polish notation.
     * If the input value is of class double (number), it will simply go into the array.
     * If it is an operator:
     * 1) if the stack is empty - put the statement on the stack
     * 2) if it is "(" it goes on the stack
     * 3) If it is ")", then all operators from the stack are transferred to the array of reverse Polish notation
     * 4) If an operator's priority is higher than the priority of the last operator on the stack, it is added to the stack
     * 5) If the priority of the operator is less than or equal to the priority of the operator from the stack, then the operator from the stack
     * moved to a reverse polish array, after which the comparison operator is pushed onto the stack
     * 6) At the end, all n = operators remaining on the stack are transferred to the array according to the formula
     * @param splitFormula An array containing the formula.
     * @return reverse polish notation
     */
    public LinkedList<Object> polishFormula(LinkedList<String> splitFormula) {
        for (String value : splitFormula) {

            if (isDigital(value))
                polishFormula.add(Double.parseDouble(value));
            else if (isLetter(value))
                polishFormula.add(value);
            else if (isOperator(value)) {

                if (operators.isEmpty() || value.equals("("))
                    operators.add(value);

                else if (value.equals(")"))
                    moveEverythingInBrackets();

                else if (priorityOfOperator(value) > priorityOfOperator(operators.peek()))
                    operators.add(value);

                else if (priorityOfOperator(value) <= priorityOfOperator(operators.peek()))
                    MoveAccordingToOperatorPrecedence(value);

            }
        }
        while (!operators.isEmpty())
            polishFormula.add(operators.pop());
        return polishFormula;
    }

    /**
     * This method pops operators from the stack into an array of formulas while operator precedence is higher than precedence
     * last statement on the stack
     * @param value operator to compare
     */
    private void MoveAccordingToOperatorPrecedence(String value) {
        while (!operators.isEmpty() && priorityOfOperator(value) <= priorityOfOperator(operators.peek()))
            polishFormula.add(operators.pop());
        operators.add(value);
    }

    /**
     * This method moves all operators from the stack will not meet "("
     * then removes "("
     */
    private void moveEverythingInBrackets() {
        while (true) {
            if (operators.peek().equals("(")) break;
            else polishFormula.add(operators.pop());
        }
        operators.pop();
    }
}