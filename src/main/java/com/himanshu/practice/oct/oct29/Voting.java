package com.himanshu.practice.oct.oct29;

import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by himanshubhardwaj on 29/10/19.
 */
public class Voting {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            ArrayList<Voter> voters = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                String[] str = br.readLine().split(" ");
                voters.add(new Voter(Long.parseLong(str[1]), Integer.parseInt(str[0])));
            }

            long dp[][] = new long[n][n + 1];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= n; j++) {
                    dp[i][j] = Long.MAX_VALUE;
                }
            }


            for (int i = 0; i < n; i++) {
                dp[i][0] = voters.get(i).price;
            }

            for (int i=0;i<=n;i++) {
            }

            for (int i = 1; i < n; i++) {
                for (int j = 0; j <= i; j++) {
                    if (i == 0) {



                    } else {
                        int pos = voters.get(i).numVotReq;
                        long minCost = Long.MAX_VALUE;

                        for (int k = pos; k <= n; k++) {
                            minCost = Math.min(minCost, dp[i - 1][k]);
                        }
                        dp[i][j] = Math.min(dp[i - 1][j - 1] + voters.get(i).price, minCost);
                    }
                }
            }


        }

    }
}

@AllArgsConstructor
class Voter {
    long price;
    int numVotReq;
}