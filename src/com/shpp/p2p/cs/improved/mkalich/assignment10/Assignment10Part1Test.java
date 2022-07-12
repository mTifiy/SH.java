package com.shpp.p2p.cs.improved.mkalich.assignment10;

import org.junit.jupiter.api.Test;

import static com.shpp.p2p.cs.improved.mkalich.assignment10.Assignment10Part1.main;

class Assignment10Part1Test {
    public void mainTest(String[] input, String exert) {
        String str = "";
        for (String s : input) str += s + " ";
        System.out.println("Formuls : " + str + "\nResult = ");
        main(input);
        System.out.println(" Good result = " + exert + "\n");
    }

    @Test
    public void mainTest1() {
        String[] input = {"10^a", "a = 1"};
        String exp = "10";
        mainTest(input, exp);
    }

    @Test
    public void mainTest2() {
        String[] input = {"10^a", "a = 2"};
        String exp = "10";
        mainTest(input, exp);
    }

    @Test
    public void mainTest3() {
        String[] input = {"((-2 +48)/5)- (45/5 *(6+6))"};
        String exp = "10";
        mainTest(input, exp);
    }

    @Test
    public void mainTest4() {
        String[] input = {"1+-а", "а=-2"};
        String exp = "10";
        mainTest(input, exp);
    }

    @Test
    public void mainTest5() {
        String[] input = {"sqrt(c)-56.8 + d", "c = 1024", "d=5.5"};
        String exp = "10";
        mainTest(input, exp);
    }

    @Test
    public void mainTest6() {
        String[] input = {"log2(24)"};
        String exp = "10";
        mainTest(input, exp);
    }
}