package com.himanshu.practice.nov.nov2;

/**
 * Created by himanshubhardwaj on 02/11/19.
 * Topcoder: https://arena.topcoder.com/#/u/practiceCode/17244/67923/15005/2/331608
 */
public class A0Paper {
    public String canBuild(int[] A) {
        for (int i = A.length - 1; i >= 0; i--) {
            A[i] += A[i + 1] / 2;
        }

        if (A[0]>0) {
            return "Possible";
        } else {
            return "Impossible";
        }
    }
}
