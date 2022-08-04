package com.shpp.p2p.cs.collections.myStack;

import java.util.Stack;

/**
 * This class tests MyStack against Stack from the java library.
 * Initially, 2 class fields are created, which are collections.
 * For each collection, the methods of the class of these collections are called in turn.
 * followed by a comparison of the two collections. If the collections match, then the test is passed.
 */
public class TestMySteak {

    /**
     * Tested MyStack
     */
    static MyStack<String> myStack = new MyStack<>();

    /**
     * Pattern to compare the Stack under test
     */
    static Stack<String> stack = new Stack<>();

    /**
     * Variable that stores the test result
     */
    static boolean testIsPassed = true;

    /**
     * Size of the created Stack
     */
    static final int sizeOfStack = 3;

    /**
     * Entry point to the program.
     * In this method, the collection methods are called in turn, and the result is compared with the template.
     *
     * @param args main method arguments. Not used in the program
     */
    public static void main(String[] args) {
        pushInSteak();
        popAndPeekInStack();
        if (testIsPassed)
            System.out.println("Oll tests is passed");
        else
            System.out.println("Summonsing problem with testing");

    }

    /**
     * This method tests the pop() and peek() methods
     * The method is run after the stack is filled with data
     * Until the stack is empty, each stack is called in turn
     * the peek() method saving the given variables.
     * Until the stack becomes empty, values are removed from the stacks using the pop() method,
     * and compared with the variables on which the peek() method was called.
     * If the comparison in both cases does not give a true result, the test is considered not passed.
     * If the stackValue and myStackValue variables are not equal, the test is considered failed.
     * At the end of each iteration, the stacks are compared, if they are not equal, the test is considered not passed.
     * at the end of the method, the stack check is called again, if the check is successful, output to the console
     * information about this, if not, information about the stacks is displayed.
     */
    static void popAndPeekInStack() {
        while (!myStack.isEmpty() && !stack.isEmpty()) {

            String stackValue = stack.peek();
            String myStackValue = myStack.peek();

            if (!stack.isEmpty() && !(stackValue.equals(stack.pop()) && myStackValue.equals(myStack.pop()))) {
                System.out.println("summonsing problem with operator peek()");
                testIsPassed = false;
                break;
            }
            if (!stackValue.equals(myStackValue)) {
                System.out.println("summonsing problem with operator pop()");
                testIsPassed = false;
                break;
            }
            if (!stackIsEquals()) {
                stackStatus("pop, problem with value " + myStackValue + " and " + stackValue);
            }
        }
        stackStatus("pop and peek in stack ");
    }

    /**
     * This method tests the push() method.
     * Alternately, the push() method is called on the stacks.
     * At the end of each iteration, stacks are compared.
     * If the stacks are not equal, information is displayed on which iteration the problem occurred.
     * at the end of the method, the stack check is called again, if the check is successful, output to the console
     * information about this, if not, information about the stacks is displayed.
     */
    static void pushInSteak() {
        for (int i = 0; i < sizeOfStack; i++) {
            stack.push("Index " + i);
            myStack.push("Index " + i);
            if (!stackIsEquals()) {
                stackStatus("push, problem with iteration " + i);
            }
        }
        stackStatus("push in stack ");
    }

    /**
     * This method compares ArrayList and MyArrayList through the toString() method
     * true - if both methods have the same toString() method value
     * false - if methods have different toString() method values
     *
     * @return is ArrayList and MyArrayList are equals
     */
    static boolean stackIsEquals() {
        testIsPassed = myStack.toString().equals(stack.toString());
        return testIsPassed;
    }

    /**
     * This method prints the results of comparing ArrayList and MyArrayList to the console.
     * If ArrayList and MyArrayList are the same, prints "Test is passed"
     * If not, displays information about arrays.
     *
     * @param s Prints additional information to the console, indicating which test is currently running.
     *          Basically, it should store the name of the method being checked.
     */
    static void stackStatus(String s) {
        if (stackIsEquals()) {
            System.out.println(s + "Test is passed");
        } else {
            System.out.print("Test for " + s + ": \n");
            System.out.println("MyStack: " + myStack);
            System.out.println("  Stack:   " + stack);
        }
    }

}
