package com.himanshu.practice.july.july20;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

/**
 * Created by himanshubhardwaj on 20/07/19.
 */
public class A {
    static final char CTRL_A = '\u0001';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] str = new String[n];
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            str[i] = br.readLine();
            sb.append(str[i] + CTRL_A);
        }
        System.out.println(sb.toString());
        LinkedList linkedList = new LinkedList();
        linkedList.add(sb.toString());

        PrintWriter pw = new PrintWriter("/Users/himanshubhardwaj/Desktop/job_practice/AdvancePractice/src/test/resources/the-file-name.txt");
        pw.append(sb.toString());
        pw.flush();

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Deserialising");
        String [] strings = sb.toString().split(CTRL_A+"");

        for (String s:strings) {
            System.out.println(s);
        }



    }
}
