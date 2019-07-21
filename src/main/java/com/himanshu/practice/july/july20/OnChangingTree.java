package com.himanshu.practice.july.july20;

import java.io.*;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Himanshu Bhardwaj on 17/07/18.
 */
public class OnChangingTree {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int k = Integer.parseInt(str[1]);
        int denominations[] = new int[n];
        str = br.readLine().split(" ");
        for (int i = 0; i < str.length; i++) {
            denominations[i] = Integer.parseInt(str[i]);
        }
        int q = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);
        for (int i = 0; i < q; i++) {
            int amount = Integer.parseInt(br.readLine());
            pw.append(getAmountBruteForce(amount, denominations, k) + "\n");
        }
        pw.flush();
    }

    private static int getAmountBruteForce(int amount, int[] denominations, int maxNum) {
        int minNumber = Integer.MAX_VALUE;
        for (int i = denominations.length - 1; i >= 0; i--) {
            if (amount % denominations[i] == 0) {
                if ((amount / denominations[i]) <= maxNum) { //System.out.println("(" + i + ", " + 0 + ")\t" + minNumber + "\t" + (amount / denominations[i]));
                    minNumber = Math.min(amount / denominations[i], minNumber);
                } else {
                    break;
                }
                continue;
            }
            boolean flag1 = true;
            for (int k = Math.min(maxNum, minNumber); (k >= 0) && flag1; k--) {
                int remainingAmount = (amount - k * denominations[i]);
                boolean flag = true;
                for (int j = i - 1; (j >= 0) && flag; j--) { //keep in mind that denomination[i] > denomination[j]
                    if (k * denominations[i] > amount) {
                        flag = false;
                        continue;
                    }
                    if (remainingAmount % denominations[j] == 0) {
                        if ((remainingAmount / denominations[j] <= (maxNum - k))) {
                            minNumber = Math.min(k + remainingAmount / denominations[j], minNumber);
                            flag = false;
                        }
                    }
                }
            }
        }
        if (minNumber == Integer.MAX_VALUE) {
            minNumber = -1;
        }
        return minNumber;
    }
}