package com.himanshu.practice.nov.nov15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Queue;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 15/11/19.
 * Statement:https://codeforces.com/contest/436/problem/C
 * Submission: https://codeforces.com/contest/436/submission/65108233
 * Algo: Prism
 * Node: Kurashkal will not work here
 */
public class DungeonsAndCandies {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        int k = Integer.parseInt(str[2]);
        int w = Integer.parseInt(str[3]);

        Graph graph = new Graph(k, w);
        ArrayList<State> states = new ArrayList<>();
        char[][] chars = new char[n][m];

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n; j++) {
                chars[j] = br.readLine().toCharArray();
            }
            State state = new State(i + 1);
            state.setChars(chars);
            states.add(state);
        }

        graph.setEdges(states);
//        graph.print();
        graph.computeMST();
    }

}

class Graph {
    ArrayList<Edge> adjList[];
    long w;
    int n;
    Queue<Edge> order;

    public Graph(int n, int w) {
        adjList = new ArrayList[n + 1];

        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = new ArrayList<>();
        }
        this.w = w;
        this.n = n;
    }

    void setEdge(int s, int d, int w) {
        Edge e = new Edge(s, d, w);
        adjList[s].add(e);
        adjList[d].add(e);
    }

    void setEdges(ArrayList<State> states) {
        for (State s : states) {
            Edge e = new Edge(0, s.index, s.chars.length * (long) s.chars[0].length);
            adjList[0].add(e);
            adjList[s.index].add(e);
        }

        for (int i = 0; i < states.size(); i++) {
            for (int j = i + 1; j < states.size(); j++) {
                Edge e = new Edge(states.get(i).index, states.get(j).index, states.get(i).cost(states.get(j), w));
                adjList[states.get(i).index].add(e);
                adjList[states.get(j).index].add(e);
            }
        }
    }


    public void computeMST() {
        Node[] nodes = new Node[n + 1];
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i <= n; i++) {
            nodes[i] = new Node(i, 0, Long.MAX_VALUE);
        }

        nodes[0].weight = 0l;
        long tw = 0l;
        int count = 0;
        boolean[] visited = new boolean[n + 1];
        TreeSet<Node> treeSet = new TreeSet<>();
        treeSet.add(nodes[0]);
        visited[0] = true;
        boolean flag = false;

        while ((count <= n) && treeSet.isEmpty() == false) {
            Node nearest = treeSet.first();
            treeSet.remove(nearest);
            tw += nearest.weight;
            if (flag) {
                sb.append(nearest.index + " " + nearest.parent + "\n");
            } else {
                flag = true;
            }
            count++;
            visited[nearest.index] = true;
            for (Edge e : adjList[nearest.index]) {
                int neighbourNode = (e.s == nearest.index) ? e.d : e.s;
                if (!visited[neighbourNode] && nodes[neighbourNode].weight > e.weight) {
                    Node nn = nodes[neighbourNode];
                    treeSet.remove(nn);
                    nn.parent = nearest.index;
                    nn.weight = e.weight;
                    treeSet.add(nn);
                    //
                }
            }
        }


        System.out.println(tw);
        System.out.print(sb.toString());
    }


    static class Node implements Comparable<Node> {
        int index;
        int parent;
        long weight;

        @java.beans.ConstructorProperties({"index", "parent", "weight"})
        public Node(int index, int parent, long weight) {
            this.index = index;
            this.parent = parent;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            if (Double.compare(this.weight, o.weight) != 0) {
                return Long.compare(this.weight, o.weight);
            } else {
                return Integer.compare(this.index, o.index);
            }
        }

        public String toString() {
            return "Graph.Node(index=" + this.index + ", parent=" + this.parent + ", weight=" + this.weight + ")";
        }
    }


    static class Edge implements Comparable<Edge> {
        int s;
        int d;
        long weight;

        @java.beans.ConstructorProperties({"s", "d", "weight"})
        public Edge(int s, int e, long weight) {
            this.s = s;
            this.d = e;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.weight, o.weight);
        }
    }


    void print() {
        for (int i = 0; i < adjList.length; i++) {
            System.out.print(i + ": ");
            for (Edge e : adjList[i]) {
                System.out.print("(" + ((e.d == i) ? e.s : e.d) + "," + e.weight + ")");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println();
        System.out.println();
    }
}


class State {
    int index;
    char[][] chars;

    public State(int index) {
        this.index = index;
    }

    public void setChars(char[][] chars) {
        this.chars = chars.clone();
    }


    public long cost(State s, long w) {
        return chostHelper(chars, s.chars) * w;
    }

    private int chostHelper(char[][] chars, char[][] chars1) {
        int count = 0;

        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[0].length; j++) {
                if (chars[i][j] != chars1[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }
}
