package com.himanshu.practice.feb2020.feb5;


/**
 * @author Himanshu Bhardwaj
 * Date 05/Feb/2020
 */
public class StringRings {
    public static void main(String[] args) {
        StringRings stringRings = new StringRings();
        System.out.println(stringRings.expectedRings(1, 2));
    }

    public double expectedRings(int A, int B) {
        return expectedRingsHelper(A, A, B);
    }

    private double expectedRingsHelper(int i, int j, int k) {
        if (i < 0 || j < 0 || k < 0 || (i + j + k == 0)) {
            return 0;
        }
        return expectedRingsHelper(i - 1, j, k) +
                expectedRingsHelper(i - 1, j - 1, k + 1) +
                expectedRingsHelper(i, j - 1, k) +
                expectedRingsHelper(i, j, k - 1) +
                (1.0 / (i + j + k));
    }
}
