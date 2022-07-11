package com.shpp.p2p.cs.improved.mkalich.assignment10;

import java.util.LinkedList;
import java.util.Stack;

public class Calculator extends ValueType{

    private static final Stack<Object> result = new Stack<>();

    public Object result(LinkedList<Object> polishFormula){
        Double toClass = 0.5;
        for (Object value: polishFormula){
            if (value.getClass() == toClass.getClass()) result.add(value);
            else doTheCalculation((String) value);
        }
        return result.get(0);
    }

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
