package com.shpp.p2p.cs.improved.mkalich.assignment10;

import java.util.HashMap;
import java.util.LinkedList;

public class Assignment10Part1 extends ValueType {

    public static HashMap<String, LinkedList<Object>> savedFormulas = new HashMap<>();


    public static void main(String[] args) {

        Assignment10Part1 myObj = new Assignment10Part1();
        Calculator makeCalculator = new Calculator();

        LinkedList<String> polishFormula = new SeparateNumbersAndOperators().separateNumbersAndOperators(args[0]);
        polishFormula = new ReversePolishNotation().polishFormula(polishFormula);

        LinkedList<Object> parsePolishFormula = new LinkedList<>();

        myObj.parsingOrOverwritingFormula(polishFormula, parsePolishFormula);

        myObj.replaceArguments(args, parsePolishFormula);


        Double answer = (Double) makeCalculator.result(parsePolishFormula);
        System.out.println(answer);
    }

    private void replaceArguments(String[] args, LinkedList<Object> parsePolishFormula) {
        for (int i = 1; i < args.length; i++) {
            LinkedList<String> arg = new SeparateNumbersAndOperators().separateNumbersAndOperators(args[i]);
            for (int j = 0; j < parsePolishFormula.size(); j++)
                if (parsePolishFormula.get(j).equals(arg.get(0)))
                    parsePolishFormula.set(j, Double.parseDouble(arg.get(2)));
        }
    }

    private void parsingOrOverwritingFormula(LinkedList<String> polishFormula, LinkedList<Object> parsePolishFormula) {
        if (savedFormulas.containsKey(keyToFormula(polishFormula))) {
            parsePolishFormula = savedFormulas.get(keyToFormula(polishFormula));
            System.out.println("Hey, I am calculate this formula before!");
        } else {
            parsTheFormula(parsePolishFormula, polishFormula);
            savedFormulas.put(keyToFormula(polishFormula), parsePolishFormula);
        }
    }


    private void parsTheFormula(LinkedList<Object> parsePolishFormula, LinkedList<String> polishFormula) {
        for (String value : polishFormula) {
            if (isDigital(value)) {
                parsePolishFormula.add(Double.parseDouble(value));
            } else parsePolishFormula.add(value);
        }
    }

    private static String keyToFormula(LinkedList<String> polishFormula) {
        StringBuilder result = new StringBuilder();
        for (String value : polishFormula)
            result.append(value);
        return result.toString();
    }
}