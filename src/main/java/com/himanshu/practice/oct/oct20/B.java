package com.himanshu.practice.oct.oct20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 20/10/19.
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        TreeSet<Numbers> treeSet = new TreeSet<>();
        String[] str = br.readLine().split(" ");


        for (int i = 0; i < str.length; i++) {
            treeSet.add(new Numbers(Long.parseLong(str[i]), i));
        }

        long hL = 0;
        long vL = 0;

        while (!treeSet.isEmpty()) {
            hL += treeSet.last().value;
            treeSet.remove(treeSet.last());
            if (!treeSet.isEmpty()) {
                vL += treeSet.first().value;
                treeSet.remove(treeSet.first());
            }
        }
        System.out.println(((hL * hL) + (vL * vL)));

    }
}

class Numbers implements Comparable<Numbers> {
    long value;
    int index;

    @java.beans.ConstructorProperties({"value", "index"})
    public Numbers(long value, int index) {
        this.value = value;
        this.index = index;
    }

    @Override
    public int compareTo(Numbers o) {
        if (this.value != o.value) {
            return Long.compare(this.value, o.value);
        } else {
            return Integer.compare(this.index, o.index);
        }
    }
}