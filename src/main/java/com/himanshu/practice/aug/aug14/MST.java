package com.himanshu.practice.aug.aug14;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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


        graph.print();

        ArrayList<Edge> mst = graph.mstPrism();

        for (Edge e : mst) {
            System.out.println(e);
        }


    }
}

class Graph {
    ArrayList<Edge>[] adjList;
    int n;
    int m;
    ArrayList<Node> nodes;

    public Graph(int n, int m) {
        adjList = new ArrayList[n];
        this.n = n;
        this.m = m;
        nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
            Node node = new Node();
            node.index = i;
            nodes.add(node);
        }
    }

    //undirected Graph
    void insert(int v1, int v2, int w) {
        Edge e = new Edge(v1, v2, w);
        adjList[v1].add(e);
        adjList[v2].add(e);
    }


    void print() {
        for (int i = 0; i < n; i++) {
            System.out.print(i + ": ");
            for (Edge neighour : adjList[i]) {
                int n = (neighour.v1 == i) ? neighour.v2 : neighour.v1;
                System.out.print(n + ", ");
            }
            System.out.println();
        }
    }


    ArrayList<Edge> mstPrism() {
        ArrayList<Node> nodesC = (ArrayList<Node>) nodes.clone();
        nodesC.get(0).distance = 0;
        TreeSet<Node> prioritySet = new TreeSet<>();
        for (int i = 0; i < nodesC.size(); i++) {
            if (i != 0) {
                nodesC.get(i).distance = Integer.MAX_VALUE;
            }
            prioritySet.add(nodesC.get(i));
        }

        ArrayList<Edge> mst = new ArrayList<>();

        while (!prioritySet.isEmpty()) {
            Node nearest = prioritySet.first();
            prioritySet.remove(prioritySet.first());

            if (nearest.parentEdge != null) {
                mst.add(nearest.parentEdge);
            }

            for (Edge nE : adjList[nearest.index]) {
                Node neighbour = nodesC.get((nE.v1 == nearest.index) ? nE.v2 : nE.v1);

                if (neighbour.distance > nE.weight) {
                    prioritySet.remove(neighbour);
                    neighbour.distance = nE.weight;
                    neighbour.parentEdge = nE;
                    prioritySet.add(neighbour);
                }
            }
        }

        return mst;
    }


}

@AllArgsConstructor
@ToString
class Edge {
    int v1;
    int v2;
    int weight;
}


class Node implements Comparable<Node> {
    int index;
    int distance;
    Edge parentEdge;

    @Override
    public int compareTo(Node o) {
        if (this.distance != o.distance) {
            return this.distance - o.distance;
        } else {
            return this.index - o.index;
        }
    }
}


/*


7 10
0 1 1
0 2 1
1 3 5
1 4 7
2 1 2
2 3 3
3 4 9
4 5 10
4 6 15
3 5 3


 */