package com.himanshu.practice.aug.aug18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 18/08/19.
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] a = new long[n];
        long sum = 0l;
        String[] str = br.readLine().split(" ");
        int zeroC = 0;
        int pC = 0;
        int nC = 0;


        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(str[i]);
            if (a[i] == 0) {
                zeroC++;
            } else if (a[i] > 0) {
                pC++;
            } else {
                nC++;
            }
        }


        if (nC % 2 == 0) {
            for (int i = 0; i < n; i++) {

                if (a[i] == 0) {
                    sum++;
                } else {
                    sum += Math.abs(a[i]) - 1;
                }

            }
            System.out.print(sum);
        } else {
            if (zeroC > 0) {
                for (int i = 0; i < n; i++) {

                    if (a[i] == 0) {
                        sum++;
                    } else {
                        sum += Math.abs(a[i]) - 1;
                    }

                }
                System.out.print(sum);
                return;
            } else {
                for (int i = 0; i < n; i++) {

                    if (a[i] == 0) {
                        sum++;
                    } else {
                        sum += Math.abs(a[i]) - 1;
                    }

                }
                sum += 2;
                System.out.print(sum);
            }
        }

    }
}
