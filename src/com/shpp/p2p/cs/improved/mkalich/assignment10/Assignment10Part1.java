package com.shpp.p2p.cs.improved.mkalich.assignment10;

import java.util.HashMap;
import java.util.LinkedList;

public class Assignment10Part1 {

    public static HashMap<String, LinkedList<Object>> savedFormulas = new HashMap<>();

    public static void main(String[] args) {

        LinkedList<String> separateFormula = new SeparateNumbersAndOperators().separateNumbersAndOperators(args[0]);

        LinkedList<Object> parsePolishFormula = parsingOrOverwritingFormula(separateFormula);

        replaceArguments(args, parsePolishFormula);

        Double answer = (Double) new Calculator().result(parsePolishFormula);
        System.out.println(answer);
    }

    private static void replaceArguments(String[] args, LinkedList<Object> parsePolishFormula) {
        for (int i = 1; i < args.length; i++) {
            LinkedList<String> arg = new SeparateNumbersAndOperators().separateNumbersAndOperators(args[i]);
            for (int j = 0; j < parsePolishFormula.size(); j++)
                if (parsePolishFormula.get(j).equals(arg.get(0)))
                    parsePolishFormula.set(j, Double.parseDouble(arg.get(2)));
        }
    }

    private static LinkedList<Object> parsingOrOverwritingFormula(LinkedList<String> separateFormula) {
        LinkedList<Object> parsePolishFormula;
        if (savedFormulas.containsKey(keyToFormula(separateFormula))) {
            parsePolishFormula = savedFormulas.get(keyToFormula(separateFormula));
            System.out.println("Hey, I am calculate this formula before!");
        } else {
            parsePolishFormula = new ReversePolishNotation().polishFormula(separateFormula);
            savedFormulas.put(keyToFormula(separateFormula), parsePolishFormula);
        }
        return parsePolishFormula;
    }

    private static String keyToFormula(LinkedList<String> polishFormula) {
        StringBuilder result = new StringBuilder();
        for (String value : polishFormula)
            result.append(value);
        return result.toString();
    }
}