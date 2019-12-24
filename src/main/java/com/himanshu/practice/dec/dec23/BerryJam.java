package com.himanshu.practice.dec.dec23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * @author Himanshu Bhardwaj
 * Date 23/Dec/2019
 * Statement: https://codeforces.com/contest/1278/problem/C
 * Submission: https://codeforces.com/contest/1278/submission/67502715
 */
public class BerryJam {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int a[] = new int[2 * n];
            String[] str = br.readLine().split(" ");

            for (int i = 0; i < str.length; i++) {
                a[i] = Integer.parseInt(str[i]);
            }

            pw.append(computeMinimumJars(a) + "\n");
        }
        pw.flush();
        pw.close();
    }

    private static int computeMinimumJars(int[] a) {
        Index[] indices = new Index[a.length];
        int score = 0;
        HashMap<Integer, TreeSet<Index>> indexes = new HashMap<>();
        int minimumNumber = 2 * a.length;

        for (int i = a.length - 1; i >= a.length / 2; i--) {
            if (a[i] == 2) {
                score--;
            } else {
                score++;
            }
            indices[i] = new Index(score, i);
            if (!indexes.containsKey(score)) {
                indexes.put(score, new TreeSet<Index>());
            }
            indexes.get(score).add(indices[i]);
            if (score == 0) {
                minimumNumber = Math.min(i, minimumNumber);

            }
        }
        //System.out.println(indexes);


        minimumNumber = Math.min(minimumNumber, (score == 0) ? a.length / 2 : a.length);

        score = 0;
        for (int i = 0; i < a.length / 2; i++) {
            if (a[i] == 2) {
                score--;
            } else {
                score++;
            }
            if (score == 0) {
                minimumNumber = Math.min(a.length - i - 1, minimumNumber);

            }
        }

        minimumNumber = (score == 0) ? Math.min(minimumNumber, a.length / 2) : minimumNumber;


        score = 0;
        for (int i = 0; i < a.length / 2; i++) {
            if (a[i] == 2) {
                score--;
            } else {
                score++;
            }
            if (indexes.containsKey(-score)) {
                minimumNumber = Math.min(minimumNumber, indexes.get(-score).first().index - i - 1);
            }


        }

        return minimumNumber;
    }
}

class Index implements Comparable<Index> {
    int score;
    int index;

    @java.beans.ConstructorProperties({"score", "index"})
    public Index(int score, int index) {
        this.score = score;
        this.index = index;
    }

    @Override
    public int compareTo(Index o) {
        return Integer.compare(this.index, o.index);
    }

    public String toString() {
        return "Index(score=" + this.score + ", index=" + this.index + ")";
    }
}

/*

1
2
1 2 1 1

1
3
1 2 2 2 1 1

* */