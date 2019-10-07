package com.himanshu.practice.oct.oct6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 06/10/19.
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int q = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (q-- > 0) {
            int n = Integer.parseInt(br.readLine());
            if (n == 2) {
                pw.append(2 + "\n");
            } else {
                if (n % 2 == 0) {
                    pw.append(0 + "\n");
                } else {
                    pw.append(1 + "\n");
                }
            }
        }

        pw.flush();
        pw.close();
    }
}
