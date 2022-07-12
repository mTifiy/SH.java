package com.shpp.p2p.cs.improved.mkalich.assignment10;

import java.util.HashMap;
import java.util.LinkedList;

public class Assignment10Part1 {

    public static HashMap<String, LinkedList<Object>> savedFormulas = new HashMap<>();

    public static void main(String[] args) {

        LinkedList<String> separateFormula = new SeparateNumbersAndOperators().separateNumbersAndOperators(args[0]);

        LinkedList<Object> parsePolishFormula = new Assignment10Part1().parsingOrOverwritingFormula(separateFormula);

        LinkedList<Object> parsePolishFormulaToCalculate = replaceArguments(args, parsePolishFormula);

        Double answer = (Double) new Calculator().result(parsePolishFormulaToCalculate);
        System.out.println(answer);

    }

    private static LinkedList<Object> replaceArguments(String[] args, LinkedList<Object> parsePolishFormula) {
        LinkedList<Object> result = (LinkedList<Object>) parsePolishFormula.clone();
        for (int i = 1; i < args.length; i++) {
            LinkedList<String> arg = new SeparateNumbersAndOperators().separateNumbersAndOperators(args[i]);
            for (int j = 0; j < result.size(); j++)
                if (result.get(j).equals(arg.get(0)))
                    result.set(j, Double.parseDouble(arg.get(2)));
        }
        return result;
    }

    private LinkedList<Object> parsingOrOverwritingFormula(LinkedList<String> separateFormula) {
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