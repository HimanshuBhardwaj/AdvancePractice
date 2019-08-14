package com.himanshu.practice.july.july23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GenericSorting {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int[] strA = new int[str.length];

        for (int i = 0; i < strA.length; i++) {
            strA[i] = Integer.parseInt(str[i]);
        }


        PerUtils.printAllPermutations(0,strA.length-1,strA);
    }
}

