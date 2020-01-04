package com.himanshu.practice.dec.dec31;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Integer;

/**
 * @author Himanshu Bhardwaj
 * Date 31/Dec/2019
 */
public class InfiniteInversions {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] p = new int[100001];

        for (int i = 0; i < p.length; i++) {
            p[i] = i;
        }

        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split(" ");
            int a = Integer.parseInt(str[0]) - 1;
            int b = Integer.parseInt(str[1]) - 1;
            int temp = p[a];
            p[a] = p[b];
            p[b] = temp;
        }


        System.out.println(new BruteForce().number(p));


    }

}


class BruteForce {
    public long number(int[] number) {
        long count = 0;
        for (int i = 0; i < number.length; i++) {
            for (int j = i + 1; j < number.length; j++) {
                    if (number[i] > number[j]) {
                        count++;
                    }

            }
        }
        return count;
    }
}

