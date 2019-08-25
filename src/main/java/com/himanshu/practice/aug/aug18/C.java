package com.himanshu.practice.aug.aug18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 18/08/19.
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer n = Integer.parseInt(br.readLine());
        TreeSet<Integer> treeSet = new TreeSet<>();

        for (int i = 1; i <= (2 * n); i++) {
            treeSet.add(i);
        }

        int[] arr = new int[2 * n];

        for (int i = 0; i < n; i++) {
            int first = treeSet.first();
            treeSet.remove(first);
            int second = treeSet.first();
            treeSet.remove(second);

            if (i % 2 == 0) {
                arr[i] = first;
                arr[(i + n)] = second;
            } else {
                arr[i + n] = first;
                arr[i] = second;
            }
        }

        int[] validation = new int[4 * n];

        for (int i = 0; i < 2 * n; i++) {
            validation[i] = arr[i];
        }

        for (int i = 2 * n; i < 4 * n; i++) {
            validation[i] = arr[i - (2 * n)];
        }


        long sum = 0;
        TreeSet<Long> sumS = new TreeSet();


        for (int i = 0; i < n; i++) {
            sum += validation[i];
        }

        sumS.add(sum);

        for (int i = n; i < validation.length; i++) {
            sum = sum + validation[i] - validation[i - n];
            sumS.add(sum);
        }

        //System.out.println(sumS);
        if (Math.abs(sumS.first() - sumS.last()) <= 1) {
            PrintWriter pw = new PrintWriter(System.out);
            pw.append("YES\n");

            for (int i : arr) {
                pw.append(i + " ");
            }
            pw.flush();
            pw.close();
        } else {
            System.out.print("NO");
        }


    }
}
