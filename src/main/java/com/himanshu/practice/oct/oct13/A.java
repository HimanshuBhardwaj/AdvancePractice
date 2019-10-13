package com.himanshu.practice.oct.oct13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 13/10/19.
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
            int c = Integer.parseInt(str[2]);
            int d = Integer.parseInt(str[3]);
            int k = Integer.parseInt(str[4]);

            int req = (a / c) + ((a % c == 0) ? 0 : 1) + (b / d) + ((b % d == 0) ? 0 : 1);

            if (req <= k) {
                pw.append(((a / c) + (((a % c == 0) ? 0 : 1))) + " ");
                pw.append(((b / d) + ((b % d == 0) ? 0 : 1))+ "\n");
            } else {
                pw.append("-1\n");
            }
        }
        pw.flush();
        pw.close();


    }
}
