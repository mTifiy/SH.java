package com.shpp.p2p.cs.collections.myArrayList;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class tests MyArrayList by comparing it to ArrayList from java library
 * From the beginning, 2 class fields are created, which are collections.
 * For each collection, the methods of the class of these collections are called in turn.
 * followed by a comparison of the two collections. If the collections are the same, then the test passed.
 * The number of method calls, as well as indexes (the location of the object in the collection) are determined
 * randomly.
 * The whole process is looped, and if at some point the collections do not match, the loop stops and outputs
 * information about the error, and the place of its occurrence.
 */
public class TestMyArrayList {

    /**
     * Tested ArrayList
     */
    static MyArrayList<String> myArr = new MyArrayList<>();

    /**
     * Pattern to compare the ArrayList under test
     */
    static ArrayList<String> arr = new ArrayList<>();

    /**
     * Size of the created ArrayList
     */
    static final int sizeOfArray = 1000;

    /**
     * Variable that stores the test result
     * and is responsible for terminating testing as a result of an error
     */
    static boolean testIsPassed = true;


    /**
     * Entry point to the program.
     * In this method, the collection methods are called in turn, and the result is compared with the template.
     *
     * @param args main method arguments. Not used in the program
     */
    public static void main(String[] args) {

        // Collection testing loop that can be interrupted either by the user or by an error.
        while (testIsPassed) {

            fillArray(); // Populate the test array and pattern to compare against and compare the result
            removeInArray();  // Delete random values under random index and compute result

            fillArray();
            setInArray(); // Replace values at random index and compare result

            fillArray();
            addByIndexInArray(); // Add value at random index and compare result
        }
    }

    /**
     * This method compares ArrayList and MyArrayList through the toString() method
     * true - if both methods have the same toString() method value
     * false - if methods have different toString() method values
     *
     * @return is ArrayList and MyArrayList are equals
     */
    static boolean arrayIsEquals() {
        testIsPassed = myArr.toString().equals(arr.toString()) && myArr.size() == arr.size();
        return myArr.toString().equals(arr.toString()) && myArr.size() == arr.size();
    }

    /**
     * This method prints the results of comparing ArrayList and MyArrayList to the console.
     * If ArrayList and MyArrayList are the same, prints "Test is passed"
     * If not, displays information about arrays.
     *
     * @param s Prints additional information to the console, indicating which test is currently running.
     * Basically, it should store the name of the method being checked.
     */
    static void arrayStatus(String s) {
        if (arrayIsEquals()) {
            System.out.println(s + "Test is passed");
        } else {
            System.out.print("Test for " + s + ": \n");
            System.out.println("Size: " + myArr.size() + " myArray: " + myArr);
            System.out.println("Size: " + arr.size() + " link:   " + arr);
        }
    }

    /**
     * The method is used to generate random values from zero to the value passed as an argument
     *
     * @param max maximum value for generating random numbers (does not include the maximum value itself
     * @return a random number in the requested range
     */
    static int randomNumber(int max) {
        return new Random().nextInt(max);
    }

    /**
     * This method fills ArrayList and MyArrayList with string values by the specified amount, which
     * initialized in the class field.
     * After that, ArrayList and MyArrayList are compared.
     */
    static void fillArray() {
        myArr.clear();
        arr.clear();
        for (int i = 0; i < sizeOfArray; i++) {
            myArr.add("Index " + i);
            arr.add("Index " + i);
        }
        arrayStatus("fill ArrayList : ");
    }

    /**
     * This method tests the remove() method.
     * In ArrayList and MyArrayList under a random index (in the array length range) is called
     * remove() method.
     * At the end of each iteration, an array comparison is run.
     * If the arrays are the same, the iteration continues, if not, the loop is interrupted, output to the console
     * information about the iteration and index at which there were deviations in the arrays.
     * If no deviations are found at the end of the cycle, information is displayed on the console
     * about the successful completion of the test.
     */
    static void removeInArray() {
        int randomIteration = randomNumber(myArr.size());
        for (int i = 0; i < randomIteration; i++) {
            int removeIndex = randomNumber(myArr.size());
            myArr.remove(removeIndex);
            arr.remove(removeIndex);
            if (!arrayIsEquals()) {
                arrayStatus("remove, iteration " + i + " problem with index " + removeIndex);
                break;
            }
        }
        arrayStatus("Remove, Iteration: " + randomIteration + " result: ");
    }

    /**
     * This method tests the set() method.
     * In ArrayList and MyArrayList under a random index (in the array length range) is called
     * set() method.
     * At the end of each iteration, an array comparison is run.
     * If the arrays are the same, the iteration continues, if not, the loop is interrupted, output to the console
     * information about the iteration and index at which there were deviations in the arrays.
     * If no deviations are found at the end of the cycle, information is displayed on the console
     * about the successful completion of the test.
     */
    static void setInArray() {
        int randomIteration = randomNumber(myArr.size());
        for (int i = 0; i < randomIteration; i++) {
            int setIndex = randomNumber(myArr.size());
            myArr.set(setIndex, setIndex + "");
            arr.set(setIndex, setIndex + "");
            if (!arrayIsEquals()) {
                arrayStatus("Set, iteration " + i + " problem with index " + setIndex);
                break;
            }
        }
        arrayStatus("Set, Iteration: " + randomIteration + " result: ");
    }

    /**
     * This method tests the add() method with an index.
     * In ArrayList and MyArrayList under a random index (in the array length range) is called
     * add() method.
     * At the end of each iteration, an array comparison is run.
     * If the arrays are the same, the iteration continues, if not, the loop is interrupted, output to the console
     * information about the iteration and index at which there were deviations in the arrays.
     * If no deviations are found at the end of the cycle, information is displayed on the console
     * about the successful completion of the test.
     */
    static void addByIndexInArray() {
        int randomIteration = randomNumber(myArr.size());
        for (int i = 0; i < randomIteration; i++) {
            int addIndex = randomNumber(myArr.size());
            myArr.add(addIndex, addIndex + "");
            arr.add(addIndex, addIndex + "");
            if (!arrayIsEquals()) {
                arrayStatus("Add by index, iteration " + i + " problem with index " + addIndex);
                break;
            }
        }
        arrayStatus("Add by index, Iteration: " + randomIteration + " result: ");
    }
}
