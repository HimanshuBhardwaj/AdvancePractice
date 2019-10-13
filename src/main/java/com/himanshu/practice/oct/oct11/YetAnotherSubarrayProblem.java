package com.himanshu.practice.oct.oct11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 12/10/19.
 */
public class YetAnotherSubarrayProblem {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        int k = Integer.parseInt(str[2]);

        str = br.readLine().split(" ");
        long[] arr = new long[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(str[i]);
        }

        System.out.println(new BruteForce().getmaxSubarray(m, k, arr));


    }
}


interface Solution {
    long getmaxSubarray(int m, long k, long[] arr);
}

class BruteForce implements Solution {

    @Override
    public long getmaxSubarray(int m, long k, long[] arr) {
        long max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                long result = 0;

                for (int kk = i; kk <= j; kk++) {
                    result += arr[kk];
                }
                result -= (int) (Math.ceil(((double) (j - i + 1d)) / m) * k);
                max = Math.max(result, max);
            }
        }
        return max;
    }
}