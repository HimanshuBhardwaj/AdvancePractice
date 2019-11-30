package com.himanshu.practice.nov.nov16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 16/11/19.
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        PrintWriter pw = new PrintWriter(System.out);
        str = br.readLine().split(" ");
        ArrayList<Long> a = new ArrayList<>(n);
        TreeSet<Element> elements[] = new TreeSet[n];

        for (int i = 0; i < n; i++) {
            a.add(Long.parseLong(str[i]));
            elements[i] = new TreeSet<>();
        }

        Collections.sort(a);

        long[] sumLevel = new long[n];
        int pos = 0;
        TreeSet<Element> treeSet = new TreeSet<>();
        long previousCost = 0;


        for (int i = 0; i < n; i++) {
            if (elements[pos].size() < m) {
                Element ce = new Element(a.get(i));
                ce.bucket = pos + 1;
                Element lower = treeSet.lower((elements[pos].size() > 0) ? elements[pos].first() : ce);
                if (lower == null) {
                    treeSet.add(ce);
                    previousCost += (ce.value * (pos + 1));
                    elements[pos].add(ce);
                } else {
                    previousCost = previousCost - (lower.value * lower.bucket) + (lower.value * (pos + 1)) + (ce.value * lower.bucket);
                    ce.bucket = lower.bucket;
                    lower.bucket = pos + 1;
                    elements[lower.bucket - 1].remove(lower);
                    elements[lower.bucket - 1].add(ce);
                    elements[pos].add(lower);
                    treeSet.add(ce);
                }
            } else {
                pos++;
                i--;
                continue;
            }
            pw.append(previousCost + " ");
        }
        pw.flush();
        pw.close();
    }
}

class Element implements Comparable<Element> {
    long value;
    int bucket;

    public Element(long v) {
        value = v;
    }

    @Override
    public int compareTo(Element o) {
        if (this.value != o.value) {
            return Long.compare(this.value, o.value);
        } else {
            return Integer.compare(this.bucket, o.bucket);
        }
    }
}
