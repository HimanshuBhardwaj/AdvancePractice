package com.himanshu.practice.oct.oct29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 30/10/19.
 * Algo: Probablity, Expected Value
 * Statement: https://codeforces.com/contest/453/problem/A
 * Submission: https://codeforces.com/contest/453/submission/63862703
 */
public class LittlePonyAndExpectedMaximum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[1]);
        int m = Integer.parseInt(str[0]);

        double[] value = new double[m + 1]; //(i/m)^n

        for (int i = 1; i <= m; i++) {
            value[i] = Math.pow((i / (double) m), n);
        }

        double[] probablity = new double[m + 1];


        for (int i = 1; i <= m; i++) {
            probablity[i] = value[i] - value[i - 1];
        }

        double expectedV = 0;

        for (int i = 1; i <= m; i++) {
            expectedV += i * probablity[i];
            //expectedV += i * ((double) (Math.pow(i, n) - Math.pow(i - 1, n)) / Math.pow(m, n));
        }

        System.out.println(expectedV);


    }
}


