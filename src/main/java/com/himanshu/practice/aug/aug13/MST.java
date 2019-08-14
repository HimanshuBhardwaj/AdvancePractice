package com.himanshu.practice.aug.aug13;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;


public class MST {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        Graph graph = new Graph(n, m);

        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            int s = Integer.parseInt(str[0]);
            int d = Integer.parseInt(str[1]);
            int w = Integer.parseInt(str[2]);
            graph.insert(s, d, w);
        }

        ArrayList<Edge> mst = graph.kurashkalMST();

        System.out.println("Printing MST Kurashkal" + "\t" + mst.size());
        for (Edge e : mst) {
            System.out.println(e);
        }

//        mst = graph.prismMST();
//        System.out.println();
//        System.out.println("Printing MST Prism" + "\t" + mst.size());
//        for (Edge e : mst) {
//            System.out.println(e);
//        }
    }
}

//2:56
class Graph {
    ArrayList<Edge> edges;
    int[] parent;
    int height[];
    ArrayList<Edge> adjList[];

    public Graph(int n, int m) {
        edges = new ArrayList<>();
        parent = new int[n];
        height = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            height[i] = 1;
        }

        adjList = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    void insert(int s, int d, int w) {
        Edge e = new Edge(s, d, w);
        edges.add(e);
        adjList[s].add(e);
        adjList[d].add(e);
    }


    void print() {
        for (Edge e : edges) {
            System.out.println(e);
        }
    }

    ArrayList<Edge> kurashkalMST() {
        ArrayList<Edge> arrayListCopy = (ArrayList<Edge>) edges.clone();
        Collections.sort(arrayListCopy);
        ArrayList<Edge> mst = new ArrayList<>();

        for (Edge e : arrayListCopy) {
            if (!belongToSameCluster(e)) {
                mst.add(e);
                mergeCluster(mst, e);
            }
        }
        return mst;
    }


    ArrayList<Edge> prismMST() {
        ArrayList<Edge> mst = new ArrayList<>();
        TreeSet<Edge> edgeSet = new TreeSet<>();
        TreeSet<Integer> coveredNodes = new TreeSet<>();


        for (Edge e : adjList[0]) {
            edgeSet.add(e);
        }


        while (!edgeSet.isEmpty()) {
            Edge e = edgeSet.first();
            edgeSet.remove(e);

            if (coveredNodes.contains(e.destinatopn) && coveredNodes.contains(e.source)) {
                continue;
            } else {
                coveredNodes.add(e.source);
                coveredNodes.add(e.destinatopn);
                mst.add(e);
                System.out.println(mst.size());


                for (Edge ee : adjList[e.destinatopn]) {
                    if (!coveredNodes.contains(ee.source)) {
                        edgeSet.add(ee);
                    }

                }

                for (Edge ee : adjList[e.source]) {
                    if (!coveredNodes.contains(ee.destinatopn)) {
                        edgeSet.add(ee);
                    }

                }


            }
        }

        return mst;
    }


    private int getHeight(int n) {
        if (parent[n] == n) {
            return height[n];
        } else {
            height[n] = getParent(parent[n]);
            return height[n];
        }
    }

    //heavy light merge
    private void mergeCluster(ArrayList<Edge> mst, Edge e) {
        int pS = getParent(e.source);
        int pD = getParent(e.destinatopn);

        if (height[pS] > height[pD]) {
            parent[pD] = pS;
        } else if (height[pS] < height[pD]) {
            parent[pS] = pD;
        } else {
            parent[pS] = pD;
            height[pD] = 1 + height[pD];
        }
    }

    private boolean belongToSameCluster(Edge e) {
        return (getParent(e.source) == getParent(e.destinatopn));
    }


    int getParent(int n) {
        if (parent[n] == n) {
            return n;
        } else {
            return getParent(parent[n]);
        }
    }

}


@ToString
@AllArgsConstructor
class Edge implements Comparable<Edge> {
    int source;
    int destinatopn;
    int weight;

    @Override
    public int compareTo(Edge o) {
        if (this.weight != o.weight) {
            return this.weight - o.weight;
        } else {
            if (this.source == o.source && this.destinatopn == o.destinatopn) {
                return 0;
            }

            if (this.destinatopn == o.source && this.source == o.destinatopn) {
                return 0;
            }

            return 1;
        }
    }
}
/*

5 7
0 1 1
0 2 1
2 1 2
1 3 5
2 3 3
1 4 7
3 4 9


*
* */