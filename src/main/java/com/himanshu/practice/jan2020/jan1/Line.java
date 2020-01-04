package com.himanshu.practice.jan2020.jan1;

/**
 * @author Himanshu Bhardwaj
 * Date 31/Dec/2019
 * Statement: https://codeforces.com/problemset/problem/7/C
 */
public class Line {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.getSolution(3, 17);
        System.out.println(solution.x + "\t" + solution.y);
    }
}

class Solution {
    long x;
    long y;

    //a <= b
    void getSolution(long a, long b) {

        if (a == 1) {
            x = 1;
            y = 0;
            return;
        }

        getSolution(b % a, a);

        long temp = x;
        x = y;
        y = temp;

    }
}
