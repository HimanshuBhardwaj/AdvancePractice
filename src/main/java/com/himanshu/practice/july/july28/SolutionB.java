package com.himanshu.practice.july.july28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;

/**
 * Created by himanshubhardwaj on 28/07/19.
 */
public class SolutionB {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        String str[];
        PrintWriter printWriter = new PrintWriter(System.out);

        for (int ca = 1; ca <= t; ca++) {
            str = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int g = Integer.parseInt(str[1]);
            int m = Integer.parseInt(str[2]);

            boolean[] direction = new boolean[g];//true --> Counter Clock wise
            int[] startPoint = new int[g];
            HashSet<Integer>[] consulateGuest = new HashSet[n];
            int[] consulateTime = new int[(int) n];


            for (int i = 0; i < consulateGuest.length; i++) {
                consulateTime[i] = -1;
                consulateGuest[i] = new HashSet<>();
            }


            for (int i = 0; i < g; i++) {
                str = br.readLine().split(" ");
                startPoint[i] = Integer.parseInt(str[0]) - 1;
                direction[i] = (str[1].equals("ArraySorting")) ? true : false;
            }


            for (int i = 0; i < g; i++) {
                int start = startPoint[i];
                for (int j = 0; j <= m; j++) {
                    if (j > consulateTime[start]) {
                        consulateTime[start] = j;
                        consulateGuest[start] = new HashSet<>();
                        consulateGuest[start].add(i);
                    } else if (j == consulateTime[start]) {
                        consulateGuest[start].add(i);
                    }
                    start = (direction[i]) ? ((start + 1) % n) : ((n + start - 1) % n);
                }
            }

            int countG[] = new int[g];

            for (int i = 0; i < n; i++) {
                for (int c : consulateGuest[i]) {
                    countG[c]++;
                }
            }

            printWriter.append("Case #" + ca + ": ");
            for (int i = 0; i < g; i++) {
                printWriter.append(countG[i] + " ");

            }
            printWriter.append("\n");
        }
        printWriter.flush();
    }
}
