package com.himanshu.practice.nov.nov27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println(n);
        PrintWriter pw = new PrintWriter(System.out);


        while (n-- > 0) {
            String[] str = br.readLine().split(" ");
            int c = Integer.parseInt(str[0]);
            int sum = Integer.parseInt(str[1]);
            int count = (int) Math.ceil(((double) sum) / (double) c);

            if (count * c < sum) {
                count++;
            }


            long cost = 0;
            int total = 0;

            int[] cc = new int[c];

            for (int i = cc.length - 1; i >= 0; i--) {

            }


            pw.append(cost + "\n");
        }
        pw.flush();
        pw.close();


    }
}
