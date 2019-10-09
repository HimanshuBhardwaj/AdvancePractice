package com.himanshu.practice.oct.oct9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 09/10/19.
 * Statement: https://codeforces.com/contest/1221/problem/D
 * Submission: https://codeforces.com/contest/1221/submission/62218775
 * Algo: DP
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
                fences[i] = new Fence(i, Long.parseLong(str[0]), Long.parseLong(str[1]));
            }
//            System.out.println(getMinimumCostToMakeGreat(fences) );
            pw.append(getMinimumCostToMakeGreat(fences) + "\n");
        }
        pw.flush();
        pw.close();
    }

    private static long getMinimumCostToMakeGreat(Fence[] fences) {
        int n = fences.length;
        long[][] dp = new long[n][3];
        //0 --> same leangth
        //1--> increase by 1
        // 2 --> increase by 2

        dp[0][1] = fences[0].price;
        dp[0][2] = fences[0].price * 2;

        for (int i = 1; i < fences.length; i++) {
            if (fences[i].size == fences[i - 1].size) {
                dp[i][0] = min(dp[i - 1][1], dp[i - 1][2]);
                dp[i][1] = min(dp[i - 1][0], dp[i - 1][2]) + fences[i].price;
                dp[i][2] = min(dp[i - 1][0], dp[i - 1][1]) + (2 * fences[i].price);
            } else if (fences[i].size == (fences[i - 1].size - 1)) {
                dp[i][0] = min(dp[i - 1][0], dp[i - 1][1], dp[i - 1][2]);
                dp[i][1] = min(dp[i - 1][1], dp[i - 1][2]) + fences[i].price;
                dp[i][2] = min(dp[i - 1][0], dp[i - 1][2]) + (2 * fences[i].price);
            } else if (fences[i].size == (fences[i - 1].size - 2)) {
                dp[i][0] = min(dp[i - 1][0], dp[i - 1][1], dp[i - 1][2]);
                dp[i][1] = min(dp[i - 1][0], dp[i - 1][1], dp[i - 1][2]) + fences[i].price;
                dp[i][2] = min(dp[i - 1][1], dp[i - 1][2]) + (2 * fences[i].price);
            } else if (fences[i].size == (fences[i - 1].size + 1)) {
                dp[i][0] = min(dp[i - 1][0], dp[i - 1][2]);
                dp[i][1] = min(dp[i - 1][0], dp[i - 1][1]) + fences[i].price;
                dp[i][2] = min(dp[i - 1][0], dp[i - 1][1], dp[i - 1][2]) + (2 * fences[i].price);
            } else if (fences[i].size == (fences[i - 1].size + 2)) {
                dp[i][0] = min(dp[i - 1][0], dp[i - 1][1]);
                dp[i][1] = min(dp[i - 1][0], dp[i - 1][1], dp[i - 1][2]) + fences[i].price;
                dp[i][2] = min(dp[i - 1][0], dp[i - 1][1], dp[i - 1][2]) + (2 * fences[i].price);
            } else {
                dp[i][0] = min(dp[i - 1][0], dp[i - 1][1], dp[i - 1][2]);
                dp[i][1] = min(dp[i - 1][0], dp[i - 1][1], dp[i - 1][2]) + fences[i].price;
                dp[i][2] = min(dp[i - 1][0], dp[i - 1][1], dp[i - 1][2]) + (2 * fences[i].price);
            }
        }

        return min(dp[n - 1][0], dp[n - 1][1], dp[n - 1][2]);
    }


    static long min(long... a) {
        long temp = a[0];

        for (int i = 1; i < a.length; i++) {
            temp = Math.min(temp, a[i]);
        }
        return temp;
    }
}


class Fence {
    int index;
    long size;
    long price;

    @java.beans.ConstructorProperties({"index", "size", "price"})
    public Fence(int index, long size, long price) {
        this.index = index;
        this.size = size;
        this.price = price;
    }

    public String toString() {
        return "Fence(index=" + this.index + ", size=" + this.size + ", price=" + this.price + ")";
    }
}

/*

1
3
2 4
2 1
3 5


* */