package com.himanshu.practice.jan2020.jan5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Himanshu Bhardwaj
 * Date 04/Jan/2020
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        long n = Long.parseLong(str[0]);
        long m = Long.parseLong(str[1]);

        long count = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                count = (count + factorialModM(j - i + 1, m)) % m;
            }
        }

        System.out.println(count);
    }

    static long factorialModM(long num, long m) {
        long result = 1;

        for (int i = 2; i <= num; i++) {
            result = (result * i) % m;
        }

        return result;
    }
}
