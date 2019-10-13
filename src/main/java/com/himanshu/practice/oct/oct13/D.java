package com.himanshu.practice.oct.oct13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by himanshubhardwaj on 13/10/19.
 */
public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Tree tree = new Tree(n);
        String[] str = br.readLine().split(" ");
        tree.initialiseCost1(str);
        str = br.readLine().split(" ");
        tree.initialiseCost2(str);
        str = br.readLine().split(" ");
        tree.initialiseCost3(str);

        for (int i = 0; i < (n - 1); i++) {
            str = br.readLine().split(" ");
            tree.insert(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
        }

        tree.print();
        System.out.println();
        System.out.println(tree.costHelper());
    }
}


class Tree {
    int parent[];
    ArrayList<Integer>[] adjList;
    int numNodes;
    long[] cost1;
    long[] cost2;
    long[] cost3;
    long dp[][];

    public Tree(int n) {
        parent = new int[n];
        adjList = new ArrayList[n];
        numNodes = n;
        cost1 = new long[n];
        cost2 = new long[n];
        cost3 = new long[n];
        dp = new long[n][3];

        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
    }


    void insert(int s, int d) {
        s--;
        d--;
        adjList[s].add(d);
        adjList[d].add(s);
    }


    void initialiseCost1(String[] str) {
        for (int i = 0; i < str.length; i++) {
            cost1[i] = Long.parseLong(str[i]);
        }
    }

    void initialiseCost2(String[] str) {
        for (int i = 0; i < str.length; i++) {
            cost2[i] = Long.parseLong(str[i]);
        }
    }

    void initialiseCost3(String[] str) {
        for (int i = 0; i < str.length; i++) {
            cost3[i] = Long.parseLong(str[i]);
        }
    }


    long costHelper() {
        for (int i = 0; i < numNodes; i++) {
            if (adjList[i].size() >= 3) {
                return -1;
            }
        }
        return cost(0, -1);
    }


    private long cost(int node, int parent) {
        for (int child : adjList[node]) {
            if (child != parent) {
                cost(child, node);
            }
        }

        if (parent != -1) {
            //leaf
            if (adjList[node].size() == 1) {
                dp[node][0] = cost1[node];
                dp[node][1] = cost2[node];
                dp[node][2] = cost3[node];
                return Math.min(Math.min(dp[node][0], dp[node][1]), dp[node][2]);
            }
        }


        long sum0 = cost1[node];
        long sum1 = cost2[node];
        long sum2 = cost3[node];

        for (int child : adjList[node]) {
            if (child != parent) {
                if (adjList[child].size() > 1) {
                    for (int grandChild : adjList[child]) {
                        if (grandChild != node) {
                            sum0 += Math.min(dp[child][1] + dp[grandChild][2], dp[child][2] + dp[grandChild][1]);
                            sum1 += Math.min(dp[child][0] + dp[grandChild][2], dp[child][2] + dp[grandChild][0]);
                            sum2 += Math.min(dp[child][0] + dp[grandChild][1], dp[child][1] + dp[grandChild][0]);
                        }
                    }
                } else {//leaf child
                    sum0 += Math.min(dp[child][1], dp[child][2]);
                    sum1 += Math.min(dp[child][0], dp[child][2]);
                    sum2 += Math.min(dp[child][0], dp[child][1]);
                }
            }
        }

        dp[node][0] = sum0;
        dp[node][1] = sum1;
        dp[node][2] = sum2;


        return Math.min(Math.min(dp[node][0], dp[node][1]), dp[node][2]);
    }

    void print() {
        for (int i = 0; i < numNodes; i++) {
            System.out.print(i + ": ");
            for (int neighbour : adjList[i]) {
                System.out.print(neighbour + ",");
            }
            System.out.println();
        }
    }
}
