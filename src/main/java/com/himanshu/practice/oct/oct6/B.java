package com.himanshu.practice.oct.oct6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 06/10/19.
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int q = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (q-- > 0) {
            int[] a = charFewq(br.readLine());
            int[] b = charFewq(br.readLine());

            String result = "NO\n";
            for (int i = 0; i < a.length; i++) {
                if (a[i] > 0 && b[i] > 0) {
                    result = "YES\n";
                }
            }
            pw.append(result);
        }
        pw.flush();
        pw.close();
    }


    static int[] charFewq(String s) {
        int[] arr = new int[26];

        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
        }
        return arr;
    }
}
