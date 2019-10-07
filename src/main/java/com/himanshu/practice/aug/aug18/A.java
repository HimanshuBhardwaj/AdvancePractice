package com.himanshu.practice.aug.aug18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

/**
 * Created by himanshubhardwaj on 18/08/19.
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        HashSet<Integer> setA = new HashSet<>();
        String[] str = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(str[i]);
            setA.add(num);
            a[i] = num;
        }

        int m = Integer.parseInt(br.readLine());
        int[] b = new int[m];

        HashSet<Integer> setB = new HashSet<>();
        str = br.readLine().split(" ");

        for (int i = 0; i < m; i++) {
            int num = Integer.parseInt(str[i]);
            setB.add(num);
            b[i] = num;
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int sum = a[i] + b[j];
                if (setA.contains(sum) || setB.contains(sum)) {
                    continue;
                } else {
                    System.out.print(a[i] + " " + b[j]);
                    return;
                }
            }
        }


    }
}
