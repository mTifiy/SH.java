package com.shpp.p2p.cs.improved.mkalich.assignment10;

import java.util.LinkedList;
import java.util.Stack;

public class Calculator extends ValueType{

    private static final Stack<Double> result = new Stack<>();

    public Double result(LinkedList<String> polishFormula){

        for (String value: polishFormula){
            if (isDigital(value)) result.add(Double.parseDouble(value));
            else doTheCalculation(value);
        }
        return result.get(0);
    }

    private void doTheCalculation(String operator) {

            if (priorityOfOperator(operator) < 4) {

                double firstDigital = result.pop();
                double secondDigital = result.pop();

                switch (operator) {
                    case "+" -> result.add(secondDigital + firstDigital);
                    case "-" -> result.add(secondDigital - firstDigital);
                    case "/" -> result.add(secondDigital / firstDigital);
                    case "*" -> result.add(secondDigital * firstDigital);
                    case "^" -> result.add(Math.pow(secondDigital, firstDigital));
                }
            } else {

                double firstDigital = result.pop();

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
