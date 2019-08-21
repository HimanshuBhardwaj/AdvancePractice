package com.himanshu.practice.july.july22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by himanshubhardwaj on 22/07/19.
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String[] str = br.readLine().split(" ");
            ArrayList<Integer> arrayList = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                arrayList.add(Integer.parseInt(str[i]));
            }
            Collections.sort(arrayList, Collections.<Integer>reverseOrder());
            int result = Math.min(arrayList.get(1)-1, n - 2);
            pw.append(result + "\n");
        }
        pw.flush();
    }
}
