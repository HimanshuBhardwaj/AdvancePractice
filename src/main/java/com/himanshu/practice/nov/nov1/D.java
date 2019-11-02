package com.himanshu.practice.nov.nov1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 01/11/19.
 */
public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);


        while (t-- > 0) {
            String[] str = br.readLine().split(" ");
            int a = Integer.parseInt(str[0]);
            int b = Integer.parseInt(str[1]);

            if (gcd(a, b) == 1) {
                pw.append("Finite\n");
            } else {
                pw.append("Infinite\n");
            }

        }
        pw.flush();
        pw.close();
    }

    static int gcd(int a, int b) {
        if (a < b) {
            return gcd(b, a);
        }
        if (b == 0) {
            return a;
        }
        if (b == 1) {
            return 1;
        }

        return gcd(a % b, b);
    }
}
