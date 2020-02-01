package com.himanshu.practice.jan2020.jan23;

import java.nio.charset.StandardCharsets;

/**
 * @author Himanshu Bhardwaj
 * Date 23/Jan/2020
 */
public class HowManyTrees {
    public static void main(String[] args) {
        String str = "\"data\": {\n" +
                "      \"signalName\": null,\n" +
                "      \"userId\": \"19884823\",\n" +
                "      \"userScoreS3\": 0,\n" +
                "      \"advIds\": [\n" +
                "         \"384c53a1-54d9-4377-9f09-996da65f5075\"\n" +
                "      ],\n" +
                "      \"deviceIds\": [\n" +
                "         \"869033026749748\"\n" +
                "      ]\n" +
                "   }";
        System.out.println(str.getBytes(StandardCharsets.UTF_8).length);

    }
}


class NumTrees {
    Long[][] treeNum;

    public NumTrees(int n, int h) {
        treeNum = new Long[n + 1][h + 1];

        for (int i = 0; i <= n; i++) {
            treeNum[i][0] = 0l;
        }

        for (int i = 1; i < n; i++) {
            treeNum[n][1] = 1l;
            if (i <= n && i <= h) {
                treeNum[i][i] = 1l;
            }
        }
    }


    long numTrees(int n, int h) {
        if (treeNum[n][h] != null) {
            return treeNum[n][h];
        }
        long result = -1;


        // Do something
        treeNum[n][h] = result;

        return treeNum[n][h];

    }
}