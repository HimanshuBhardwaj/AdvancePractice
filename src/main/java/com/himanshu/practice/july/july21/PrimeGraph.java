package com.himanshu.practice.july.july21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 21/07/19.
 * with PW: 109 MS
 * with sout: 124 MS
 * Stateent: https://codeforces.com/contest/1178/problem/D
 * Algo: Graph Construction
 * Submission: https://codeforces.com/contest/1178/submission/57469165
 */
public class PrimeGraph {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        TreeSet<Integer> primes = getPrimes(2 * n + 1);
//        System.out.println(primes);

        Graph cyclicGraph = GrphUtils.createCyclincGraph(n);


        if (primes.contains(n)) {
            cyclicGraph.print(n);
        } else {
            int ceil = primes.ceiling(n + 1);
            int remainingEdges = ceil - n;
            GrphUtils.addremainingEdges(cyclicGraph, remainingEdges);
            cyclicGraph.print(ceil);
        }


    }

    private static TreeSet<Integer> getPrimes(int num) {
        TreeSet<Integer> primes = new TreeSet<>();
        primes.add(2);
        primes.add(3);
        primes.add(5);

        for (int i = 7; i <= num; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
        return primes;
    }

    private static boolean isPrime(int num) {
        int limit = (int) Math.sqrt(num) + 1;

        for (int i = 2; i <= limit; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}

class GrphUtils {
    public static Graph createCyclincGraph(int n) {
        Graph graph = new Graph(n);

        for (int i = 0; i < n; i++) {
            graph.insert(i, (i + 1) % n);
        }

//        graph.print(n);
//        System.out.println("---");
//        System.out.println("---");

        return graph;
    }

    public static void addremainingEdges(Graph cyclicGraph, int remainingEdges) {
        int n = cyclicGraph.n;
        int start = 0;
        int end = n / 2 + start;

        for (int i = 0; i < remainingEdges; i++) {
            cyclicGraph.insert(start, end);
            start++;
            end = n / 2 + start;
        }
    }
}


class Graph {
    ArrayList<Integer>[] adjList;
    int n;

    public Graph(int n) {
        adjList = new ArrayList[n];
        this.n = n;
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    public void insert(int s, int d) {
        adjList[s].add(d);
    }

    public void print(int numEdges) {
        System.out.println(numEdges);

        for (int i = 0; i < n; i++) {
            for (int neighbour : adjList[i]) {
                System.out.println((i + 1) + " " + (neighbour + 1));
            }
        }
    }
}
