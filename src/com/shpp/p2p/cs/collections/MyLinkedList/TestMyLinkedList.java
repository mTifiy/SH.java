package com.shpp.p2p.cs.collections.myLinkedList;

import java.util.LinkedList;
import java.util.Random;

/**
 * This class tests MyLinkedList by comparing it to LinkedList from java library
 * Some methods of the MyLinkedList class are not tested
 * because they internally call the same methods that are subject to testing.
 * Such methods mostly belong to the MyQueue interface.
 * From the beginning, 2 class fields are created, which are collections.
 * For each collection, the methods of the class of these collections are called in turn.
 * followed by a comparison of the two collections. If the collections are the same, then the test passed.
 * The number of method calls, as well as indexes (the location of the object in the collection) are determined
 * randomly.
 * The whole process is looped, and if at some point the collections do not match, the loop stops and outputs
 * information about the error, and the place of its occurrence.
 */
public class TestMyLinkedList {

    /**
     * Tested MyLinkedList
     */
    static MyLinkedList<String> myLink = new MyLinkedList<>();

    /**
     * Pattern to compare the ArrayList under test
     */
    static LinkedList<String> link = new LinkedList<>();

    /**
     * The size of the generated MyLinkedList
     */
    static int sizeOfLink = 100;

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
            fillLink(); // fill in the test list and pattern for comparison and compare the result
            removeInLink(); // Remove random values under random index and compare result

            fillLink();
            setInLink(); // Replace values under random index and compare result

            fillLink();
            addByIndexInLink(); // Add value at random index and compare result

            addFirstInLink(); // Add a value to the beginning of the list and compare the result

            addLastInLink(); // Add a value to the end of the list and compare the result

            fillLink();
            removeLastInLink(); // Remove value from end of list and compare result

            fillLink();
            removeFirstInLink(); // Remove value from the beginning of the list and compare the result

            fillLink();
            pollFirstInLink(); // Remove and return value from head of list and compare result

