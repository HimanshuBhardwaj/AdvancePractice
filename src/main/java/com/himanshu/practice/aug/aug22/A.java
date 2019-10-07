package com.himanshu.practice.aug.aug22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 22/08/19.
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String[] str = br.readLine().split(" ");
            int b = Integer.parseInt(str[0]);
            int p = Integer.parseInt(str[1]);
            int f = Integer.parseInt(str[2]);
            str = br.readLine().split(" ");
            long h = Integer.parseInt(str[0]);
            long c = Integer.parseInt(str[1]);

            long maxProfit = 0l;

            for (int i = 0; i <= b; i++) {
                maxProfit = Math.max(maxProfit, (Math.min(i / 2, p) * h) + Math.min((b - i) / 2, f) * c);
            }
            pw.append(maxProfit + "\n");
        }
        pw.flush();
    }
}
