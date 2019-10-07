package com.himanshu.practice.aug.aug22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 22/08/19.
 *
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String[] str = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            long a = Integer.parseInt(str[1]);
            long b = Integer.parseInt(str[2]);
            long[][] cost = new long[n][4];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < 4; j++) {
                    cost[i][j] = Long.MAX_VALUE;
                }
            }

            char[] road = br.readLine().toCharArray();

            cost[0][0] = (road[1] == '0') ? b + a : Long.MAX_VALUE;
            cost[0][2] = 2 * (b + a);

            for (int i = 1; i < n; i++) {
                if (road[i] == '0') {
                    if (cost[i - 1][2] != Long.MAX_VALUE) {
                        if ((i < n - 1) && road[i + 1] == '1') {
                            cost[i][1] = Long.MAX_VALUE;
                        } else {
                            cost[i][0] = Math.min(cost[i - 1][2] + b + (2 * a), (cost[i - 1][0] != Long.MAX_VALUE) ? (cost[i - 1][0] + b + a) : Long.MAX_VALUE);//okay
                        }

                        cost[i][2] = Math.min(cost[i - 1][2] + (2 * b) + (a), (cost[i - 1][0] != Long.MAX_VALUE) ? cost[i - 1][0] + (2 * b) + (2 * a) : Long.MAX_VALUE);
                    } else {
                        if ((i < n - 1) && road[i + 1] == '1') {
                            cost[i][1] = Long.MAX_VALUE;
                        } else {
                            cost[i][0] = (cost[i - 1][0] != Long.MAX_VALUE) ? cost[i - 1][0] + b + a : Long.MAX_VALUE;
                        }
                        cost[i][2] = (cost[i - 1][0] != Long.MAX_VALUE) ? cost[i - 1][0] + (2 * b) + (2 * a) : Long.MAX_VALUE;
                    }
                } else if (road[i] == '1') {
                    cost[i][0] = Long.MAX_VALUE;
                    if (cost[i - 1][2] != Long.MAX_VALUE) {
                        cost[i][2] = Math.min(cost[i - 1][2] + (2 * b) + (a), (cost[i - 1][0] != Long.MAX_VALUE) ? cost[i - 1][0] + (2 * b) + (2 * a) : Long.MAX_VALUE);
                    } else {
                        cost[i][2] = (cost[i - 1][0] != Long.MAX_VALUE) ? cost[i - 1][0] + (2 * b) + (2 * a) : Long.MAX_VALUE;
                    }
                }
            }
//            if (t == 1) {
//                for (int i = 0; i < n; i++) {
//                    System.out.println(cost[i][0] + "\t" + cost[i][2] + " ");
//                }
//            }
            pw.append((cost[n - 1][0] + b) + "\n");
        }

        pw.flush();
    }
}
