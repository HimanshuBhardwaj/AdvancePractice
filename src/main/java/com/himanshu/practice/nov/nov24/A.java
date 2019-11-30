package com.himanshu.practice.nov.nov24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    }
}


class ResultA {

    /*
     * Complete the 'countPerms' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER n as parameter.
     */

    static int modulo = 1000000007;

    public static int countPerms(int n) {
        // Write your code here
        long[][] dp = new long[n][5];
        dp[n - 1][0] = 1;
        dp[n - 1][1] = 1;
        dp[n - 1][2] = 1;
        dp[n - 1][3] = 1;
        dp[n - 1][4] = 1;
//aeiou
        for (int i = n - 2; i >= 0; i--) {
            dp[i][0] = dp[i + 1][1];
            dp[i][1] = dp[i + 1][0] + dp[i + 1][2];
            dp[i][2] = dp[i + 1][0] + dp[i + 1][1] + dp[i + 1][3] + dp[i + 1][4];
            dp[i][3] = dp[i + 1][2] + dp[i + 1][4];
            dp[i][4] = dp[i + 1][0];

            for (int j = 0; j < 5; j++) {
                dp[i][j] = dp[i][j] % modulo;
            }
        }

        return (int) ((dp[0][0] + dp[0][1] + dp[0][2] + dp[0][3] + dp[0][4]) % modulo);
    }

}