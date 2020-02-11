package com.himanshu.practice.feb2020.feb11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author Himanshu Bhardwaj
 * Date 11/Feb/2020
 * ALGO: Binary Search
 * Submission: https://codeforces.com/contest/1288/submission/70775831
 * Statement: https://codeforces.com/contest/1288/problem/D
 */
public class MinimaxProblem {
    static int[] bitArray;
    static HashMap<Integer, Integer> mapping = new HashMap<>();
    static int array1, array2, value;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        int maxNum = (int) (Math.pow(2, m) - 1);
        //System.out.println("MAx num bit rep\t" + Integer.toBinaryString(maxNum));
        bitArray = new int[n];

        ArrayList<Array> arrays = new ArrayList<>();
        HashSet<Integer> hashSet = new HashSet<>();
        ArrayList<Integer> numbers = new ArrayList<>();


        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            Array array = new Array(i);
            array.parse(str, numbers);
            arrays.add(array);
        }

        //System.out.println("Possible Numbers" + treeSet);


        Collections.sort(numbers);

        int start = 0;
        int end = numbers.size() - 1;
        int maxPossible = numbers.get(0);

        while ((end - start) >= 2) {
            int mid = (end + start) / 2;

            if (isPossible(numbers.get(mid), arrays, maxNum)) {
                start = mid;
                maxPossible = Math.max(numbers.get(mid), maxPossible);
            } else {
                end = mid;
            }
        }

        for (int i = start; i <= end; i++) {
            if (isPossible(numbers.get(i), arrays, maxNum)) {
                maxPossible = Math.max(numbers.get(i), maxPossible);
            }
        }

        System.out.print(array1 + " " + array2);


    }

    private static boolean isPossible(Integer integer, ArrayList<Array> arrays, int maxNum) {
        reset();


        for (Array a : arrays) {
            a.setBitRep(integer, mapping);
        }
        //System.out.println(integer + "\t" + mapping);

        for (int x : mapping.keySet()) {
            for (int y : mapping.keySet()) {
                if ((x | y) == maxNum) {
                    array1 = mapping.get(x);
                    array2 = mapping.get(y);
                    value = integer;
                    return true;
                }
            }
        }

        return false;
    }

    private static void reset() {
        mapping.clear();

        for (int i = 0; i < bitArray.length; i++) {
            bitArray[i] = 0;
        }
    }

}


class Array {
    int[] arr;
    int bitRep;
    int index;

    public Array(int index) {
        this.index = index;
    }

    void setBitRep(int x, HashMap<Integer, Integer> mapping) {
        bitRep = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= x) {
                bitRep = bitRep | (1 << i);
            }
            //System.out.print(arr[i] + " ");
        }
        //System.out.println("\t" + x + "\t" + Integer.toBinaryString(bitRep));


        mapping.put(bitRep, index + 1);

    }

    void parse(String[] str, ArrayList<Integer> treeSet) {
        arr = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            arr[i] = Integer.parseInt(str[i]);
            treeSet.add(arr[i]);
        }
    }
}