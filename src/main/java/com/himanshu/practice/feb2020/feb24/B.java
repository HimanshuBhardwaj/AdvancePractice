package com.himanshu.practice.feb2020.feb24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Created by himanshubhardwaj on 24/02/20.
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);


        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            String[] str = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int m = Integer.parseInt(str[1]);

            int[] arr = new int[n];
            str = br.readLine().split(" ");

            for (int i = 0; i < str.length; i++) {
                arr[i] = Integer.parseInt(str[i]);
            }
            boolean[][] swap = new boolean[101][101];

            str = br.readLine().split(" ");
            int[] p = new int[str.length];

            for (int i = 0; i < str.length; i++) {
                p[i] = Integer.parseInt(str[i]) - 1;
            }

            for (int i = 0; i < str.length; i++) {

                swap[p[i]][p[i] + 1] = true;
                swap[p[i] + 1][p[i]] = true;
            }


            pw.append(isPossible(arr, swap) + "\n");
        }
        pw.flush();
        pw.close();
    }


    private static String isPossible(int[] arr, boolean[][] swap) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                if ((arr[j] < arr[j - 1]) && swap[j][j - 1]) {
                    int t = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = t;
                }
            }
        }

        int temp[] = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[i];
        }

        Arrays.sort(temp);


        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != temp[i]) {
                return "NO";
            }
        }


        return "YES";
    }
}

/*
1
3 3
3 2 1
1 2 3


1
5 2
2 1 2 3 3
1 4

*
* */