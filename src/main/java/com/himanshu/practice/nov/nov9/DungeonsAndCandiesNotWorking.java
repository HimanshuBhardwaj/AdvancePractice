package com.himanshu.practice.nov.nov9;

import com.himanshu.practice.oct.oct6.E;
import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by himanshubhardwaj on 09/11/19.
 * Statement: https://codeforces.com/problemset/problem/436/C
 */

public class DungeonsAndCandiesNotWorking {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        int k = Integer.parseInt(str[2]);
        int w = Integer.parseInt(str[3]);
        ArrayList<State> states = new ArrayList<>();
        char[][] chars = new char[n][m];
        long costA = 0l;
        StringBuffer sb = new StringBuffer();


        for (int j = 0; j < k; j++) {
            for (int i = 0; i < n; i++) {
                chars[i] = br.readLine().toCharArray();
            }
            State state = new State(j, chars);
            state.computePreviousState(states, w);
            costA += state.cost;
            sb.append(state);
            states.add(state);
        }
        pw.append(costA + "\n");
        pw.append(sb.toString());
        pw.flush();
        pw.close();
    }
}


class Graph {
    ArrayList<Edge> graph;
    int s;


    public Graph(int size) {
        graph = new ArrayList<>();
        s = size;
    }

    void insert(int s, int d, long w) {
        Edge edge = new Edge(s, d, w);
        graph.add(edge);
    }


    ArrayList<Edge> getMST() {
        Comparator<Edge> comparator = new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return Long.compare(o1.w, o2.w);
            }
        };
        Collections.sort(graph, comparator);

        ArrayList<Edge> mst = new ArrayList<>();
        int pos = 0;

        while (pos < graph.size() && (mst.size() < (s - 1))) {
            if (!isFormingcycle(mst, graph.get(pos))) {
                mst.add(graph.get(pos));
            }
            pos++;
        }

        return mst;
    }

    private boolean isFormingcycle(ArrayList<Edge> mst, Edge edge) {
        return false;
    }


    @AllArgsConstructor
    static class Edge {
        int s;
        int d;
        long w;
    }


}

class Node {
    int index;
    int parent;
}

class State {
    int index;
    char[][] chars;
    long cost;//default cost
    int previsousState;//state Which will be used to transfer. -1 means transfer exact bytes;
    long numOfbytesDiffFromPreviousState;

    public State(int i, char[][] chars) {
        this.chars = chars.clone();
        this.index = i;
        this.cost = chars.length * chars[0].length;
        previsousState = 0;
    }


    public void computePreviousState(ArrayList<State> states, int w) {
        if (states.size() == 0) {
            return;
        }
        long max = cost;
        int ps = 0;
        long numBDFPS = 0;
        for (int i = 0; i < index; i++) {
            long tempC = costDiff(states.get(i).chars, chars);
            if (tempC < max) {
                max = tempC;
                ps = i + 1;
                numBDFPS = tempC;
            }
        }
        if (ps == 0) {
        } else {
            this.cost = w * max;
            this.previsousState = ps;
            this.numOfbytesDiffFromPreviousState = numBDFPS;
        }
    }

    private long costDiff(char[][] chars, char[][] chars1) {
        long cost = 0;

        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[i].length; j++) {
                if (chars[i][j] != chars1[i][j]) {
                    cost++;
                }
            }
        }

        return cost;
    }

    public String toString() {
        return (index + 1) + " " + previsousState + "\n";
    }
}
