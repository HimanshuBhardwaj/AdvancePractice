package com.himanshu.practice.july.july22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by himanshubhardwaj on 25/07/19.
 */
public class AllPossibleSubsetSumsPoc {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int[] arr = new int[str.length];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(str[i]);
            System.out.println(str[i] + "\t" + arr[i]);
        }

        ArrayList<Integer> sum = possibleSums(arr);
        System.out.println(sum);
    }

    private static ArrayList<Integer> possibleSums(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        ArrayList<Integer> sums = new ArrayList<>();
        sums.add(0);

        for (int i = 0; i < arr.length; i++) {
            ArrayList<Integer> tSums = new ArrayList<>();
            tSums.addAll(sums);
            for (int x : sums) {
                if (!tSums.contains(x + arr[i])) {
                    tSums.add(x + arr[i]);
                }
            }
            sums = tSums;
        }


        Collections.sort(sums);
        return sums;
    }
}
