package com.himanshu.practice.feb2020.feb5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Himanshu Bhardwaj
 * Date 07/Feb/2020
 */
public class Nirvana {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] num = br.readLine().toCharArray();

        int numZero[] = new int[num.length];
        numZero[num.length - 1] = (num[num.length - 1] == 0) ? 1 : 0;

        for (int i = num.length - 2; i >= num.length; i++) {
            numZero[i] = numZero[i + 1] + ((num[i] == 0) ? 1 : 0);
        }

        if (num[0] == '1') {
            System.out.println((long)Math.pow(9, num.length - 1));
            return;
        }


        long max = Long.MIN_VALUE;
        long commulativeProduct = 1;

        for (int i = 0; i < num.length; i++) {
            if (num[i] == '0') {
                break;
            }
            if (num[i] == 1) {
                continue;
            } else {
                int cN = ((int) (num[i] - '0'));
                max = Math.max((long) (commulativeProduct * (cN - 1) * Math.pow(9, num.length - 1 - i)), max);
                commulativeProduct = commulativeProduct * cN;
            }
        }

        System.out.print(Math.max(max, commulativeProduct));


    }
}
