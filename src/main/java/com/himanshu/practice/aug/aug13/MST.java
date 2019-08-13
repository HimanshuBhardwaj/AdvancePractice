package com.himanshu.practice.aug.aug13;

import lombok.AllArgsConstructor;
import lombok.ToString;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;



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

        System.out.println("Printing MST" + "\t" + mst.size());
        for (Edge e : mst) {
            System.out.println(e);
        }
    }
}

//2:56
class Graph {
    ArrayList<Edge> edges;
    int[] parent;
    int height[];

    public Graph(int n, int m) {
        edges = new ArrayList<>();
        parent = new int[n];
        height = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            height[i] = 1;
        }
    }

    void insert(int s, int d, int w) {
        edges.add(new Edge(s, d, w));
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
        return this.weight - o.weight;
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