package com.himanshu.practice.oct.oct8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 08/10/19.
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);


        while (t-- > 0) {
            String str[] = br.readLine().split(" ");
            long x = Long.parseLong(str[0]);
            long y = Long.parseLong(str[1]);

            if ((x - y) == 1l) {
                pw.append("NO\n");
            } else {
                pw.append("YES\n");
            }
        }

        pw.flush();

    }
}
