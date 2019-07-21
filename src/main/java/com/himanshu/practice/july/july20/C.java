package com.himanshu.practice.july.july20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 20/07/19.
 */
public class C {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int w = Integer.parseInt(str[0]);
        int h = Integer.parseInt(str[1]);

        long dp[][][] = new long[h][w][4];
        boolean visited[][][] = new boolean[h][w][4];

        for (int i = 0; i < 4; i++) {
            dp[0][0][i] = 1;
            visited[0][0][i] = true;
        }

        countState(h, w, 0, dp, visited);

        long result = (dp[h - 1][w - 1][0] + dp[h - 1][w - 1][1] + dp[h - 1][w - 1][2] + dp[h - 1][w - 1][3]) % 998244353l;

        System.out.println(result);


    }

    private static void countState(int h, int w, int tile, long[][][] dp, boolean[][][] visited) {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                int sum = 0;
                for (int k = 0; k < 4; k++) {
                    if (!visited[i][j][k]) {
                        // System.out.println(i+","+j+","+k);
                        visited[i][j][k] = true;
                        switch (k) {
                            case 0: //dp[i][j][k]+= (((i>0)? (dp[i-1][j][2] + dp[i-1][j][0]):0) + ((j>0)?dp[i][j-1][1] + dp[i][j-1][0]:0)) ;
                                dp[i][j][k] += Math.min(((i > 0) ? (dp[i - 1][j][2] + dp[i - 1][j][0]) : Long.MAX_VALUE), ((j > 0) ? dp[i][j - 1][1] + dp[i][j - 1][0] : Long.MAX_VALUE));
                                break;
                            case 1: //dp[i][j][k]+= (((i>0)? (dp[i-1][j][3]+dp[i-1][j][1]):0) + ((j>0)?dp[i][j-1][0]+dp[i][j-1][1]:0)) ;
                                dp[i][j][k] += Math.min(((i > 0) ? (dp[i - 1][j][3] + dp[i - 1][j][1]) : Long.MAX_VALUE), ((j > 0) ? dp[i][j - 1][0] + dp[i][j - 1][1] : Long.MAX_VALUE));
                                break;
                            case 2: //dp[i][j][k]+= (((i>0)? (dp[i-1][j][0]+dp[i-1][j][2]):0) + ((j>0)?dp[i][j-1][3]+dp[i][j-1][2]:0)) ;
                                dp[i][j][k] += Math.min(((i > 0) ? (dp[i - 1][j][0] + dp[i - 1][j][2]) : Long.MAX_VALUE), ((j > 0) ? dp[i][j - 1][3] + dp[i][j - 1][2] : Long.MAX_VALUE));
                                break;
                            case 3:
                                dp[i][j][k] += Math.min(((i > 0) ? (dp[i - 1][j][1] + dp[i - 1][j][3]) : Long.MAX_VALUE), ((j > 0) ? dp[i][j - 1][2] + dp[i][j - 1][3] : Long.MAX_VALUE));
                                break;
                        }
                    }
                    dp[i][j][k] = dp[i][j][k] % 998244353l;
                    sum += dp[i][j][k];
                }
                //System.out.print(sum + "\t");
            }
            //System.out.println();
        }
    }
}


class State implements Comparable<State> {
    int i;
    int j;
    int pos;

    @Override
    public int compareTo(State o) {
        return (this.i + "," + this.j + "," + this.pos).compareTo(o.i + "," + o.j + "," + o.pos);
    }
}
