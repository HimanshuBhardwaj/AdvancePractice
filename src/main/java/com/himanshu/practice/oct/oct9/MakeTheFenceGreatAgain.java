package com.himanshu.practice.oct.oct9;

import javafx.scene.chart.BubbleChart;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 09/10/19.
 */
public class MakeTheFenceGreatAgain {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int q = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (q-- > 0) {
            int n = Integer.parseInt(br.readLine());
            Fence[] fences = new Fence[n];
            for (int i = 0; i < n; i++) {
                String[] str = br.readLine().split(" ");
                fences[i] = new Fence(i, Long.parseLong(str[1]), Long.parseLong(str[2]));
            }
            pw.append(getMinimumCostToMakeGreat(fences) + "\n");
        }
        pw.flush();
        pw.close();
    }

    private static long getMinimumCostToMakeGreat(Fence[] fences) {
        long[][] dp = new long[fences.length][3];
        //0 --> same leangth
        //1--> increase by 1
        // 2 --> increase by 2

        dp[0][1] = fences[0].price;
        dp[0][2] = fences[0].price * 2;

        for (int i = 1; i < fences.length; i++) {
            if (fences[i].size == fences[i - 1].size) {
                dp[i][0] = min(dp[i - 1][1], dp[i - 1][2], fences[i].price + dp[i - 1][0], fences[i].price + dp[i - 1][2], 2 * fences[i].price + dp[i - 1][0], fences[i].price * 2 + dp[i - 1][1]);
                dp[i][1] = min(dp[i - 1][1], dp[i - 1][2], fences[i].price + dp[i - 1][0], fences[i].price + dp[i - 1][2], 2 * fences[i].price + dp[i - 1][0], fences[i].price * 2 + dp[i - 1][1]);
                dp[i][2] = min(dp[i - 1][1], dp[i - 1][2], fences[i].price + dp[i - 1][0], fences[i].price + dp[i - 1][2], 2 * fences[i].price + dp[i - 1][0], fences[i].price * 2 + dp[i - 1][1]);
            } else if (fences[i].size == fences[i - 1].size - 1) {
                dp[i][0] = min(dp[i - 1][0], dp[i - 1][1], dp[i - 1][2]);
                dp[i][1] = min(dp[i - 1][1], dp[i - 1][2]) + fences[i].price;
                dp[i][2] = min(dp[i - 1][0], dp[i - 1][2]) + (2 * fences[i].price);
            } else if (fences[i].size == fences[i - 1].size - 2) {
                dp[i][0] = min(dp[i - 1][0], dp[i - 1][1], dp[i - 1][2]);
                dp[i][1] = min(dp[i - 1][0], dp[i - 1][1], dp[i - 1][2]) + fences[i].price;
                dp[i][2] = min(dp[i - 1][1], dp[i - 1][2]) + (2 * fences[i].price);
            } else {

            }
        }


        return 0;
    }

    static long min(long... a) {
        return 0;
    }
}


@AllArgsConstructor
class Fence {
    int index;
    long size;
    long price;
}

