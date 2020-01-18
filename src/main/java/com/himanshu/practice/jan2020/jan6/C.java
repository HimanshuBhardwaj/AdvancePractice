package com.himanshu.practice.jan2020.jan6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Himanshu Bhardwaj
 * Date 05/Jan/2020
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int evenM = n / 2;
        int oddM = n - evenM;
        String[] str = br.readLine().split(" ");
        int first = -1;

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str[i]);
            if (arr[i] != 0) {
                if (arr[i] % 2 == 0) {
                    evenM--;
                } else {
                    oddM--;
                }
            } else {
                if (first == -1) {
                    first = i;
                }
            }
        }


        int c = 0;
        if (first == -1) {
            int count = 0;
            for (int i = 1; i < arr.length; i++) {
                count += checkParity(arr[i], arr[i - 1]);

            }
            return;
        } else {
            for (int i = 1; i < first; i++) {
                c += checkParity(arr[i], arr[i - 1]);
            }
        }


        System.out.print(Math.min(getCount(first, arr, evenM - 1, oddM, 2, (first == 0) ? -1 : arr[first - 1])
                        + c + ((first != 0) ? checkParity(0, arr[first - 1]) : 0),
                getCount(first, arr, evenM, oddM - 1, 1, ((first == 0) ? -1 : arr[first - 1]))
                        + c + ((first != 0) ? checkParity(1, arr[first - 1]) : 0)));

    }

    private static long getCount(int index, int[] arr, int even, int oddM, int number, int previous) {


        if (even < 0 || oddM < 0) {
            //return Long.MAX_VALUE - 100000;
            return 0;
        }

        if (arr.length == index) {
            return 0;
        }


        long value = 0;

        if (arr[index] != 0) {
            if (index == 0) {
                value = getCount(index + 1, arr, even, oddM, number, arr[index]);
            } else {
                value = checkParity(arr[index], previous) + getCount(index + 1, arr, even, oddM, number, arr[index]);
            }
        } else {
            value = Long.MAX_VALUE - 10000;

//            value = Math.min(checkParity(previous, number) + (even > 0) ? getCount(index + 1, arr, even - 1, oddM, 2,
//                    arr[index]) : 0, checkParity(previous, number) + (oddM > 0) ? getCount(index + 1, arr, even, oddM - 1, 1, arr[index]));
        }


        return value;
    }

    private static long checkParity(int i, int previous) {
        if (previous == -1) {
            return 0;
        }

        if ((i % 2) == (previous % 2)) {
            return 0;
        }
        return 1;
    }
}
