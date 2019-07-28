package com.himanshu.practice.july.july28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;

/**
 * Created by himanshubhardwaj on 28/07/19.
 */
public class SolutionC {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        String str[];
        PrintWriter pw = new PrintWriter(System.out);

        for (int ca = 1; ca <= t; ca++) {
            str = br.readLine().split(" ");
            int n = Integer.parseInt(str[1]);
            int k = Integer.parseInt(str[0]);
            str = br.readLine().split(" ");
            int[] position = new int[n];

            for (int i = 0; i < n; i++) {
                position[i] = Integer.parseInt(str[i]);
            }

            str = br.readLine().split(" ");


            long baseCost[] = new long[n];

            for (int i = 0; i < n; i++) {
                baseCost[i] = Long.parseLong(str[i]);
            }

            long costIncured = 0;

            long currentMinimum = Long.MAX_VALUE;


            for (int i = 0; i < n; i++) {
                costIncured = baseCost[i];
                PriorityQueue<Stall> priorityQueue = new PriorityQueue<>();
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        priorityQueue.add(new Stall(position[j], Math.abs(position[j] - position[i]) + baseCost[j]));
                    }
                }

                for (int j = 0; j < k; j++) {
                    costIncured += priorityQueue.poll().cost;

                }

                if (costIncured < currentMinimum) {
                    currentMinimum = costIncured;
                }
            }
            pw.append("Case #" + ca + ": " + currentMinimum+"\n");
        }
        pw.flush();

    }
}


class Stall implements Comparable<Stall> {
    int position;
    long cost;

    @java.beans.ConstructorProperties({"position", "cost"})
    public Stall(int position, long cost) {
        this.position = position;
        this.cost = cost;
    }

    @Override
    public int compareTo(Stall o) {
        return (((this.cost - o.cost) > 0) ? 1 : (((this.cost - o.cost) == 0) ? 0 : -1));
    }
}
