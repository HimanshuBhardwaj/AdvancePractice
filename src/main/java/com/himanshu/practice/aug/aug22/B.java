package com.himanshu.practice.aug.aug22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by himanshubhardwaj on 22/08/19.
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        boolean[][] A = new boolean[n][m];
        boolean[][] B = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            for (int j = 0; j < str.length; j++) {
                if (str[j].equals("1")) {
                    A[i][j] = true;
                }
            }
        }

        Queue<Steps> queue = new LinkedList<>();


        for (int i = 0; i < (n - 1); i++) {
            for (int j = 0; j < (m - 1); j++) {
                if (A[i][j] && A[i + 1][j] && A[i][j + 1] && A[i + 1][j + 1]) {
                    B[i][j] = true;
                    B[i + 1][j] = true;
                    B[i][j + 1] = true;
                    B[i + 1][j + 1] = true;
                    queue.add(new Steps(i, j));
                }
            }
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (A[i][j] != B[i][j]) {
                    System.out.print(-1);
                    return;
                }
            }
        }

        PrintWriter pw = new PrintWriter(System.out);
        pw.append(queue.size() + "\n");

        for (Steps s : queue) {
            pw.append(s + "\n");
        }
        pw.flush();


    }
}


class Steps {
    int x;
    int y;

    @java.beans.ConstructorProperties({"x", "y"})
    public Steps(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return (x + 1) + " " + (1 + y);
    }
}