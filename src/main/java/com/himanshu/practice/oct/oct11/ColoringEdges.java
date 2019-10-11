package com.himanshu.practice.oct.oct11;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by himanshubhardwaj on 11/10/19.
 * ALGO: Cycle in Directed Graph
 * Submission: https://codeforces.com/contest/1217/submission/62350868
 * Statement: https://codeforces.com/contest/1217/problem/D
 */
public class ColoringEdges {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        Graph graph = new Graph(n);

        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            graph.insert(i, Integer.parseInt(str[0]), Integer.parseInt(str[1]));
        }
        graph.DFS();
        StringBuilder sb = new StringBuilder();
        for (Edge e : graph.edges) {
            sb.append(e.colour);
            sb.append(" ");
        }
        System.out.println((graph.flag) ? 2 : 1);
        System.out.print(sb.toString());
    }

}


class Graph {
    ArrayList<Edge> adjList[];
    int startTime[];
    int endTime[];
    int numNodes;
    int clock = 0;
    ArrayList<Edge> edges;
    boolean flag = false;

    public Graph(int n) {
        this.numNodes = n;
        startTime = new int[n];
        endTime = new int[n];
        adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList();
        }
        edges = new ArrayList<>();
    }

    void insert(int index, int s, int d) {
        s--;
        d--;
        Edge e = new Edge(index, s, d);
        adjList[s].add(e);
        edges.add(e);
    }

    void DFS() {
        for (int i = 0; i < numNodes; i++) {
            if (startTime[i] == 0) {
                DFSHelper(i);
            }
        }
    }

    private void DFSHelper(int node) {
        clock++;
        startTime[node] = clock;

        for (Edge e : adjList[node]) {
            int neighbour = e.end;
            if (endTime[neighbour] == 0) {
                if (startTime[neighbour] != 0) {
                    if (startTime[neighbour] < startTime[node]) {
                        e.incrementColour();
                        flag = true;
                    }
                } else {
                    DFSHelper(neighbour);
                }
            }

        }


        clock++;
        endTime[node] = clock;
    }


}


class Node {
    int source;
    int destination;
}

class Edge {
    int index;
    int start;
    int end;
    int colour;

    public Edge(int index, int s, int e) {
        this.index = index;
        this.start = s;
        this.end = e;
        this.colour = 1;
    }

    void incrementColour() {
        colour++;
    }
}