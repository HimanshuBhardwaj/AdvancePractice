package com.himanshu.practice.july.july28;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by himanshubhardwaj on 28/07/19.
 */
public class ParallelMergeSort {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    }
}

class Mapper<T> implements Runnable {
    T[] arrSegment;
    Comparator<T> comparator;

    @Override
    public void run() {
        Arrays.sort(arrSegment, comparator);
    }
}


class Reducer<T> {
    ArrayList<Mapper<T>> mapper;
    Comparator<T> comparator;

    public Reducer(Comparator<T> comparator) {
        mapper = new ArrayList<>();
        this.comparator = comparator;
    }

    public void addMapper(Mapper<T> mapper) {
        this.mapper.add(mapper);
    }

    public T[] reduce() {
        PriorityQueue<T> priorityQueue = new PriorityQueue<>();

        return null;
    }

}
