package com.himanshu.practice.oct.oct12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by himanshubhardwaj on 12/10/19.
 */
public class ArraySplitting {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int k = Integer.parseInt(str[1]);

        str = br.readLine().split(" ");

        ArrayList<Long> diff = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            diff.add(get(str, i - 1, i));
        }

        long sum = get(str, n - 1, 0);

        Collections.sort(diff);


        for (int i=0;i<k-1;i++) {
            sum+=diff.get(i);
        }

        System.out.print(sum);


    }

    static Long get(String[] str, int a, int b) {
        return new Long(Integer.parseInt(str[a]) - Integer.parseInt(str[b]));
    }
}
