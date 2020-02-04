package com.himanshu.practice.feb2020.feb4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;


/**
 * @author Himanshu Bhardwaj
 * Date 04/Feb/2020
 * Statement: https://codeforces.com/contest/1285/problem/D
 * Algo: BitWise
 * Submission: https://codeforces.com/contest/1285/submission/70218605
 */
public class DrEvilUnderscores {
    public static void main(String[] args) throws IOException {
//        System.out.println((1 << 29));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int n = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");
        int[] arr = new int[str.length];
        HashSet<Integer> hashSet = new HashSet<>();


        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(str[i]);
            hashSet.add(arr[i]);
        }
        System.out.println(minXOR(30, hashSet));

    }

    static int minXOR(int bit, HashSet<Integer> number) {
        if (number.size() == 0 || bit < 0) {
            return 0;
        }

        HashSet<Integer> zero = new HashSet<>();
        HashSet<Integer> one = new HashSet<>();

        for (int x : number) {
            if ((x & (1 << bit)) == 0) {
                zero.add(x);
            } else {
                one.add(x);
            }
        }

        if (zero.size() == 0 || one.size() == 0) {
            return minXOR(bit - 1, number);
        } else {
            return (int) (Math.pow(2, bit) + Math.min(minXOR(bit + 1, zero), minXOR(bit + 1, one)));
        }



    }
}
