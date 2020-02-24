package com.himanshu.practice.feb2020.feb21;

/**
 * Created by himanshubhardwaj on 21/02/20.
 */
public class OperateString {
    public static void main(String[] args) {
        OperateString operateString = new OperateString();
        String s = "ha";
        int moves[] = {1000000000,-900000000,800000000,-700000000,600000000,543210987,-1000000000,900000000,-800000000,700000000,-600000000,-543210987};
        System.out.println(operateString.operate(s,moves));

    }

    public String operate(String s, int[] moves) {
        int start = 0;

        for (int z : moves) {
            if (z > 0) {
                start = (start + z) % s.length();
            } else if (z < 0) {
                start = (start + z) % s.length();
            }
        }

        if (start < 0) {
            start = (((Math.abs(start) / s.length()) + 1) * s.length()) + start;
        }

        start = start % s.length();

        StringBuffer sb = new StringBuffer();

        for (int i = start; i < s.length(); i++) {
            sb.append(s.charAt(i));
        }

        for (int i = 0; i < start; i++) {
            sb.append(s.charAt(i));
        }

        return sb.toString();

    }
}
