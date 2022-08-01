package com.shpp.p2p.cs.collections.MyLinkedList;

import com.shpp.p2p.cs.collections.MyArrayList.MyArrayList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class TestMyLinkedList {

    static MyArrayList<String> myArr = new MyArrayList<>();
    static ArrayList<String> arr = new ArrayList<>();

    static MyLinkedList<String> myLink = new MyLinkedList<>();
    static LinkedList<String> link = new LinkedList<>();

    static int sizeOfLink = 1000;

    static boolean testIsPassed = true;

    public static void main(String[] args) {
        while (testIsPassed) {
            fillLink();
            removeInLink();

            fillLink();
            setInLink();

            fillLink();
            addByIndexInLink();

            addFirstInLink();

            addLastInLink();

            fillLink();
            removeLastInLink();

            fillLink();
            removeFirstInLink();

            fillLink();
            pollFirstInLink();

            fillLink();
            pollLastInLink();

        }
        System.out.println(testIsPassed);
    }

    static boolean linkIsEquals() {
        testIsPassed = myLink.toString().equals(link.toString()) && myLink.size() == link.size();
        return myLink.toString().equals(link.toString()) && myLink.size() == link.size();
    }

    static void linkStatus(String s) {
        if (linkIsEquals()) {
            System.out.println(s + "Test is passed");
        } else {
            System.out.print("Test for " + s + ": \n");
            System.out.println("Size: " + myLink.size() + " myLink: " + myLink);
            System.out.println("Size: " + link.size() + " link:   " + link);
        }
    }

    static int randomNumber(int min, int max) {
        return new Random().nextInt(max - min);
    }

    static void fillLink() {
        myLink.clear();
        link.clear();
        for (int i = 0; i < sizeOfLink; i++) {
            myLink.add("Index " + i);
            link.add("Index " + i);
        }
        linkStatus("fill Linked list : ");
    }

    static void removeInLink() {
        int randomIteration = randomNumber(0, myLink.size());
        for (int i = 0; i < randomIteration; i++) {
            int removeIndex = randomNumber(0, myLink.size());
            myLink.remove(removeIndex);
            link.remove(removeIndex);
            if (!linkIsEquals()) {
                linkStatus("remove, iteration " + i + " problem with index " + removeIndex);
                break;
            }
        }
        linkStatus("Remove, Iteration: " + randomIteration + " result: ");
    }

    static void setInLink() {
        int randomIteration = randomNumber(0, myLink.size());
        for (int i = 0; i < randomIteration; i++) {
            int setIndex = randomNumber(0, myLink.size());
            myLink.set(setIndex, setIndex + "");
            link.set(setIndex, setIndex + "");
            if (!linkIsEquals()) {
                linkStatus("Set, iteration " + i + " problem with index " + setIndex);
                break;
            }
        }
        linkStatus("Set, Iteration: " + randomIteration + " result: ");
    }

    static void addByIndexInLink() {
        int randomIteration = randomNumber(0, myLink.size());
        for (int i = 0; i < randomIteration; i++) {
            int addIndex = randomNumber(0, myLink.size());
            myLink.add(addIndex, addIndex + "");
            link.add(addIndex, addIndex + "");
            if (!linkIsEquals()) {
                linkStatus("Add by index, iteration " + i + " problem with index " + addIndex);
                break;
            }
        }
        linkStatus("Add by index, Iteration: " + randomIteration + " result: ");
    }

    static void addLastInLink() {
        myLink.clear();
        link.clear();
        int randomIteration = randomNumber(0, sizeOfLink);
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

    static void addFirstInLink() {
        myLink.clear();
        link.clear();
        int randomIteration = randomNumber(0, sizeOfLink);
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

    static void removeFirstInLink() {
        int randomIteration = randomNumber(0, myLink.size());
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

    static void removeLastInLink() {
        int randomIteration = randomNumber(0, myLink.size());
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

    static void pollFirstInLink() {
        int randomIteration = randomNumber(0, myLink.size());
        for (int i = 0; i < randomIteration; i++) {
            String one = myLink.pollFirst();
            String two = link.pollFirst();
            if (!(one.equals(two))){
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

    static void pollLastInLink() {
        int randomIteration = randomNumber(0, myLink.size());
        for (int i = 0; i < randomIteration; i++) {
            String one = myLink.pollLast();
            String two = link.pollLast();
            if (!(one.equals(two))){
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



