package com.himanshu.practice.july.july23;

import java.util.TreeSet;

public class AdHocSet {
    public static void main(String[] args) {
        TempSet<String> tempSet = new TempSet();
        tempSet.insert("Himanshu");
        tempSet.insert("Bhardwaj");
        tempSet.insert("taport");
        System.out.println(tempSet.ceil("B"));
    }
}

class TempSet<T extends Comparable<T>> {
    TreeSet<T> set;

    public TempSet() {
        set = new TreeSet<>();
    }

    public void insert(T key) {
        set.add(key);
    }

    public boolean contains(T key) {
        return set.contains(key);
    }

    public T ceil(T key) {
        return set.ceiling(key);
    }
}
