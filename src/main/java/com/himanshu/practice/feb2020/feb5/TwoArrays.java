package com.himanshu.practice.feb2020.feb5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedReader;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Himanshu Bhardwaj
 * Date 06/Feb/2020
 * Statement: https://codeforces.com/contest/1288/problem/C
 */
public class TwoArrays {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");

        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        TwoArraysI twoArraysI = new TwoArraysSol2();
        System.out.print(twoArraysI.solve(n, m));
    }
}


//AC: 109MS
class TwoArraysSol2 implements TwoArraysI {
    static int prime = 1000000000 + 7;

    @Override
    public long solve(int n, int m) {
        return choose(n + m + m - 1, m + m) % prime;
    }

    long choose(int n, int k) {
        long num = 1;

        for (int i = n; i > k; i--) {
            num = (num * i) % prime;
        }

        for (int i = 2; i <= (n - k); i++) {
            num = (num * pow(i, prime - 2)) % prime;
        }

        return num;
    }

    long pow(int a, int p) {
        if (p == 0) {
            return 1;
        }

        if (p == 1) {
            return a;
        }

        long exp = pow(a, p / 2) % prime;

        exp = (exp * exp) % prime;

        exp = (exp * ((p % 2 == 0) ? 1l : a)) % prime;

        return exp;
    }
}


//With Memorisation: Ac with 155MS
//Without Memorisation: TIME_LIMIT_EXCEEDED
class TwoArraysSol1 implements TwoArraysI {
    static HashMap<String, Long> dp = new HashMap<>();
    static int prime = 1000000000 + 7;

    @Override
    public long solve(int n, int m) {
        long count = 0;
        int k = Math.min(2 * m, n);
        long choose = 1;//choose(n, k);
        long arrangement = numWays(0, n, 2 * m, new int[n]);
        count = (choose * arrangement) % prime;
        return count;
    }


    //number of solution of Xi+Xi+i+..Xm-1 = n ; Xi>=0
    static long numWays(int index, int m, int n, int[] arr) {
        long count = 0;


        if (n == 0 || index == (m - 1)) {
            if (index == (m - 1)) {
                arr[index] = n;
            }
//            for (int x : arr) {
//                System.out.print(x + ",");
//            }
//            System.out.println();
            return 1;
        }

        if (n < 0 || index >= m) {
            return 0;
        }


        String key = index(arr, index, m, n);
        if (dp.containsKey(key)) {
            return dp.get(key);
        }


        for (int i = 0; i <= n; i++) {
            arr[index] = i;
            count += numWays(index + 1, m, n - i, arr);
//            for (int j = index + 1; j < arr.length; j++) {
//                arr[j] = 0;
//            }
        }

        dp.put(key, count % prime);
        return count % prime;
    }

    static String index(int[] arr, int index, int m, int n) {
        StringBuilder sb = new StringBuilder();
//        for (int x : arr) {
//            sb.append(x + ",");
//        }
        sb.append((m - index) + ",");
        sb.append(n);
        return sb.toString();
    }
}

interface TwoArraysI {
    long solve(int n, int m);
}