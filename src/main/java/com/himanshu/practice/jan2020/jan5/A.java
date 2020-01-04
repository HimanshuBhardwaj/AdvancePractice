package com.himanshu.practice.jan2020.jan5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * Date 04/Jan/2020
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);


        String a[] = new String[n];
        String b[] = new String[m];

        str = br.readLine().split(" ");
        for (int i = 0; i < str.length; i++) {
            a[i] = str[i];
        }

        str = br.readLine().split(" ");
        for (int i = 0; i < str.length; i++) {
            b[i] = str[i];
        }


        int q = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);


        while (q-- > 0) {
            int num = Integer.parseInt(br.readLine()) - 1;
            String strA = a[(num % a.length)];
            String strB = b[(num % b.length)];
            pw.append(strA + strB + "\n");
        }
        pw.flush();
        pw.close();

    }
}
