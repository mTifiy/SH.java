package com.shpp.p2p.cs.improved.mkalich.assignment10;

import org.junit.jupiter.api.Test;

import static com.shpp.p2p.cs.improved.mkalich.assignment10.Assignment10Part1.main;

class Assignment10Part1Test {
    public void mainTest(String[] input, String exert) {
        String str = "";
        for (String s : input) str += s + " ";
        System.out.println("Formula : " + str + "\nResult = ");
        main(input);
        System.out.println(" Good result = " + exert + "\n");
    }

    @Test
    public void mainTest01() {
        String[] input = {"-a^+b", "a = 2", "b = 3"};
        String exp = "-0.125";
        mainTest(input, exp);
    }
    @Test
    public void mainTest02() {
        String[] input = {"cos sin 30"};
        String exp = "-0.125";
        mainTest(input, exp);
    }
    @Test
    public void mainTest03() {
        String[] input = {"2 + 5.5.5"};
        String exp = "-0.125";
        mainTest(input, exp);
    }
    @Test
    public void mainTest04() {
        String[] input = {"(5+5))"};
        String exp = "-0.125";
        mainTest(input, exp);
    }
    @Test
    public void mainTest05() {
        String[] input = {"cos(cos)"};
        String exp = "-0.125";
        mainTest(input, exp);
    }
    @Test
    public void mainTest06() {
        String[] input = {"cos(+10)"};
        String exp = "-0.125";
        mainTest(input, exp);
    }
    @Test
    public void mainTest07() {
        String[] input = {"cos(sin(60)"};
        String exp = "-0.125";
        mainTest(input, exp);
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
        String exp = "100";
        mainTest(input, exp);
    }

    @Test
    public void mainTest3() {
        String[] input = {"((-2 +48)/5)- (45/5 *(6+6))"};
        String exp = "-98.8";
        mainTest(input, exp);
    }

    @Test
    public void mainTest4() {
        String[] input = {"1+-а", "а=-2"};
        String exp = "3";
        mainTest(input, exp);
    }

    @Test
    public void mainTest5() {
        String[] input = {"sqrt(c)-56.8 + d", "c = 1024", "d=5.5"};
        String exp = "-19.3";
        mainTest(input, exp);
    }

    @Test
    public void mainTest6() {
        String[] input = {"log2(24)"};
        String exp = "4.584";
        mainTest(input, exp);
    }

    @Test
    public void mainTest7() {
        String[] input = {"-2^-4"};
        String exp = "-0.0625";
        mainTest(input, exp);
    }

    @Test
    public void mainTest8() {
        String[] input = {"-a^-b", "a = 2", "b = 3"};
        String exp = "-0.125";
        mainTest(input, exp);
    }

    @Test
    public void mainTest9() {
        String[] input = {"15/(7-(1+1))*3-(2+(1+1))*15/(7-(200+1))*3-(2+(1+1))*(15/(7-(1+1))*3-(2+(1+1))+15/(7-(1+1))*3-(2+(1+1)))"};
        String exp = "−30.07216494845361";
        mainTest(input, exp);
    }


}