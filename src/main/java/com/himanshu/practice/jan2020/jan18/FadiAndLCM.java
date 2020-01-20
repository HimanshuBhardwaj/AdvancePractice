package com.himanshu.practice.jan2020.jan18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Himanshu Bhardwaj
 * Date 18/Jan/2020
 */
public class FadiAndLCM {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        long sqrt = (long) (Math.sqrt(n) + 1);

        long max = Long.MAX_VALUE;
        long ra = -1;
        long rb = -1;

        for (int i = 1; i <= sqrt; i++) {
            if (n % i == 0) {
                long a = i;
                long b = n / i;

                long d = gcd(a, b);

                if (d == 1) {
                    if (max > Math.max(a, b)) {
                        max = Math.max(a, b);
                        ra = a;
                        rb = b;
                    }
                }
//                else {
//                    a = a / d;
//                    if (max > Math.max(a, b)) {
//                        max = Math.max(a, b);
//                        ra = a;
//                        rb = b;
//                    }
//                    a = a * d;
//                    b = b / d;
//
//                    if (max > Math.max(a, b)) {
//                        max = Math.max(a, b);
//                        ra = a;
//                        rb = b;
//                    }
//                }
            }
        }

        System.out.println(ra + " " + rb);


    }

    private static long gcd(long a, long b) {
        if (a > b) {
            return gcd(b, a);
        }

        if (a == 0) {
            return b;
        }

        if (a == 1) {
            return 1;
        }


        return gcd(b % a, a);
    }
}


class Solution1 {

}
