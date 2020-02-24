package com.himanshu.practice.feb2020.feb21;

/**
 * Created by himanshubhardwaj on 21/02/20.
 */
public class ArraySorting {
    public static void main(String[] args) {
        System.out.println("Hello ");
    }

    int[] arraySort(int[] A) {
        int dp[][] = new int[A.length][2001];
        //dp[i][j] = min cost to produce array such that ith element is j


        for (int i = 2000; i >= 0; i--) {
            dp[A.length - 1][i] = (A[A.length - 1] != i) ? 1 : 0;

        }

        for (int i = A.length - 2; i >= 0; i--) {
            for (int j = 2000; j > 0; j--) {

            }
        }
        return null;
    }
}
