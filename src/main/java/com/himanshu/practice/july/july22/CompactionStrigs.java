package com.himanshu.practice.july.july22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 27/07/19.
 * Algo: Recursion
 * Statement: https://www.careercup.com/question?id=5733696185303040
 */
public class CompactionStrigs {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        compact("", 0, 0, str);

    }

    private static void compact(String prefix, int num, int pos, String str) {
        if (pos >= (str.length() - 1)) {
            System.out.println(prefix + ((num != 0) ? num : "") + str.charAt(pos));
            return;
        }

        if (pos == 0) {
            compact("" + str.charAt(0), 0, 1, str);
        } else {
            if (num == 0) {
                compact(prefix + str.charAt(pos), num, pos + 1, str);
                compact(prefix, num + 1, pos + 1, str);
            } else {
                compact(prefix + num + str.charAt(pos), 0, pos + 1, str);
                compact(prefix, num + 1, pos + 1, str);
            }
        }
    }
}
