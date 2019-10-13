package com.himanshu.practice.oct.oct11;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 12/10/19.
 * Algo: Mathematics
 * Submision: https://codeforces.com/contest/1202/submission/62385436
 * Statement: https://codeforces.com/contest/1202/problem/D
 */
public class PrintA1337String {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            long n = Long.parseLong(br.readLine());
            pw.append("1");
            long lam = lambda(n);
            int rem = (int) (n - (((lam - 1) * lam) / 2));

            pw.append("33");

            for (int i = 0; i < rem; i++) {
                pw.append("7");
            }




            for (int i = 0; i < lam-2; i++) {
                pw.append("3");
            }
            pw.append("7");
            pw.append("\n");
        }

        pw.flush();
        pw.close();
    }

    static long lambda(long n) {
        int seed = (int) (1 + Math.sqrt(1 + (8 * n))) / 2;
        int last = 1;

        for (int i = 1; i <= seed + 100; i++) {
            if (i * (i - 1l) <= 2 * n) {
                last = i;
            }
        }

        return last;
    }
}
