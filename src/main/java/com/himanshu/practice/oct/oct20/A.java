package com.himanshu.practice.oct.oct20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 20/10/19.
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);


        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int eN = 0;
            int oN = 0;
            String[] str = br.readLine().split(" ");

            for (int i = 0; i < n; i++) {
                int num = Integer.parseInt(str[i]);
                if (num % 2 == 0) {
                    eN++;
                } else {
                    oN++;
                }
            }

            int m = Integer.parseInt(br.readLine());
            int eM = 0;
            int oM = 0;
            str = br.readLine().split(" ");

            for (int i = 0; i < m; i++) {
                int num = Integer.parseInt(str[i]);
                if (num % 2 == 0) {
                    eM++;
                } else {
                    oM++;
                }
            }
            pw.append(((eN * (long)eM) + (oN * (long)oM)) + "\n");
        }

        pw.flush();
        pw.close();

    }
}
