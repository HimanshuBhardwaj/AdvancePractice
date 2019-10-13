package com.himanshu.practice.oct.oct13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by himanshubhardwaj on 13/10/19.
 */
public class Line {
    static long LOWER_BOUND = -2000000000l;
    static long UPPER_BOUND = 2000000000l;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int a = Integer.parseInt(str[0]);
        int b = Integer.parseInt(str[1]);
        int c = Integer.parseInt(str[2]) * -1;
        int gcd = gcd(Math.abs(a), Math.abs(b));

        if (c % gcd != 0) {
            System.out.print(-1);
            return;
        }


        a = a / gcd;
        b = b / gcd;
        c = c / gcd;

        br.close();
        //bruteforce(a, b, c);
        //ax+by=c
        smart(a, b, c);


    }

    //ax+by=c
    private static void smart(int a, int b, int c) {
        long x;
        for (int i = 0; i <= UPPER_BOUND; i = i + c) {
            x = i;
            if ((c - (a * x)) % b == 0) {
                long y = (c - (a * x)) / b;
                if (LOWER_BOUND <= x && x <= UPPER_BOUND && LOWER_BOUND <= y && y <= UPPER_BOUND) {
                    System.out.println(x + " " + y);
                    return;
                }
            }

            x = -1 * i;

            if ((c - (a * x)) % b == 0) {
                long y = (c - (a * x)) / b;
                if (LOWER_BOUND <= x && x <= UPPER_BOUND && LOWER_BOUND <= y && y <= UPPER_BOUND) {
                    System.out.println(x + " " + y);
                    return;
                }
            }

        }
        System.out.println(-1);

    }


    //Wrong Answer
    static void bruteforce(int a, int b, int c) {
        ArrayList<String> solution = new ArrayList<>();
        for (int i = 1; i <= 100000000; i++) {
            if ((c - (a * i)) % b == 0) {
                long y = (c - (a * i)) / b;
                if (LOWER_BOUND <= y && y <= UPPER_BOUND && LOWER_BOUND <= i && i <= UPPER_BOUND) {
                    System.out.println(i + " " + y);
                    return;
                }
            }

            if ((c - (b * i)) % a == 0) {
                long x = (c - (b * i)) / a;
                if (LOWER_BOUND <= x && x <= UPPER_BOUND && LOWER_BOUND <= i && i <= UPPER_BOUND) {
                    System.out.println(x + " " + i);
                    return;
                }
            }
        }
    }

    static int gcd(int a, int b) {
        if (a == 1 || b == 1) {
            return 1;
        }

        if (a == 0) {
            return b;
        }

        if (b == 0) {
            return a;
        }
        if (a < b) {
            return gcd(b, a);
        }

        return gcd(a % b, b);
    }

}
