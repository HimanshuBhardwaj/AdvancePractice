package com.himanshu.practice.oct.oct15;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");
        Tree tree = new Tree(n);


        tree.insertCost(str, 0);
        str = br.readLine().split(" ");
        tree.insertCost(str, 1);
        str = br.readLine().split(" ");
        tree.insertCost(str, 2);

        for (int i = 0; i < (n - 1); i++) {
            str = br.readLine().split(" ");
            int s = Integer.parseInt(str[0]);
            int d = Integer.parseInt(str[1]);
            tree.insert(s, d);
        }

        //tree.print();
        //System.out.println();
        //System.out.println();
        long cost = tree.computeScore();
        PrintWriter pw = new PrintWriter(System.out);
        pw.append(cost + "\n");
        if (cost != -1) {
            int[] costs = tree.computePath(cost);
            for (int x : costs) {
                pw.append(x + " ");
            }
        }
        pw.flush();
        pw.close();


    }
}


class Tree {
    ArrayList<Integer>[] adjList;
    long cost[][];
    int numNodes;
    int start;
    int mapping[];
    int previous[];
    long dp[][];


    public Tree(int n) {
        adjList = new ArrayList[n];
        this.numNodes = n;
        cost = new long[n][3];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
        mapping = new int[n];
        previous = new int[n];
        dp = new long[n][3];

    }

    void insertCost(String[] c, int pos) {
        for (int i = 0; i < numNodes; i++) {
            cost[i][pos] = Long.parseLong(c[i]);
        }
    }

    public void insert(int s, int d) {
        adjList[--s].add(--d);
        adjList[d].add(s);
    }

    public void computeStart() {
        for (int i = 0; i < numNodes; i++) {
            if (adjList[i].size() == 1) {
                start = i;
                break;
            }
        }
    }

    //this will compute mapping from index to node index
    public void computeMapping() {
        computeStart();
        int currentNode = start;
        int parent = -1;
        int pos = 0;
        previous[currentNode] = parent;
        mapping[pos] = start;

        while (pos < numNodes && (parent == -1 || adjList[currentNode].size() > 1)) {
            int tempC = currentNode;
            //new Current Node

            currentNode = (adjList[currentNode].get(0) != parent) ? adjList[currentNode].get(0) : adjList[currentNode].get(1);
            parent = tempC;
            mapping[pos + 1] = currentNode;
            previous[currentNode] = parent;
            pos++;
        }
    }


    long computeScore() {
        for (int i = 0; i < numNodes; i++) {
            if (adjList[i].size() > 2) {
                return -1;
            }
        }

        computeMapping();
        dp[mapping[0]][0] = cost[mapping[0]][0];
        dp[mapping[0]][1] = cost[mapping[0]][1];
        dp[mapping[0]][2] = cost[mapping[0]][2];

        for (int i = 1; i < numNodes; i++) {
            dp[mapping[i]][0] = Math.min(dp[previous[mapping[i]]][1], dp[previous[mapping[i]]][2]) + cost[mapping[i]][0];
            dp[mapping[i]][1] = Math.min(dp[previous[mapping[i]]][0], dp[previous[mapping[i]]][2]) + cost[mapping[i]][1];
            dp[mapping[i]][2] = Math.min(dp[previous[mapping[i]]][0], dp[previous[mapping[i]]][1]) + cost[mapping[i]][2];
        }

        return Math.min(Math.min(dp[mapping[numNodes - 1]][0], dp[mapping[numNodes - 1]][1]), dp[mapping[numNodes - 1]][2]);
    }


    int[] computePath(long c) {
        int last = mapping[numNodes - 1];
        int previousPaint = -1;
        Stack<Integer> stack = new Stack<>();
        int colours[] = new int[numNodes];

        //System.out.println(c+"\tCost");
        while (last != -1) {
            // System.out.println(dp[last][0] + "\t" + dp[last][1] + "\t" + dp[last][2] + "\t\t" + cost[last][0] + "\t" + cost[last][1] + "\t" + cost[last][2] + "\t\t" + c);
            if (previousPaint != 1 && dp[last][0] == c) {
                colours[last] = 1;
                c -= this.cost[last][0];
                last = previous[last];
                previousPaint = 1;
            } else if (previousPaint != 2 && dp[last][1] == c) {
                colours[last] = 2;
                c -= this.cost[last][1];
                last = previous[last];
                previousPaint = 2;
            } else {
                colours[last] = 3;
                c -= this.cost[last][2];
                last = previous[last];
            }
        }
        return colours;
    }

    void print() {
        for (int i = 0; i < numNodes; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < adjList[i].size(); j++) {
                System.out.print(adjList[i].get(j) + ", ");
            }
            System.out.println();
        }
    }

}
