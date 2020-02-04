package com.himanshu.practice.feb2020.feb01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * Date 02/Feb/2020
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String str[] = br.readLine().split(" ");
            int[] arr = new int[str.length];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = Integer.parseInt(str[i]);

            }

            pw.append(isPossible(arr) + "\n");


        }
        pw.flush();
        pw.close();
    }

    private static String isPossible(int[] arr) {
        boolean[] prefix = new boolean[arr.length];
        boolean[] suffix = new boolean[arr.length];

        for (int i = 0; i < arr.length; i++) {
            prefix[i] = suffix[i] = true;
        }

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] >= i) {
                prefix[i] = prefix[i - 1];
            } else {
                prefix[i] = false;
            }

            if (arr[arr.length - 1 - i] >= i) {
                suffix[arr.length - 1 - i] = suffix[arr.length - i];
            } else {
                suffix[arr.length - 1 - i] = false;
            }
        }

        for (int i=0;i<arr.length;i++) {
            if (suffix[i]&&prefix[i]) {
                return "Yes";
            }
        }


        return "No";


    }

}


/*

1
5
2 2 1 2 0

1
5
0 2 2 1 1

* */