package com.himanshu.practice.feb2020.feb01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * Date 02/Feb/2020
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String str = br.readLine();
            pw.append(makeEBNE(str, n) + "\n");
        }
        pw.flush();
        pw.close();

    }

    private static String makeEBNE(String str, int n) {
        String result = "";

        for (int i = 0; i < str.length(); i++) {
            if ((str.charAt(i) - '0') % 2 == 1) {
                result = result + str.charAt(i);
            }
            if (result.length() == 2) {
                break;
            }
        }

        if (result.length() == 2) {
            return result;
        }

        return "-1";
    }
}
