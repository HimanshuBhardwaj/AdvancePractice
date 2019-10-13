package com.himanshu.practice.oct.oct13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 13/10/19.
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String str = br.readLine();

            if (!str.contains("1")) {
                pw.append(n + "\n");
            } else {
                int f = str.lastIndexOf("1") + 1;
                int last = n - str.indexOf("1");
                int answer = Math.max(2 * f, 2 * last);
                pw.append(answer + "\n");
            }
        }
        pw.flush();
        pw.close();

    }
}
