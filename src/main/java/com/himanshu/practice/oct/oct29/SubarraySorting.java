package com.himanshu.practice.oct.oct29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;


/**
 * Created by himanshubhardwaj on 30/10/19.
 * Statement: https://codeforces.com/contest/1187/problem/D
 * Algo: Segment Tree
 * Submission: https://codeforces.com/contest/1187/submission/63780405
 */
public class SubarraySorting {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String[] str = br.readLine().split(" ");
            int[] a = new int[str.length];
            int[] b = new int[str.length];

            for (int i = 0; i < a.length; i++) {
                a[i] = Integer.parseInt(str[i]);
            }

            str = br.readLine().split(" ");
            for (int i = 0; i < str.length; i++) {
                b[i] = Integer.parseInt(str[i]);
            }

            boolean isPossible = (n > 100) ? ((hadSamePArity(a, b)) && SolutionSegmentTree.isPossible(a, b)) : (Bruteforce.isPossible(a, b));
            if (isPossible) {
                pw.append("YES\n");
            } else {
                pw.append("NO\n");
            }
        }
        pw.flush();
        pw.close();
    }

    private static boolean hadSamePArity(int[] a, int[] b) {
        int[] cA = a.clone();
        int[] cB = b.clone();

        Arrays.sort(cA);
        Arrays.sort(cB);

        for (int i = 0; i < a.length; i++) {
            if (cA[i] != cB[i]) {
                return false;
            }
        }

        return true;

    }
}


class SolutionSegmentTree {
    public static boolean isPossible(int[] a, int[] b) {
        HashMap<Integer, LinkedList<Integer>> aIndexing = new HashMap<>();
        int pos = 0;
        for (int aE : a) {
            if (aIndexing.containsKey(aE)) {
                aIndexing.get(aE).add(pos);
            } else {
                aIndexing.put(aE, new LinkedList<Integer>());
                aIndexing.get(aE).add(pos);
            }
            pos++;
        }

        for (int x : aIndexing.keySet()) {
            Collections.sort(aIndexing.get(x));
        }
        //System.out.println(aIndexing);
        //System.out.println();

        SegmentTree segmentTree = new SegmentTree(a);

        for (int bE : b) {
            int pE;
            if (aIndexing.get(bE).isEmpty()) {
                return false;
            } else {
                pE = aIndexing.get(bE).getFirst();
                aIndexing.get(bE).removeFirst();
            }

            int minE = segmentTree.getMin(0, pE);
            if (minE < bE) {
                return false;
            } else {
                segmentTree.remove(pE);
            }
        }
        return true;
    }
}


class SegmentTree {
    int[] segTree;
    int size;

    public SegmentTree(int[] a) {
        size = (int) Math.pow(2, Math.ceil(Math.log(a.length) / Math.log(2)));
        size = (2 * size) - 1;
        segTree = new int[size];

        for (int i = 0; i <= size / 2; i++) {
            if (i < a.length) {
                segTree[i + size / 2] = a[i];
            } else {
                segTree[i + size / 2] = Integer.MAX_VALUE;
            }
        }

        for (int i = ((size / 2) - 1); i >= 0; i--) {
            segTree[i] = Math.min(segTree[(2 * i) + 1], segTree[(2 * i) + 2]);
        }

        //System.out.println("Tree Formation Completed");
    }


    public int getMin(int start, int end) {
        return getMinHelper(0, size / 2, start, end, 0);
    }


    private int getMinHelper(int startI, int endI, int s, int e, int index) {
        //System.out.println(startI + "\t" + endI);
        if (startI < 0 || endI < startI || index >= segTree.length || endI < s || startI > e) {
            return Integer.MAX_VALUE;
        }

        if (startI >= s && endI <= e) {
            return segTree[index];
        }


        if (startI == endI) {
            return Integer.MAX_VALUE;
        }

        int mid = (startI + endI) / 2;

        return Math.min(getMinHelper(startI, mid, s, e, (2 * index) + 1),
                getMinHelper(mid + 1, endI, s, e, (2 * index) + 2));
    }

    public void remove(int pE) {
        segTree[(size / 2) + pE] = Integer.MAX_VALUE;
        int pos = (((size / 2) + pE) - 1) / 2;

        while (pos >= 0) {
            //  System.out.println("Pos\t" + pos);
            segTree[pos] = Math.min(segTree[2 * pos + 1], segTree[2 * pos + 2]);
            if (pos == 0) {
                break;
            }
            pos = (pos - 1) / 2;
        }
        //System.out.println();
        //System.out.print("segTree after removing index " + pE + "\t\t");
        //print();
    }

    void print() {
        for (int x : segTree) {
            System.out.print(x + ", ");
        }
        System.out.println();
    }
}


class Bruteforce {
    public static boolean isPossible(int[] a, int[] b) {
        LinkedList<Integer> aL = new LinkedList<>();
        for (int x : a) {
            aL.addLast(x);
        }


        for (int x : b) {
            int min = Integer.MAX_VALUE;
            int pos = 0;
            boolean found = false;

            for (int aE : aL) {
                if (aE == x) {
                    found = true;
                    break;
                } else if (aE < min) {
                    min = aE;
                }
                pos++;
            }
            if (found && min > x) {
                aL.remove(pos);
            } else {
                return false;
            }
        }


        return true;
    }
}
/*



1
7
1 7 1 4 4 5 6
1 1 4 4 5 7 6


 *
 *
 * */