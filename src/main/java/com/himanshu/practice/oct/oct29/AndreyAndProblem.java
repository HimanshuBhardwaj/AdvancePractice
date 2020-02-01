package com.himanshu.practice.oct.oct29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by himanshubhardwaj on 30/10/19.
 */
public class AndreyAndProblem {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Double[] p = new Double[n];
        String[] str = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            p[i] = Double.parseDouble(str[i]);
        }

        Arrays.parallelSort(p, Collections.<Double>reverseOrder());

        if (Double.compare(p[0], 1) == 0) {
            System.out.println(1);
            return;
        }

        double A = 0;
        double B = 1;

        int pos = 0;

        while (pos < n && A < 1) {
            double nA = A + (p[pos] / (1 - p[pos]));
            double nB = B * (1 - p[pos]);
            if (Double.compare(nA, 1) == -1) {
                pos++;
                A = nA;
                B = nB;
            } else {
                pos++;
                break;
            }
        }

//        System.out.println(pos + "\t" + A + "\t\t" + A * BinaryHeapLeaf);


        A = 0;
        B = 1;

        for (int i = 0; i < pos; i++) {
            A += (p[i] / (1 - p[i]));
            B *= (1 - p[i]);
        }
        System.out.print(A * B);


    }
}
