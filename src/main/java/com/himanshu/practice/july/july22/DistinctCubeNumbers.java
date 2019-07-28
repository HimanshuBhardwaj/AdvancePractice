package com.himanshu.practice.july.july22;

import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 25/07/19.
 */
public class DistinctCubeNumbers {
    public static void main(String[] args) {
        TreeSet<Long> treeSet = new TreeSet<>();
        for (int i = 0; i < 10000; i++) {
            treeSet.add(1l*i * i * i);
        }

        TreeSet<Long> pairs = new TreeSet<>();

        for (long i : treeSet) {
            for (long j : treeSet) {
                pairs.add(i + j);
            }
            if (i%1000 ==0) {
                System.out.println(i);
            }
        }

        System.out.println(pairs.size());


    }
}