            fillLink();
            pollLastInLink(); // Remove and return value from end of list and compare result

        }
    }

    /**
     * This method compares MyLinkedList and LinkedList via the toString() method
     * true - if both methods have the same toString() method value
     * false - if methods have different toString() method values
     *
     * @return is ArrayList and MyArrayList are equals
     */
    static boolean linkIsEquals() {
        testIsPassed = myLink.toString().equals(link.toString()) && myLink.size() == link.size();
        return myLink.toString().equals(link.toString()) && myLink.size() == link.size();
    }

    /**
     * This method prints the results of comparing MyLinkedList and LinkedList to the console.
     * If MyLinkedList and LinkedList are the same, prints "Test is passed"
     * If not, displays information about the lists.
     *
     * @param s Prints additional information to the console, indicating which test is currently running.
     * Basically, it should store the name of the method being checked.
     */
    static void linkStatus(String s) {
        if (linkIsEquals()) {
            System.out.println(s + "Test is passed");
        } else {
            System.out.print("Test for " + s + ": \n");
            System.out.println("Size: " + myLink.size() + " myLink: " + myLink);
            System.out.println("Size: " + link.size() + " link:   " + link);
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
     * This method fills MyLinkedList and LinkedList with string values by the specified amount, which
     * initialized in the class field.
     * After which there is a comparison of MyLinkedList and LinkedList.
     */
    static void fillLink() {
        myLink.clear();
        link.clear();
        for (int i = 0; i < sizeOfLink; i++) {
            myLink.add("Index " + i);
            link.add("Index " + i);
        }
        linkStatus("fill Linked list : ");
    }

    /**
     * This method tests the remove() method.
     * In MyLinkedList and LinkedList under a random index (in the range of the list length) is called
     * remove() method.
     * At the end of each iteration, a list comparison is run.
     * If the lists are the same, iteration continues, if not, the loop is interrupted, output to the console
     * information about the iteration and index at which there were deviations in the lists.
     * If no deviations are found at the end of the cycle, information is displayed on the console
     * about the successful completion of the test.
     */
    static void removeInLink() {
        int randomIteration = randomNumber(myLink.size());
        for (int i = 0; i < randomIteration; i++) {
            int removeIndex = randomNumber(myLink.size());
            myLink.remove(removeIndex);
            link.remove(removeIndex);
            if (!linkIsEquals()) {
                linkStatus("remove, iteration " + i + " problem with index " + removeIndex);
                break;
            }
        }
        linkStatus("Remove, Iteration: " + randomIteration + " result: ");
    }

    /**
     * This method tests the set() method.
     * In MyLinkedList and LinkedList under a random index (in the range of the list length) is called
     * set() method.
     * At the end of each iteration, a list comparison is run.
     * If the lists are the same, iteration continues, if not, the loop is interrupted, output to the console
     * information about the iteration and index at which there were deviations in the lists.
     * If no deviations are found at the end of the cycle, information is displayed on the console
     * about the successful completion of the test.
     */
    static void setInLink() {
        int randomIteration = randomNumber(myLink.size());
        for (int i = 0; i < randomIteration; i++) {
            int setIndex = randomNumber(myLink.size());
            myLink.set(setIndex, setIndex + "");
            link.set(setIndex, setIndex + "");
            if (!linkIsEquals()) {
                linkStatus("Set, iteration " + i + " problem with index " + setIndex);
                break;
            }
        }
        linkStatus("Set, Iteration: " + randomIteration + " result: ");
    }

    /**
     * This method tests the add() method with an index.
     * In MyLinkedList and LinkedList under a random index (in the range of the list length) is called
     * add() method.
     * At the end of each iteration, a list comparison is run.
     * If the lists are the same, iteration continues, if not, the loop is interrupted, output to the console
     * information about the iteration and index at which there were deviations in the lists.
     * If no deviations are found at the end of the cycle, information is displayed on the console
     * about the successful completion of the test.
     */
    static void addByIndexInLink() {
        int randomIteration = randomNumber(myLink.size());
        for (int i = 0; i < randomIteration; i++) {
            int addIndex = randomNumber(myLink.size());
            myLink.add(addIndex, addIndex + "");
            link.add(addIndex, addIndex + "");
            if (!linkIsEquals()) {
                linkStatus("Add by index, iteration " + i + " problem with index " + addIndex);
                break;
            }
        }
        linkStatus("Add by index, Iteration: " + randomIteration + " result: ");
    }

    /**
     * This method tests the addLast() method.
     * In lists a random number of times a value is added to the end of the list
     * At the end of each iteration, a list comparison is run.
     * If the lists are the same, iteration continues, if not, the loop is interrupted, output to the console
     * information about the iteration and index at which there were deviations in the lists.
     * If no deviations are found at the end of the cycle, information is displayed on the console
     * about the successful completion of the test.
     */
    static void addLastInLink() {
        myLink.clear();
        link.clear();
        int randomIteration = randomNumber(sizeOfLink);
        for (int i = 0; i < randomIteration; i++) {
            myLink.addLast(i + "");
            link.addLast(i + "");
            if (!linkIsEquals()) {
                linkStatus("AddLas, iteration " + i + " problem with index " + i);
                break;
            }
        }
        linkStatus("AddLast, Iteration: " + randomIteration + " result: ");
    }

    /**
     * This method tests the addFirst() method.
     * In the lists a random number of times the value is added to the beginning of the list
     * At the end of each iteration, a list comparison is run.
     * If the lists are the same, iteration continues, if not, the loop is interrupted, output to the console
     * information about the iteration and index at which there were deviations in the lists.
     * If no deviations are found at the end of the cycle, information is displayed on the console
     * about the successful completion of the test.
     */
    static void addFirstInLink() {
        myLink.clear();
        link.clear();
        int randomIteration = randomNumber(sizeOfLink);
        for (int i = 0; i < randomIteration; i++) {
            myLink.addFirst(i + "");
            link.addFirst(i + "");
            if (!linkIsEquals()) {
                linkStatus("AddFirst, iteration " + i + " problem with index " + i);
                break;
            }
        }
        linkStatus("AddFirst, Iteration: " + randomIteration + " result: ");
    }

    /**
     * This method tests the removeFirst() method.
     * From the list a random number of times the value from the beginning of the list is removed
     * At the end of each iteration, a list comparison is run.
     * If the lists are the same, iteration continues, if not, the loop is interrupted, output to the console
     * information about the iteration and index at which there were deviations in the lists.
     * If no deviations are found at the end of the cycle, information is displayed on the console
     * about the successful completion of the test.
     */
    static void removeFirstInLink() {
        int randomIteration = randomNumber(myLink.size());
        for (int i = 0; i < randomIteration; i++) {
            myLink.removeFirst();
            link.removeFirst();
            if (!linkIsEquals()) {
                linkStatus("remove First, iteration " + i + " problem with index " + i);
                break;
            }
        }
        linkStatus("Remove first, Iteration: " + randomIteration + " result: ");
    }

    /**
     * This method tests the removeLast() method.
     * From the list a random number of times the value from the end of the list is removed
     * At the end of each iteration, a list comparison is run.
     * If the lists are the same, iteration continues, if not, the loop is interrupted, output to the console
     * information about the iteration and index at which there were deviations in the lists.
     * If no deviations are found at the end of the cycle, information is displayed on the console
     * about the successful completion of the test.
     */
    static void removeLastInLink() {
        int randomIteration = randomNumber(myLink.size());
        for (int i = 0; i < randomIteration; i++) {
            myLink.removeLast();
            link.removeLast();
            if (!linkIsEquals()) {
                linkStatus("remove last, iteration " + i + " problem with index " + i);
                break;
            }
        }
        linkStatus("Remove last, Iteration: " + randomIteration + " result: ");
    }

    /**
     * This method tests the pollFirst() method.
     * From the list a random number of times the value from the beginning of the list is removed, returning this same value.
     * At the end of each iteration, a comparison of lists and values is started.
     * If the lists and values are the same, the iteration continues, if not, the loop is interrupted, output to the console
     * information about the iteration and index at which there were deviations in the lists.
     * If no deviations are found at the end of the cycle, information is displayed on the console
     * about the successful completion of the test.
     */
    static void pollFirstInLink() {
        int randomIteration = randomNumber(myLink.size());
        for (int i = 0; i < randomIteration; i++) {
            String one = myLink.pollFirst();
            String two = link.pollFirst();
            if (!(one.equals(two))) {
                System.out.println(one + " " + two);
                System.out.println("Pool first test is fail");
            }
            if (!linkIsEquals()) {
                linkStatus("pool firs, iteration " + i + " problem with index " + i);
                break;
            }
        }
        linkStatus("Poll first, Iteration: " + randomIteration + " result: ");
    }

    /**
     * This method tests the pollLast() method.
     * From the list a random number of times the value from the end of the list is removed, returning this same value.
     * At the end of each iteration, a comparison of lists and values is started.
     * If the lists and values are the same, the iteration continues, if not, the loop is interrupted, output to the console
     * information about the iteration and index at which there were deviations in the lists.
     * If no deviations are found at the end of the cycle, information is displayed on the console
     * about the successful completion of the test.
     */
    static void pollLastInLink() {
        int randomIteration = randomNumber(myLink.size());
        for (int i = 0; i < randomIteration; i++) {
            String one = myLink.pollLast();
            String two = link.pollLast();
            if (!(one.equals(two))) {
                System.out.println(one + " " + two);
                System.out.println("Pool Last test is fail");
            }
            if (!linkIsEquals()) {
                linkStatus("pool Last, iteration " + i + " problem with index " + i);
                break;
            }
        }
        linkStatus("Poll last, Iteration: " + randomIteration + " result: ");
    }
}



