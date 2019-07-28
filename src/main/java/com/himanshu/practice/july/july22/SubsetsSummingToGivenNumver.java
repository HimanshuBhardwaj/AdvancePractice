package com.himanshu.practice.july.july22;

import java.util.LinkedList;

/**
 * Created by himanshubhardwaj on 27/07/19.
 */
public class SubsetsSummingToGivenNumver {
    public static void main(String[] args) {
        printSubsert(new LinkedList<Integer>(),4);

    }

    static void printSubsert(LinkedList<Integer> numbers, int number) {
        if (number < 0) {
            return;
        }
        if (number == 0) {
            System.out.println(numbers);
            return;
        }

        for (int i = 1; i < 10; i++) {
            numbers.addLast(i);
            printSubsert(numbers, number - i);
            numbers.removeLast();
        }

    }
}
