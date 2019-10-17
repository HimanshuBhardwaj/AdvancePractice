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

        tree.computeScore();

        //tree.print();
        //System.out.println();
        //System.out.println();
//        long cost = tree.computeScore();
//        PrintWriter pw = new PrintWriter(System.out);
//        pw.append(cost + "\n");
//        if (cost != -1) {
//            int[] costs = tree.computePath(cost);
//            for (int x : costs) {
//                pw.append(x + " ");
//            }
//        }
//        pw.flush();
//        pw.close();


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

    int getNext(int index, int first) {
        int next = -1;
        if (adjList[index].size() == 1) {
            if (index == first) {
                next = adjList[index].get(0);

            } else {
                next = -1;
            }
        } else {
            return (adjList[index].get(0) == index) ? adjList[index].get(1) : adjList[index].get(0);
        }
        return -1;
    }

    int getPrevious(int index, int first) {
        if (index == first) {
            return -1;
        } else {
            return previous[index];
        }
    }


    void computeScore() {
        for (int i = 0; i < numNodes; i++) {
            if (adjList[i].size() > 2) {
                System.out.println(-1);
                return;
            }
        }
        computeMapping();

        if (numNodes == 1) {
            System.out.println();
            Math.min(Math.min(cost[0][0], cost[0][1]), cost[0][2]);
            if (cost[0][0] <= Math.min(cost[0][1], cost[0][2])) {
                System.out.println(1);
            } else if (cost[0][1] <= Math.min(cost[0][0], cost[0][2])) {
                System.out.println(2);
            } else {
                System.out.println(3);
            }
            return;
        }

        int first = start;
        int second = getNext(start, start);
        int[] bestFilledColour = null;
        long cost = Long.MAX_VALUE;

        int filledColours[] = new int[numNodes];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i != j) {
                    filledColours[first] = i;
                    filledColours[second] = j;
                    fillRemaining(filledColours, first, second);
                    for (int k = 0; k < filledColours.length; k++) {
                        System.out.print(filledColours[k] + ",");
                    }
                    System.out.println();
                    long tempCost = computeCost(filledColours);
                    if (tempCost < cost) {
                        cost = tempCost;
                        bestFilledColour = filledColours.clone();
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(cost + "\n");
        for (int x : bestFilledColour) {
            sb.append(x + " ");
        }
        System.out.println(sb.toString());
    }

    private long computeCost(int[] filledColours) {
        int tCost = 0;

        for (int i = 0; i < filledColours.length; i++) {
            tCost += cost[i][filledColours[i]];
        }
        return tCost;
    }

    private void fillRemaining(int[] filledColours, int first, int second) {
        int n = 2;
        int f = first;

        while (n < numNodes) {
            int next = getNext(second, f);
            filledColours[next] = 3 - filledColours[first] - filledColours[second];
            first = second;
            second = next;
            n++;
        }
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
