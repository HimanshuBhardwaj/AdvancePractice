package com.himanshu.practice.july.july20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 20/07/19.
 */
public class B {
    static int[] numO;
    static int[] contVVSuffix;
    static int[] contVVPrefix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] chars = br.readLine().toCharArray();
        numO = new int[chars.length];
        contVVSuffix = new int[chars.length];
        contVVPrefix = new int[chars.length];


        for (int i = 0; i < numO.length; i++) {
            if (i > 0) {
                numO[i] += numO[i - 1] + (chars[i] == 'o' ? 1 : 0);
            } else {
                numO[i] += (chars[i] == 'o') ? 1 : 0;
            }
        }


        for (int i = contVVSuffix.length - 2; i >= 0; i--) {
            if (chars[i] == 'v' && chars[i + 1] == 'v') {
                contVVSuffix[i] += contVVSuffix[i + 1] + 1;
            } else {
                contVVSuffix[i] += contVVSuffix[i + 1];
            }
        }


        for (int i = 1; i < contVVPrefix.length; i++) {
            if (chars[i] == 'v' && chars[i - 1] == 'v') {
                contVVPrefix[i] += contVVPrefix[i - 1] + 1;
            } else {
                contVVPrefix[i] = contVVPrefix[i - 1];
            }
        }

//        for (int i = 0; i < contVVPrefix.length; i++) {
//            System.out.print(i + "\t");
//        }
//        System.out.println();
//        for (int i = 0; i < contVVPrefix.length; i++) {
//            System.out.print(contVVPrefix[i] + "\t");
//        }
//        System.out.println();
//        for (int i = 0; i < contVVSuffix.length; i++) {
//            System.out.print(contVVSuffix[i] + "\t");
//        }
//        System.out.println();


//        System.out.println(numW(0, chars.length - 1, chars));

        long count = 0;

        for (int i = 1; i < contVVPrefix.length - 1; i++) {
            if (chars[i] == 'o') {
                count += (contVVPrefix[i - 1] * (long)contVVSuffix[i + 1]);
            }
        }
        System.out.print(count);

    }


    static int numW(int start, int end, char[] chars) {
        if (end - start < 4) {
            return 0;
        }

        int i = start;

        for (; i + 1 <= end; i++) {
            if (chars[i] == 'v' && chars[i + 1] == 'v') {
                break;
            }
        }

        int j = end;

        for (; j - 1 >= start; j--) {
            if (chars[j] == 'v' && chars[j - 1] == 'v') {
                break;
            }
        }

        if (i == end || j == start) {
            return 0;
        }

        if (i + 1 == j - 1) {
            return 0;
        }

        System.out.println(getNumO(i + 2, j - 2) + "...");

        return getNumO(i + 2, j - 2) + numW(i + 2, j - 2, chars);
    }


    static int getNumO(int start, int end) {
        if (start > end) {
            return 0;
        }
        if (start > 0) {
            return numO[end] - numO[start - 1];
        } else {
            return numO[end];
        }
    }
}
