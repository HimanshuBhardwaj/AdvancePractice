package com.himanshu.practice.nov.nov24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.PriorityQueue;

public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    }
}


class ResultC {

    /*
     * Complete the 'reductionCost' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY num as parameter.
     */

    public static int reductionCost(List<Integer> num) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for (int x : num) {
            priorityQueue.add(x);
        }

        long cost = 0;

        while (priorityQueue.size() > 1) {
            int first = priorityQueue.poll();
            int second = priorityQueue.poll();
            cost += first + second;
            priorityQueue.add(first + second);
        }

        // Write your code here
        //this return type should be long
        return (int)cost;

    }

}