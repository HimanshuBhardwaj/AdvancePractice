package com.himanshu.practice.aug.aug21;

import java.util.ArrayList;
import java.util.Collections;

public class NearestNumberGreaterThanPersistentSegmentTree implements NearestNumberGreaterThanI {
    int[] arr;
    ArrayList<Integer> persistentSegmentTree[];

    public NearestNumberGreaterThanPersistentSegmentTree(int[] arr) {
        //arr length >10 only
        int size = (int) Math.pow(2, Math.ceil(Math.log(arr.length) / Math.log(2)));
        size = 2 * size + 1;
        this.arr = arr;
        persistentSegmentTree = new ArrayList[size];

        for (int i = 0; i < persistentSegmentTree.length; i++) {
            persistentSegmentTree[i] = new ArrayList<>();
        }

        for (int i = 0; i <= persistentSegmentTree.length / 2; i++) {
            if (i < arr.length) {
                persistentSegmentTree[(persistentSegmentTree.length / 2) + i].add(arr[i]);
            } else {
                persistentSegmentTree[(persistentSegmentTree.length / 2) + i].add(Integer.MAX_VALUE);
            }
        }

        for (int i = (persistentSegmentTree.length / 2) - 1; i >= 0; i--) {
            persistentSegmentTree[i].addAll(persistentSegmentTree[2 * i + 1]);
            persistentSegmentTree[i].addAll(persistentSegmentTree[2 * i + 2]);
            Collections.sort(persistentSegmentTree[i]);
        }
    }


    @Override
    public int nearestNumberGreaterThanI(int index) {
        if (index >= arr.length) {
            return -1;
        }
        int number = nearestNumberGreaterThanIHelper(0, persistentSegmentTree.length / 2, index + 1, persistentSegmentTree.length / 2, arr[index], 0);

        return (number != Integer.MAX_VALUE) ? number : -1;

    }

    private int nearestNumberGreaterThanIHelper(int segStart, int segEnd, int start, int end, int number, int index) {
        if (segStart > segEnd || start > end || segStart > end || segEnd < start) {
            return Integer.MAX_VALUE;
        }

        if (segStart >= segStart && segEnd <= end) {
            return getCeil(number, persistentSegmentTree[index]);
        }


        if (segStart == segEnd) {
            return Integer.MAX_VALUE;
        }

        int mid = (segStart + segEnd) / 2;
        int maxLEft = nearestNumberGreaterThanIHelper(segStart, mid, start, end, number, 2 * index + 1);
        if (maxLEft == Integer.MAX_VALUE) {
            return nearestNumberGreaterThanIHelper(mid + 1, end, start, end, number, 2 * index + 2);
        } else {
            return maxLEft;
        }

    }



    private int getCeil(int element, ArrayList<Integer> integers) {
        if (integers.size() == 0) {
            return -1;
        }

        return getCeilHelper(0, integers.size() - 1, element, integers);
    }

    private int getCeilHelper(int start, int end, int element, ArrayList<Integer> integers) {
        if (end < start) {
            return -1;
        }

        if (end == start) {
            if (integers.get(start) > element) {
                return integers.get(start);
            } else {
                return -1;
            }
        }

        if (end == (start - 1)) {
            if (integers.get(start) > element) {
                return integers.get(start);
            } else {
                if (integers.get(end) > element) {
                    return integers.get(end);
                } else {
                    return -1;
                }
            }
        }

        int mid = (start + end) / 2;
        if (integers.get(mid) > element) {
            return getCeilHelper(start, mid, element, integers);
        } else {
            return getCeilHelper(mid + 1, end, element, integers);
        }
    }
}
