package com.himanshu.practice.nov.nov17;

import lombok.ToString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 17/11/19.
 */
public class HarmoniousGraph {
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
        System.out.print(graph.numBerOFNodesRequired());


    }
}


class Graph {
    ArrayList<Integer>[] adjList;
    int size;
    int cluster[];

    public Graph(int n) {
        this.size = n;
        adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
        cluster = new int[n];
    }

    public void insert(int s, int d) {
        s--;
        d--;
        adjList[s].add(d);
        adjList[d].add(s);
    }

    private ArrayList<Cluster> DFS() {
        for (int i = 0; i < cluster.length; i++) {
            cluster[i] = -1;
        }

        int clusterNum = 0;

        for (int i = 0; i < cluster.length; i++) {
            if (cluster[i] == -1) {
                DFSHelper(i, -1, clusterNum++, cluster);
            }
        }

        ArrayList<Cluster> clusters = new ArrayList<>(clusterNum);

        for (int i = 0; i < clusterNum; i++) {
            clusters.add(new Cluster(i));
        }

        for (int i = 0; i < cluster.length; i++) {
            clusters.get(cluster[i]).nodes.add(i);
        }
        return clusters;
    }

    private void DFSHelper(int node, int parent, int clusterNum, int[] cluster) {
        cluster[node] = clusterNum;
        for (int neighbour : adjList[node]) {
            if (neighbour != parent && (cluster[neighbour] == -1)) {
                DFSHelper(neighbour, node, clusterNum, cluster);
            }
        }
    }

    int numBerOFNodesRequired() {
        ArrayList<Cluster> clusters = DFS();
        int count = 0;

        for (int i = 0; i < clusters.size(); i++) {
            Cluster c = clusters.get(i);
            if ((c.nodes.last() - c.nodes.first() + 1) == c.nodes.size()) {
                continue;
            }

            HashSet<Integer> addedCluster = new HashSet<>();
            for (int n = c.nodes.first(); n <= c.nodes.last(); n++) {
                if (!c.nodes.contains(n) && !addedCluster.contains(cluster[n])) {
                    addedCluster.add(cluster[n]);
                    clusters.get(cluster[n]).nodes.addAll(c.nodes);
                    count++;
                }
            }
        }
        return count;
    }

    public void print() {
        System.out.println("Printing Graph");

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

@ToString
class Cluster {
    TreeSet<Integer> nodes;
    public int index;

    public Cluster(int i) {
        index = i;
        nodes = new TreeSet<>();
    }

}