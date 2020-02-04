package com.himanshu.practice.feb2020.feb01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * Date 02/Feb/2020
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);
        GetMinNumber getMinNumber = new BruteForce();

        while (t-- > 0) {
            String[] str = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int m = Integer.parseInt(str[1]);
            int k = Integer.parseInt(str[2]);
            str = br.readLine().split(" ");
            int[] arr = new int[str.length];

            for (int i = 0; i < arr.length; i++) {
                arr[i] = Integer.parseInt(str[i]);
            }
            pw.append(getMinNumber.solve(n, m, k, arr) + "\n");

        }
        pw.flush();
        pw.close();
    }
}


class SegmentTreeSolution implements GetMinNumber {

    @Override
    public String solve(int n, int m, int k, int[] arr) {
        if (m <= k) {
            k = m - 1;
        }

        int num = m - 1 - k;
        SegmentTree segmentTree = new SegmentTree(arr);

        for (int i = 0; i <= k; i++) {


        }


        return null;
    }
}







class SegmentTree {

    public SegmentTree(int[] arr) {

    }
}










class BruteForce implements GetMinNumber {

    @Override
    public String solve(int n, int m, int k, int[] arr) {
        int s = 0;
        int end = arr.length - 1;

        if (m <= k) {
            k = m - 1;
        }

        int num = m - 1 - k;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i <= k; i++) {
            int tMin = Integer.MAX_VALUE;
            for (int j = 0; j <= num; j++) {
                int leftPos = i + j;
                int rightPos = n - 1 - (m - 1 - i - j);
                tMin = Math.min(Math.max(arr[leftPos], arr[rightPos]), tMin);
            }
            max = Math.max(max, tMin);
        }


        return max + "";
    }
}

interface GetMinNumber {
    String solve(int n, int m, int k, int[] arr);
}


/*

1
5 4 2
1 2 3 4 4 5 3

* */