package com.shpp.p2p.cs.improved.mkalich.assignment10;

public class ValueType {

    public boolean isDigital(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLetter(String symbol) {
        if (symbol.length() > 1) return false;
        return Character.isLetter(symbol.toCharArray()[0]);
    }

    public boolean isLetter(char symbol) {
        return Character.isLetter(symbol);
    }

    public boolean isOperator(String value) {
        String[] allOperators = {
                "+", "-", "/", "*", "^", "sin", "cos", "tan", "atan", "log10", "log2", "sqrt", "(", ")", "="};
        for (String s : allOperators)
            if (value.equals(s)) return true;
        return false;
    }

    public boolean canBeDigital(char symbol) {
        if (symbol == '.') return true;
        return Character.isDigit(symbol);
    }

    public int priorityOfOperator(String operator) {
        return switch (operator) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            case "^" -> 3;
            case "sin", "cos", "tan", "atan", "log10", "log2", "sqrt" -> 4;
            default -> 0;
        };
    }


}
