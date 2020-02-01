package com.himanshu.practice.jan2020.jan25;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * Date 28/Jan/2020
 * Statement:https://codeforces.com/contest/1217/problem/C
 * Algo: Ad-Hoc
 * Submission: https://codeforces.com/contest/1217/submission/69961073
 */
public class TheNumberOfGoodSubstrings {
    static long range = 2000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String str = br.readLine();
            pw.append(getNumber(str) + "\n");
        }
        pw.flush();
        pw.close();


    }

    private static long getNumber(String str) {
        int last1[] = new int[str.length()];
        int prev1 = (str.charAt(0) == '1') ? 0 : -1;
        last1[0] = -1;

        for (int i = 1; i < last1.length; i++) {
            last1[i] = prev1;
            prev1 = (str.charAt(i) == '1') ? i : prev1;
//            if (str.charAt(i) == '1') {
//                System.out.print(i + ",");
//            }
        }
//        System.out.println();
//
//        for (int i = 0; i < last1.length; i++) {
//            System.out.println(i + "\t" + last1[i]);
//        }


        long count = 0;
        for (int i = 0; i < str.length(); i++) {
            long num = 0;
            for (int j = i; (j >= 0) && (j >= i - 28); j--) {
                num += (str.charAt(j) - '0') * Math.pow(2, i - j);
                if (num == (i - j + 1)) {
                    count++;
                }
            }

            if ((i - 28) > 0) {
                int p = i - 28;
                if (((i - last1[p]) >= num) && (num > (29))) {
                    count++;
                }
            }
        }


        return count;
    }
}
/*
1
00000000010000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010000000000
* */