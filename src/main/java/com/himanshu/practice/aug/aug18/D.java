package com.himanshu.practice.aug.aug18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by himanshubhardwaj on 18/08/19.
 */
public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");
        long[] arr = new long[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(str[i]);
        }

        if (n >= 500) {
            System.out.println(3);
            return;
        }

        Graph graph = new Graph(n, arr);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((arr[i] & arr[j]) != 0l) {
                    graph.insert(i, j);
                }
            }
        }
        //graph.print();
        int length = graph.shortestCycle();
        System.out.print((length == Integer.MAX_VALUE) ? -1 : length);


    }
}


class Graph {
    ArrayList<Integer>[] adjList;
    int size;
    long[] arr;

    public Graph(int n, long[] arr) {
        this.arr = arr;
        adjList = new ArrayList[n];
        size = n;
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
    }


    public void insert(int s, int d) {
        adjList[s].add(d);
        adjList[d].add(s);
    }


    public int shortestCycle() {
        int[] visited = new int[size];
        int length = Integer.MAX_VALUE;
        Queue<Integer> queue = new LinkedList<>();

        int tLength = shortestCycleHelper((int) (Math.random() * size), visited, queue);
        length = Math.min(length, tLength);

        for (int i = 0; i < visited.length; i++) {
            if (visited[i] == 0) {
                visited[i] = 1;
                tLength = shortestCycleHelper(i, visited, queue);
                if (tLength < length) {
                    length = tLength;
                }
            }
        }
        return length;
    }

    private int shortestCycleHelper(int index, int[] visited, Queue<Integer> queue) {
        int level = 2;
        int minCycle = Integer.MAX_VALUE;
        queue.add(index);
        visited[index] = 1;

        while (!queue.isEmpty()) {
            int root = queue.poll();

            for (int neighbour : adjList[root]) {
                if (visited[neighbour] == 0) {
                    visited[neighbour] = visited[root] + 1;
                    queue.add(neighbour);
                } else {
                    if (visited[neighbour] >= visited[root]) {
                        minCycle = Math.min(minCycle, 2 * (visited[neighbour] - 1) + ((visited[neighbour] != visited[root]) ? 0 : 1));
                    }
                }
            }
            level++;
        }
        return minCycle;
    }


    void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(i + ": ");
            for (int j : adjList[i]) {
                System.out.print(j + ", ");
            }
            System.out.println();
        }
    }
}