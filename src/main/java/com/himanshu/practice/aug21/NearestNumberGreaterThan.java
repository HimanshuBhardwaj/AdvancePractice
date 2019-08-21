package com.himanshu.practice.aug21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class NearestNumberGreaterThan {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int[] arr = new int[str.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }
        NearestNumberGreaterThanI nearestNumberGreaterThanI = new NearestNumberGreaterThanSqrtDecomposition(arr);

        for (int i = 0; i < arr.length; i++) {
            int result = nearestNumberGreaterThanI.NearestNumberGreaterThanI(i);
            System.out.println(arr[i] + "\t\t" + result);
        }
    }
}


class NearestNumberGreaterThanSqrtDecomposition implements NearestNumberGreaterThanI {
    private int[] arr;
    private ArrayList<Integer>[] sqrtDecompositon;
    private int sizeDecomposition;


    //arr.length >= 10
    public NearestNumberGreaterThanSqrtDecomposition(int[] arr) {
        this.sizeDecomposition = (int) Math.ceil(Math.sqrt(arr.length));
        sqrtDecompositon = new ArrayList[(int) Math.ceil(((double) (arr.length)) / sizeDecomposition)];
        this.arr = arr;

        for (int i = 0; i < sqrtDecompositon.length; i++) {
            sqrtDecompositon[i] = new ArrayList<>();
        }

        for (int i = 0; i < arr.length; i++) {
            int index = getSegment(i);
            sqrtDecompositon[index].add(arr[i]);
        }

        for (int i = 0; i < sizeDecomposition; i++) {
            Collections.sort(sqrtDecompositon[i]);
        }
        System.out.println();
    }


    public int NearestNumberGreaterThanI(int index) {
        if (index >= arr.length || index < 0) {
            return -1;
        }


        for (int i = index; i <= endIndexOfSegment(getSegment(index)); i++) {
            if (arr[index] < arr[i]) {
                return arr[i];
            }
        }

        int currentsegment = getSegment(index);

        for (int i = currentsegment + 1; i < sqrtDecompositon.length; i++) {
            if (sqrtDecompositon[i].get(sqrtDecompositon[i].size() - 1) > arr[index]) {
                return getCeil(arr[index], sqrtDecompositon[i]);
            }
        }

        return -1;
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


    private int getSegment(int index) {
        return (index / sizeDecomposition);

    }

    private int startingIndexOfSegment(int segment) {
        return segment * sizeDecomposition;
    }

    private int endIndexOfSegment(int segment) {
        return Math.min((((segment + 1) * sizeDecomposition) - 1), arr.length - 1);
    }
}
/*


1 3 1 2 5 7 9 11 2 5 11 33 0

* */