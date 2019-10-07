package com.himanshu.practice.aug.aug25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by himanshubhardwaj on 25/08/19.
 */
public class SolutionA {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tt = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);


        for (int t = 1; t <= tt; t++) {
            String[] str = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int m = Integer.parseInt(str[1]);

            Graph graph = new Graph(n, m);

            for (int i = 0; i < m; i++) {
                str = br.readLine().split(" ");
                graph.insert(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
            }

            pw.append("Case #" + t + ": " + graph.computeConnectedComponents() + "\n");
        }
        pw.flush();
    }
}


class Graph {
    int[] component;
    TreeMap<Integer, Integer> componentSize;
    int size;
    int bE;

    ArrayList<Edge> adjList[];

    public Graph(int n, int bE) {
        adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
        componentSize = new TreeMap<>();
        this.size = n;
        this.bE = bE;
    }


    void insert(int s, int d) {
        s--;
        d--;
        Edge e = new Edge(s, d);
        adjList[s].add(e);
        adjList[d].add(e);
    }


    long computeConnectedComponents() {
        int[] component = new int[size];
        int ci = 1;

        for (int i = 0; i < this.size; i++) {
            if (component[i] == 0) {
                computeComponet(i, ci, component);
                ci++;
            }
        }

        long totalWeight = 0;

        for (int i = 0; i < component.length; i++) {
            if (!componentSize.containsKey(component[i])) {
                componentSize.put(component[i], 0);
            }
            componentSize.put(component[i], componentSize.get(component[i]) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : componentSize.entrySet()) {
            totalWeight += (entry.getValue() - 1);
        }

        totalWeight += 2 * (componentSize.size() - 1);

        return totalWeight;

    }

    //beaware of stackoverflow
    private void computeComponet(int node, int ci, int[] component) {
        component[node] = ci;

        for (Edge e : adjList[node]) {
            int neighN = (e.destinatopn == node) ? e.source : e.destinatopn;
            if (component[neighN] == 0) {
                computeComponet(neighN, ci, component);
            }
        }

    }


    void print() {
        for (int i = 0; i < adjList.length; i++) {
            System.out.print(i + "\t");
            for (Edge e : adjList[i]) {
                System.out.println(e);
            }
        }
    }
}


class Edge implements Comparable<Edge> {
    int source;
    int destinatopn;

    @java.beans.ConstructorProperties({"source", "destinatopn"})
    public Edge(int source, int destinatopn) {
        this.source = source;
        this.destinatopn = destinatopn;
    }

    @Override
    public int compareTo(Edge o) {
        if (this.source == o.source && this.destinatopn == o.destinatopn) {
            return 0;
        }

        if (this.destinatopn == o.source && this.source == o.destinatopn) {
            return 0;
        }

        return 1;
    }

    public String toString() {
        return "Edge(source=" + this.source + ", destinatopn=" + this.destinatopn + ")";
    }
}
