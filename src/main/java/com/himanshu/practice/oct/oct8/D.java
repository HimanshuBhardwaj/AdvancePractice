package com.himanshu.practice.oct.oct8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by himanshubhardwaj on 08/10/19.
 */
public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> arrayList = pal_array("AABBB");
        for (Integer x :arrayList) {
            System.out.println(x);
        }

    }

    static ArrayList<Integer> pal_array(String s) {
        int n = s.length();

        s = "@" + s + "$";

        char[] ss = s.toCharArray();

        int[] len = new int[n + 1];

        int l = 1, r = 1;
        for (int i = 1; i <= n; i++) {
            len[i] = Math.min(r - i, len[l + (r - i)]);
            while (ss[i - len[i]] == ss[i + len[i]])
                len[i]++;
            if (i + len[i] > r) {
                l = i - len[i];
                r = i + len[i];
            }
        }

        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 1; i < len.length; i++) {
            result.add(len[i]);
        }


        return result;
    }
}
