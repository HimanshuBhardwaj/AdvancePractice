package com.himanshu.practice.nov.nov2;

/**
 * Created by himanshubhardwaj on 02/11/19.
 */
public class MaximumMoves {
    public long getMaximumMoves(long P, long Q) {
        if (P > Q) {
            return -1;
        }

        if ((Q - P) == 1) {
            return -1;
        }

        return (Q - P) / 2;
    }
}
