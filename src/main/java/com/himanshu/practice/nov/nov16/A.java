package com.himanshu.practice.nov.nov16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 16/11/19.
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] a = new int[n];
            int[] b = new int[n];
            String[] str = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(str[i]);
            }

            str = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                b[i] = Integer.parseInt(str[i]);
            }

            pw.append(canEqual(a, b) ? "YES\n" : "NO\n");

        }
        pw.flush();
        pw.close();
    }

    private static boolean canEqual(int[] a, int[] b) {
//        if (a.length == 1) {
//            return true;
//        }


        int[] diff = new int[a.length];
        TreeSet<Integer> diffS = new TreeSet<>();

        for (int i = 0; i < a.length; i++) {
            diff[i] = b[i] - a[i];
            if (diff[i] < 0) {
                return false;
            }
            diffS.add(diff[i]);
        }

        if (diffS.size() == 0) {
            return true;
        }

        if (diffS.size() > 2) {
            return false;
        }
        if (diffS.size() == 2) {
            if (!diffS.contains(0)) {
                return false;
            }
        }

        Integer diffElement = null;
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            if (diffElement != null) {
                if (diff[i] != diffElement) {
                    if (count >= 1 && diff[i] != 0) {
                        return false;
                    } else {
                        count++;
                        diffElement = diff[i];
                    }
                }
            } else if (diff[i] != 0) {
                diffElement = diff[i];
            }

        }

        return true;

    }
}
