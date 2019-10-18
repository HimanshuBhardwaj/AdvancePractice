package com.himanshu.practice.oct.oct13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by himanshubhardwaj on 13/10/19.
 * Statement: https://codeforces.com/contest/1244/problem/D
 * Algo: DFS, MAths
 * Submission: https://codeforces.com/contest/1244/problem/D
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

        //tree.print();
        //System.out.println();
        System.out.print(tree.cost());
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
    long minCost = Long.MAX_VALUE;
    int[] minCostColours;

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

    public String cost() {

        for (ArrayList<Integer> x : adjList) {
            if (x.size() > 2) {
                minCost = -1;
                break;
            }
        }


        if (minCost != -1) {
            int[] costColours = new int[numNodes];
            for (int i = 0; i < adjList.length; i++) {
                if (adjList[i].size() == 1) {
                   // System.out.println("Root: " + i);
                    costHelper(i, -1, null, null, costColours);
                    break;
                }
            }
        }


        StringBuilder sb = new StringBuilder();
        sb.append(minCost + "\n");
        if (minCost != -1) {
            for (int x : minCostColours) {
                sb.append((x + 1) + " ");
            }
        }
        return sb.toString();
    }

    private void costHelper(int node, int parent, Integer parentColour, Integer grandParentColour, int[] costColours) {
        if (parentColour == null) {
            for (int i = 0; i < 3; i++) {
                costColours[node] = i;
                for (int neighbour : adjList[node]) {
                    if (neighbour != parent) {
                        costHelper(neighbour, node, i, grandParentColour, costColours);
                    }
                }
            }
            return;
        }

        if (grandParentColour == null) {
            for (int i = 2; i >= 0; i--) {
                if (i != parentColour) {
                    costColours[node] = i;

                    for (int neighbour : adjList[node]) {
                        if (neighbour != parent) {
                            costHelper(neighbour, node, i, parentColour, costColours);
                            long tempCost = computeCost(costColours);
                            if (tempCost < this.minCost) {
                                minCost = tempCost;
                                minCostColours = costColours.clone();
                            }
                        }
                    }
                }
            }

            return;
        }

        costColours[node] = 3 - parentColour - grandParentColour;
        for (int neighbour : adjList[node]) {
            if (neighbour != parent) {
                costHelper(neighbour, node, costColours[node], parentColour, costColours);
            }
        }
    }

    private long computeCost(int[] costColours) {
        long temoCost = 0l;

        for (int i = 0; i < costColours.length; i++) {
            if (costColours[i] == 0) {
                temoCost += cost1[i];
            } else if (costColours[i] == 1) {
                temoCost += cost2[i];
            } else if (costColours[i] == 2) {
                temoCost += cost3[i];
            } else {
                System.out.println("Something is wrong");
            }
        }
//
//        for (int x : costColours) {
//            System.out.print(x + " ");
//        }
//        System.out.println(":\t\t" + temoCost);

        return temoCost;
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
