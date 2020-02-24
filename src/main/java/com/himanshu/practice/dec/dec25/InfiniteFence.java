package com.himanshu.practice.dec.dec25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 25/12/19.
 */
public class InfiniteFence {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String[] str = br.readLine().split(" ");
            int r = Integer.parseInt(str[0]);
            int b = Integer.parseInt(str[1]);
            int k = Integer.parseInt(str[2]);
            pw.append(schenerio(r, b, k) + "\n");
        }

        pw.flush();
        pw.close();

    }

    //r<=b
    private static String schenerio(int r, int b, int k) {
        if (r > b) {
            return schenerio(b, r, k);
        }

        if (r == b) {
            return "OBEY";
        }

        int gcd = gcd(r, b);

        if (gcd == 1) {
            int num = (int) Math.ceil(((double) (b - 1)) / (r));
            if (num >= k) {
                return "REBEL";
            } else {
                return "OBEY";
            }
        } else {
            int num = (int) Math.ceil(((double) (b - 1)) / (r));
            if (num >= k) {
                return "REBEL";
            } else {
                return "OBEY";
            }
        }

    }

    private static int gcd(int a, int b) {
        if (a > b) {
            return gcd(b, a);
        }

        if (a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }
}
