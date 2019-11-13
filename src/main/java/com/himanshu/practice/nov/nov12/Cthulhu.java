package com.himanshu.practice.nov.nov12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

/*
* @author: Himanshu Bhardwaj
* Statement: https://codeforces.com/contest/103/problem/B
* Submission: https://codeforces.com/contest/103/submission/64759838
* Algo: DFS
* */

public class Cthulhu {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        Graph graph = new Graph(n);

        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            int s = Integer.parseInt(str[0]);
            int d = Integer.parseInt(str[1]);
            graph.insert(s, d);
        }

        //graph.print();

        if (graph.detectCycle(0)) {
            System.out.print("FHTAGN!");
        } else {
            System.out.print("NO");
        }
    }
}


class Graph {
    ArrayList<Integer> adjList[];
    HashSet<Integer> cycle;

    public Graph(int n) {
        adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
        cycle = new HashSet<>();
    }


    void insert(int s, int d) {
        s--;
        d--;
        adjList[s].add(d);
        adjList[d].add(s);
    }


    boolean detectCycle(int r) {
        boolean[] visited = new boolean[adjList.length];
        LinkedList<Integer> path = new LinkedList<>();
        boolean result = DFS(r, -1, visited, path);
        //System.out.println(result + "\t\t" + cycle.isEmpty() + "\t\t");
        if (!result) {
            return false;
        } else {
            for (boolean x : visited) {
                if (x == false) {
                    return false;
                }
            }
            return (cycle.isEmpty() == false);
        }
    }

    private boolean DFS(int index, int parent, boolean[] visited, LinkedList<Integer> path) {
        if (visited[index]) {
            if (cycle.size() == 0) {
                boolean flag = false;
                for (int x : path) {
                    if (x == index) {
                        flag = true;
                    }
                    if (flag) {
                        cycle.add(x);
                    }
                }
                return true;
            } else {
                if (cycle.contains(index)) {
                    return true;
                }

                return false;
            }
        }

        visited[index] = true;
        path.addLast(index);

        for (int neighbour : adjList[index]) {
            if (neighbour != parent) {
                if (!DFS(neighbour, index, visited, path)) {
                    return false;
                }
            }

        }
        path.removeLast();

        return true;
    }


    void print() {
        for (int i = 0; i < adjList.length; i++) {
            System.out.print(i + ": ");
            for (int n : adjList[i]) {
                System.out.print(n + ",");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();

    }


}

/*

6 6
6 3
6 4
5 1
2 5
1 4
5 4


* */