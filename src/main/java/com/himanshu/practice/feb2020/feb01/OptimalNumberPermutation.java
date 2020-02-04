package com.himanshu.practice.feb2020.feb01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * @author Himanshu Bhardwaj
 * Date 01/Feb/2020
 */
public class OptimalNumberPermutation {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[2 * n];

        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
            arr[n + n - i - 1] = i + 1;
        }

        PrintWriter pw = new PrintWriter(System.out);

        for (int x : arr) {
            pw.append(x + " ");
        }
        pw.flush();
        pw.close();


//        POC poc = new POC(n, arr);
//        System.out.println(poc.print(arr) + "..");
//        poc.allPermutuations(0, arr.length - 1, arr);
//        System.out.println(poc.minS);
//        for (int x : poc.clone) {
//            System.out.print(x + ",");
//        }
//        System.out.println();

    }
}

class POC {
    int n;
    int[] arr;
    long minS = Integer.MAX_VALUE;
    int[] clone;

    public POC(int n, int[] arr) {
        this.n = n;
        this.arr = arr;
    }

    public void allPermutuations(int start, int end, int[] arr) {
        if (start == end) {
            print(arr);
            return;
        }

        for (int i = start; i <= end; i++) {
            swap(start, i, arr);
            allPermutuations(start + 1, end, arr);
            swap(start, i, arr);
        }
    }

    private void swap(int start, int end, int[] arr) {
        int t = arr[start];
        arr[start] = arr[end];
        arr[end] = t;
    }


    //arr will contain 1..n
    long print(int[] arr) {
        HashMap<Integer, TreeSet<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.put(i + 1, new TreeSet<Integer>());
        }

        for (int i = 0; i < arr.length; i++) {
            map.get(arr[i]).add(i + 1);
        }

        long s = 0;

        for (int i = 1; i <= n; i++) {
            s += (arr.length - i) * Math.abs(map.get(i).last() - map.get(i).first());
        }
//        System.out.print(s + "\t\t");
//        for (int x : arr) {
//            System.out.print(x + ",");
//        }
//        System.out.println();


        if (s < minS) {
            clone = arr.clone();
            minS = s;
        }

        return s;
    }

}
