package com.himanshu.practice.oct.oct24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 24/10/19.
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String s = br.readLine();
            pw.append(workingButtons(s) + "\n");
        }
        pw.flush();
        pw.close();
    }

    private static String workingButtons(String str) {
        if (str.length() == 1) {
            return str;
        }
        TreeSet<Character> hashSet = new TreeSet<>();

        int pos = 0;

        while (pos < (str.length())) {
            if (pos == (str.length() - 1)) {
                hashSet.add(str.charAt(pos));
                break;
            }

            if (str.charAt(pos) == str.charAt(pos + 1)) {
                pos = pos + 2;
            } else {
                hashSet.add(str.charAt(pos));
                pos++;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (Character c : hashSet) {
            sb.append(c);
        }


        return sb.toString();
    }
}
