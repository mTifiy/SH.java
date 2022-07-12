package com.shpp.p2p.cs.improved.mkalich.assignment10;

import java.util.LinkedList;

public class SeparateNumbersAndOperators extends ValueType  {

    private final LinkedList<String> dividedFormula = new LinkedList<>();

    private final String UNARY_MINUS = "UNARY";

    public LinkedList<String> separateNumbersAndOperators(String args) {
        breakString(args);
        correctMinusOperator();
        correctOperators();
        LinkedList<String> finalDividedFormula = new LinkedList<>();
        correctUnaryOperators(finalDividedFormula);
        return finalDividedFormula;
    }

    private void correctUnaryOperators(LinkedList<String> finalDividedFormula) {
        for (String value: dividedFormula){
            if (value.equals(UNARY_MINUS)){
                finalDividedFormula.add("-1");
                finalDividedFormula.add("*");
            }else{
                finalDividedFormula.add(value);
            }
        }
    }

    private void correctOperators() {
        if (dividedFormula.size() > 3)
            for (int i = 0; i < dividedFormula.size(); i++) {
                if (dividedFormula.get(i).equals("log") && dividedFormula.get(i + 1).equals("10")) {
                    dividedFormula.set(i, "log10");
                    dividedFormula.remove(i + 1);
                    i--;
                }
                if (dividedFormula.get(i).equals("log") && dividedFormula.get(i + 1).equals("2")) {
                    dividedFormula.set(i, "log2");
                    dividedFormula.remove(i + 1);
                    i--;
                }
            }
    }

    private void correctMinusOperator() {
        if (dividedFormula.get(0).equals("-")) {
            dividedFormula.set(0, UNARY_MINUS);
        }
        for (int i = 0; i < dividedFormula.size() - 2; i++) {
            if (isOperator(dividedFormula.get(i)) && dividedFormula.get(i + 1).equals("-") && isDigital(dividedFormula.get(i + 2))) {
                dividedFormula.set(i + 2, "-" + dividedFormula.get(i + 2));
                dividedFormula.remove(i + 1);
                i--;
            }
        }
        for (int i = 0; i < dividedFormula.size() - 2; i++) {
            if (isOperator(dividedFormula.get(i)) && dividedFormula.get(i + 1).equals("-") && isOperator(dividedFormula.get(i + 2))) {
                dividedFormula.set(i + 1, UNARY_MINUS);
            }
        }
    }

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

    private void addDigitalAndLetter(String digital, String letter) {
        if (!letter.isEmpty()) dividedFormula.add(letter);
        if (!digital.isEmpty()) dividedFormula.add(digital);
    }

    private void removeEmptyElement() {
        for (int i = 0; i < dividedFormula.size(); i++) {
            if (dividedFormula.get(i).isEmpty()) {
                dividedFormula.remove(i);
                i--;
            }
        }
    }

}

