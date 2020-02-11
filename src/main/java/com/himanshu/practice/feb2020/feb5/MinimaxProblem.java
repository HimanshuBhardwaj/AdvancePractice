package com.himanshu.practice.feb2020.feb5;

import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.concurrent.ForkJoinPool;

/**
 * @author Himanshu Bhardwaj
 * Date 07/Feb/2020
 */
public class MinimaxProblem {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        Array[] arrays = new Array[n];

        Comparator<Array> maxC = new Comparator<Array>() {
            @Override
            public int compare(Array o1, Array o2) {
                return Integer.compare(o1.max, o2.max);
            }
        };

        Comparator<Array> minC = new Comparator<Array>() {
            @Override
            public int compare(Array o1, Array o2) {
                return Integer.compare(o1.min, o2.min);
            }
        };

        TreeSet<Array> max = new TreeSet<>(maxC);
        TreeSet<Array> min = new TreeSet<>(minC);


        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            ArrayList<Integer> list = new ArrayList<>();

            for (int j = 0; j < str.length; j++) {
                list.add(Integer.parseInt(str[j]));
            }

            arrays[i] = new Array(list, i);

            max.add(arrays[i]);
            min.add(arrays[i]);
        }

        int index1 = 0;
        int index2 = 0;
        int maxN = arrays[0].max;


        for (int i = 0; i < arrays.length; i++) {
            int maxEE = arrays[i].max;
            Array temp = new Array();
            temp.min = maxEE;
            Array minArray = min.ceiling(temp);
            if (minArray == null) {
                continue;
            } else {
                index1 = i;
                index2 = minArray.index;
                maxN = maxEE;
            }
        }

        System.out.println(index1 + " " + index2);


    }
}


@NoArgsConstructor
class Array {
    ArrayList<Integer> integers;
    int index;
    int min;
    int max;

    public Array(ArrayList<Integer> list, int index) {
        this.index = index;
        this.integers = list;
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;

        for (int x : list) {
            min = Math.min(min, x);
            max = Math.max(max, x);
        }
    }
}