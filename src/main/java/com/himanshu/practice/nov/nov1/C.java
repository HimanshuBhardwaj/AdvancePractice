package com.himanshu.practice.nov.nov1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 01/11/19.
 */
public class C {
    static long PRIME = 1000000007l;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char strA[] = br.readLine().toCharArray();
        System.out.print(numberOFWays(strA));


    }

    private static long numberOFWays(char[] strA) {
        for (char c : strA) {
            if (c == 'm' || c == 'w') {
                return 0;
            }
        }

        int n = strA.length;
        long s[][] = new long[strA.length][2];
        s[n - 1][0] = 1;
        s[n - 1][1] = 0;


        if (strA.length == 1) {
            return 1;
        }

        s[n - 2][0] = s[n - 1][0] + s[n - 1][1];


        if (strA[n - 2] == 'u' && strA[n - 1] == 'u') {
            s[n - 2][1] = 1;
        } else if (strA[n - 2] == 'n' && strA[n - 1] == 'n') {
            s[n - 2][1] = 1;
        } else {
            s[n - 2][1] = 0;
        }

        if (strA.length == 2) {
            return s[0][0] + s[0][1];
        }


        for (int i = n - 3; i >= 0; i--) {
            s[i][0] = (s[i + 1][0] + s[i + 1][1]) % PRIME;

            if (strA[i] == 'u' && strA[i + 1] == 'u') {
                s[i][1] = (s[i + 2][0] + s[i + 2][1]);
            } else if (strA[i] == 'n' && strA[i + 1] == 'n') {
                s[i][1] = (s[i + 2][0] + s[i + 2][1]);
            } else {
                //s[i][0] = (s[i + 1][0] + s[i + 1][1]) % PRIME;
            }
            s[i][1] = s[i][1] % PRIME;
        }

        return (s[0][0] + s[0][1]) % PRIME;
    }
}
