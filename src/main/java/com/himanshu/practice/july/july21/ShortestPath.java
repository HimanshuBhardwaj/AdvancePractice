package com.himanshu.practice.july.july21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by himanshubhardwaj on 21/07/19.
 * 8:50
 */
public class ShortestPath {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");

        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        int k = Integer.parseInt(str[2]);


        SGraph graph = new SGraph(n);

        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            int s = Integer.parseInt(str[0]);
            int d = Integer.parseInt(str[1]);
            graph.insert(s, d);
        }

        for (int i = 0; i < k; i++) {
            str = br.readLine().split(" ");
            int a = Integer.parseInt(str[0]);
            int b = Integer.parseInt(str[1]);
            int c = Integer.parseInt(str[2]);
            graph.insertForbiddenTriples(a, b, c);
        }

        //graph.BFS(0, graph.shortesPAthFromSource);
        // graph.BFS(n - 1, graph.shortestPathFromDestination);
        //System.out.println();
        graph.printGraph();
        int[] sd = new int[n];


        System.out.println(graph.BFS(0, sd));
        for (int i = 0; i < n; i++) {
            System.out.println(i + "\t" + sd[i]);
        }


    }
}


class SGraph {
    ArrayList<Integer>[] adjList;
    int shortesPAthFromSource[];
    int shortestPathFromDestination[];
    int n;
    HashSet<String> notAllowedTriples;
    int parent[];


    public SGraph(int n) {
        this.n = n;
        adjList = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
        shortesPAthFromSource = new int[n];
        shortestPathFromDestination = new int[n];
        notAllowedTriples = new HashSet<>();
        parent = new int[n];
    }

    public void insert(int x, int y) {
        x--;
        y--;
        adjList[x].add(y);
        adjList[y].add(x);
    }

    public void insertForbiddenTriples(int a, int b, int c) {
        a--;
        b--;
        c--;
        notAllowedTriples.add(serialiseTriple(a, b, c));
    }

    private String serialiseTriple(int a, int b, int c) {
        return a + "," + b + "," + c;
    }


    public int BFS(int source, int[] shortDistanceArray) {
        for (int i = 0; i < shortDistanceArray.length; i++) {
            shortDistanceArray[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < n; i++) {
            parent[i] = -1;
        }

        parent[source] = source;
        shortDistanceArray[source] = 0;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        visited[source] = true;
        int level = 1;
        queue.add(source);

        while (!queue.isEmpty()) {
            LinkedList<Integer> linkedList = new LinkedList<>(queue);
            queue = new LinkedList<>();

            for (int x : linkedList) {
                for (int neighbour : adjList[x]) {
                    if (!visited[neighbour]) {
                        visited[neighbour] = true;
                        queue.add(neighbour);
                        shortDistanceArray[neighbour] = level;
                        parent[neighbour] = x;
                    }
                }
            }
            level++;
        }

        return (shortDistanceArray[n - 1] == Integer.MAX_VALUE) ? -1 : shortDistanceArray[n - 1];
    }


    public int shortestDistance() {
        int shortestDistance = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            ArrayList<Integer> neighbour = adjList[i];
            for (int j = 0; j < neighbour.size(); j++) {
                for (int k = 0; k < neighbour.size(); k++) {
                    int middle = i;
                    int left = neighbour.get(j);
                    int right = neighbour.get(k);
                    if (!notAllowedTriples.contains(serialiseTriple(j, i, k))) {
                        if ((shortesPAthFromSource[left] != Integer.MAX_VALUE) && (shortestPathFromDestination[right] != Integer.MAX_VALUE)) {
                            shortestDistance = Math.min((2 + shortesPAthFromSource[left] + shortestPathFromDestination[right]), shortestDistance);
                        }
                    }
                }
            }
            System.out.println(i + ": " + shortestDistance);
        }

        return shortestDistance;
    }

    public void printGraph() {
        for (int i = 0; i < n; i++) {
            System.out.print(i + ": ");
            for (int n : adjList[i]) {
                System.out.print(n + ",");
            }
            System.out.println();
        }
    }


}
