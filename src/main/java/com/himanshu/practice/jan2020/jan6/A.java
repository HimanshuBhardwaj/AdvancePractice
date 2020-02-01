package com.himanshu.practice.jan2020.jan6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * Date 05/Jan/2020
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            char[] ch = br.readLine().toCharArray();
            pw.append(minimumTime(ch) + "\n");
        }
        pw.flush();
        pw.close();
    }

    private static int minimumTime(char[] ch) {
        int pos = 0;

        while (pos < ch.length && ch[pos] == 'P') {
            pos++;
        }

        int count = 0;
        int max = 0;
        for (int i = pos; i < ch.length; i++) {
            if (ch[i] == 'A') {
                count = 0;
            } else {
                count++;
                max = Math.max(count, max);
            }
        }
        return max;
    }
}
