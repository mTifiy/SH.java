package com.shpp.p2p.cs.improved.mkalich.assignment10;

import java.util.LinkedList;
import java.util.Stack;

public class ReversePolishNotation extends ValueType {

    private final  LinkedList<String> polishFormula = new LinkedList<>();
    private final  Stack<String> operators = new Stack<>();


    public LinkedList<String> polishFormula(LinkedList<String> splitFormula) {
        for (String value : splitFormula) {

            if (isDigital(value) || isLetter(value))
                polishFormula.add(value);
            else if (isLetter(value));

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

    private void  MoveAccordingToOperatorPrecedence(String value){
        while (!operators.isEmpty() && priorityOfOperator(value) <= priorityOfOperator(operators.peek()))
            polishFormula.add(operators.pop());
        operators.add(value);
    }

    private void moveEverythingInBrackets(){
        while (true) {
            if (operators.peek().equals("(")) break;
            else polishFormula.add(operators.pop());
        }
        operators.pop();
    }

}
