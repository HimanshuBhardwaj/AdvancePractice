package com.himanshu.practice.july.july22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by himanshubhardwaj on 22/07/19.
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int k = Integer.parseInt(str[1]);
        int gap = n - k + 1;
        str = br.readLine().split(" ");
        long[] arr = new long[n];
        long diff = Long.MAX_VALUE;


        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(str[i]);
        }

        for (int i = 0; i + gap - 1 < n; i++) {
            diff = Math.min(diff, arr[i + gap - 1] - arr[i]);
        }


        if (diff== Long.MAX_VALUE) {
            throw new RuntimeException(" Wrong");
        }
        System.out.println(diff);
    }
}
/*
4 3
1 2 3 3
* */


/*
5 3
2 4 5 5 5
*
* */