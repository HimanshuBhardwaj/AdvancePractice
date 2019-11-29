package com.himanshu.practice.nov.nov1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 01/11/19.
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String[] str = br.readLine().split(" ");
            int a = Integer.parseInt(str[0]);
            int b = Integer.parseInt(str[1]);
            int c = Integer.parseInt(str[2]);

            String bC = br.readLine();
            pw.append(whoWillWin(n, a, b, c, bC.toCharArray()) + "\n");
        }
        pw.flush();
        pw.close();
    }

    private static String whoWillWin(int n, int a, int b, int c, char[] bC) {
        char mc[] = new char[bC.length];
        int count = 0;


        for (int i = 0; i < bC.length; i++) {
            char chance = bC[i];
            if (chance == 'R') {
                if (b > 0) {
                    b--;
                    count++;
                    mc[i] = 'P';
                }
            } else if (chance == 'P') {
                if (c > 0) {
                    c--;
                    count++;
                    mc[i] = 'S';
                }
            } else {
                if (a > 0) {
                    a--;
                    count++;
                    mc[i] = 'R';
                }
            }
        }

        if (count >= ((bC.length / 2) + (bC.length % 2))) {
            StringBuffer sb = new StringBuffer();
            sb.append("YES\n");

            for (int i = 0; i < mc.length; i++) {
                if (mc[i] == 'R' || mc[i] == 'P' || mc[i] == 'S') {
                } else {
                    if (a > 0) {
                        mc[i] = 'R';
                        a--;
                    } else if (b > 0) {
                        mc[i] = 'P';
                        b--;
                    } else {
                        mc[i] = 'S';
                        c--;
                    }
                }
                sb.append(mc[i]);
            }
            return sb.toString();
        } else {
            return "NO";
        }
    }
}
