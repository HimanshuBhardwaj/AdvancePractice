package com.himanshu.practice.oct.oct24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by himanshubhardwaj on 24/10/19.
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            char[] str = br.readLine().toCharArray();
            int numbers[] = new int[str.length];

            for (int j = 0; j < numbers.length; j++) {
                numbers[j] = Integer.parseInt(str[j] + "");
            }

            pw.append(minimum(numbers));
        }

        pw.flush();
        pw.close();


    }

    private static String minimum(int[] numbers) {
        Queue<Integer> oddP = new LinkedList<>();
        Queue<Integer> evenP = new LinkedList<>();

        for (int x : numbers) {
            if ((x & 1) == 0) {
                oddP.add(x);
            } else {
                evenP.add(x);
            }
        }

        StringBuffer sb = new StringBuffer();

        while (!oddP.isEmpty() && !evenP.isEmpty()) {
            if (oddP.peek() < evenP.peek()) {
                sb.append(oddP.poll());
            } else {
                sb.append(evenP.poll());
            }
        }

        while (!oddP.isEmpty()) {
            sb.append(oddP.poll());
        }

        while (!evenP.isEmpty()) {
            sb.append(evenP.poll());
        }
        sb.append("\n");
        return sb.toString();
    }


}
