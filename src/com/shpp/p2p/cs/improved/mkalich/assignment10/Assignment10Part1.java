package com.shpp.p2p.cs.improved.mkalich.assignment10;

import java.util.HashMap;
import java.util.LinkedList;

public class Assignment10Part1 extends ValueType {

    public static HashMap<String, Object[]> savedFormulas = new HashMap<>();
    private LinkedList<String> polishFormula;


    public static void main(String[] args) {
        Assignment10Part1 pars = new Assignment10Part1();

        LinkedList<String> dividedFormula = new SeparateNumbersAndOperators().separateNumbersAndOperators(args[0]);
        LinkedList<String> polishFormula = new ReversePolishNotation().polishFormula(dividedFormula);

        Object[] parsePolishFormula = new Object[polishFormula.size()];

        if (savedFormulas.containsKey(keyToFormula(polishFormula)))
            parsePolishFormula = savedFormulas.get(keyToFormula(polishFormula));
        else
            pars.parsTheFormula(parsePolishFormula, polishFormula);

        savedFormulas.put(keyToFormula(polishFormula), parsePolishFormula);

        Double answer = new Calculator().result(polishFormula);
        System.out.println(answer);
    }

    private void parsTheFormula(Object[] parsePolishFormula, LinkedList<String> polishFormula) {
        for (int i = 0; i < polishFormula.size(); i++) {
            if (isDigital(polishFormula.get(i))) {
                parsePolishFormula[i] = Double.parseDouble(polishFormula.get(i));
            } else parsePolishFormula[i] = polishFormula.get(i);
        }
    }

    private static String keyToFormula(LinkedList<String> polishFormula) {
        StringBuilder result = new StringBuilder();
        for (String value : polishFormula)
            result.append(value);
        return result.toString();
    }
}