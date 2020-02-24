package com.himanshu.practice.feb2020.feb24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by himanshubhardwaj on 24/02/20.
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String[] str = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int m = Integer.parseInt(str[1]);
            char[] combo = br.readLine().toCharArray();
            str = br.readLine().split(" ");
            int[] arr = new int[str.length];

            for (int i = 0; i < arr.length; i++) {
                arr[i] = Integer.parseInt(str[i]);
            }


            pw.append(getCount(combo, arr)+"\n");


        }
        pw.flush();
        pw.close();
    }

    private static String getCount(char[] combo, int[] arr) {
        int[] frequency = new int[26];

        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();


        map.put(0, clone(frequency));

        for (int i = 0; i < combo.length; i++) {
            frequency[combo[i] - 'a']++;
            map.put(i + 1, clone(frequency));
        }


        int[] result = new int[26];

        for (int x : arr) {
            add(result, map.get(x));
        }

        add(result, map.get(combo.length));

        StringBuffer sb = new StringBuffer();
        for (int x : result) {
            sb.append(x + " ");
        }

        return sb.toString();
    }

    static void add(int[] arr, ArrayList<Integer> list) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] += list.get(i);
        }
    }

    static ArrayList<Integer> clone(int[] list) {
        ArrayList<Integer> clone = new ArrayList<>(26);
        for (int z : list) {
            clone.add(z);
        }

        return clone;
    }
}

/*

0 0 9 4 5 3 0 0 0 0 0 0 0 0 9 0 0 3 1 0 0 0 0 0 0 0

* */