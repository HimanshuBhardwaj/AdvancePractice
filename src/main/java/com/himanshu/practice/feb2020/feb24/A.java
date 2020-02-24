package com.himanshu.practice.feb2020.feb24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 24/02/20.
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);


        while (t-- > 0) {
            String[] str = br.readLine().split(" ");
            int a = Integer.parseInt(str[0]);
            int b = Integer.parseInt(str[1]);

            if (a == b) {
                pw.append(0 + "\n");
            } else if (a > b) {
                int x = a - b;
                if (x % 2 == 0) {
                    pw.append(1 + "\n");
                } else {
                    pw.append(2 + "\n");
                }
            } else {
                int x = b - a;
                if (x % 2 == 0) {
                    pw.append(2 + "\n");
                } else {
                    pw.append(1 + "\n");
                }
            }
        }
        pw.flush();
        pw.close();

    }
}
