package com.himanshu.practice.jan2020.jan6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

/**
 * @author Himanshu Bhardwaj
 * Date 05/Jan/2020
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int k = Integer.parseInt(str[1]);
        TreeSet<Integer>[][] treeSets = new TreeSet[3][k];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < k; j++) {
                treeSets[i][j] = new TreeSet<>();
            }
        }


        for (int i = 0; i < n; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < chars.length; j++) {
                switch (chars[j]) {
                    case 'S':
                        treeSets[0][j].add(i);
                        break;
                    case 'E':
                        treeSets[1][j].add(i);
                        break;
                    case 'T':
                        treeSets[2][j].add(i);
                        break;
                }
            }
        }

        long count = 0;
        TreeSet<Integer> workingSet = new TreeSet<>();
        for (int j = 0; j <= 3; j++) {
            workingSet = new TreeSet<>();
            if (j < 3) {
                workingSet.addAll(treeSets[j][0]);
            } else {
                workingSet.addAll(treeSets[0][0]);
                intersection(workingSet, treeSets[1][0]);
                intersection(workingSet, treeSets[2][0]);
            }
            count += getRemaining(1, k, treeSets, workingSet);
        }
        System.out.println(count);
    }

    private static long getRemaining(int i, int k, TreeSet<Integer>[][] treeSets, TreeSet<Integer> workingSet) {
        if (i == k) {
            System.out.println(workingSet);
            return workingSet.size();
        }

        if (i > k) {
            return 0;
        }

        if (workingSet.size() == 0) {
            return 0;
        }


        TreeSet<Integer> workingSetC = (TreeSet<Integer>) workingSet.clone();
        TreeSet<Integer> workingSetC1 = (TreeSet<Integer>) workingSet.clone();
        TreeSet<Integer> workingSetC2 = (TreeSet<Integer>) workingSet.clone();
        TreeSet<Integer> workingSetC3 = (TreeSet<Integer>) workingSet.clone();

        intersection(workingSetC, treeSets[0][i]);
        intersection(workingSetC, treeSets[1][i]);
        intersection(workingSetC, treeSets[2][i]);
        long c4 = getRemaining(i + 1, k, treeSets, workingSetC);

        intersection(workingSetC1, treeSets[0][i]);
        long c1 = getRemaining(i + 1, k, treeSets, workingSetC1);
        intersection(workingSetC2, treeSets[1][i]);
        long c2 = getRemaining(i + 1, k, treeSets, workingSetC2);
        intersection(workingSetC3, treeSets[2][i]);
        long c3 = getRemaining(i + 1, k, treeSets, workingSetC3);

        return c1 + c2 + c3 + c4;
    }

    private static void intersection(TreeSet<Integer> workingSet, TreeSet<Integer> integers) {
        for (int x : (TreeSet<Integer>) workingSet.clone()) {
            if (!integers.contains(x)) {
                workingSet.remove(x);
            }
        }
    }
}
