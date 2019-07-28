package com.himanshu.practice.july.july22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 27/07/19.
 */
public class PrintDivision {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        long a = Long.parseLong(str[0]);
        long b = Long.parseLong(str[1]);
        long n = Long.parseLong(str[2]);
        PrintWriter pw = new PrintWriter(System.out);
        pw.append(a / b + ".");
        a = a % b;

        generateFraction(a, b, n, pw);
        pw.flush();


    }

    private static void generateFraction(long a, long b, long n, PrintWriter pw) {
        for (int i = 0; i < n; i++) {
            a = a * 10;
            pw.append((a / b) + "");
            a = a % b;
        }
    }
}
