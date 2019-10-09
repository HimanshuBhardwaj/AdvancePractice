package com.himanshu.practice.oct.oct8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by himanshubhardwaj on 08/10/19.
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);


        while (t-- > 0) {
            String str[] = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int r = Integer.parseInt(str[1]);

            str = br.readLine().split(" ");
            LinkedList<Integer> linkedList = new LinkedList<>();

            for (int i = 0; i < str.length; i++) {
                linkedList.addLast(Integer.parseInt(str[i]));
            }
            Collections.sort(linkedList);

            int result = getResult(r, linkedList);
            pw.append(result + "\n");
        }
        pw.flush();

    }

    private static int getResult(int r, LinkedList<Integer> linkedList) {
        int distanceCovered = 0;
        int numberBlast = 0;

        while (!linkedList.isEmpty()) {
            numberBlast++;
            int lastPosition = linkedList.getLast();
            while (!linkedList.isEmpty() && (linkedList.getLast() == lastPosition)) {
                linkedList.removeLast();
            }
            distanceCovered += r;

            while (!linkedList.isEmpty() && ((linkedList.getFirst() - distanceCovered) <= 0)) {
                linkedList.removeFirst();
            }
        }


        return numberBlast;
    }

}
