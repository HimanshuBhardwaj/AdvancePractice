package com.himanshu.practice.nov.nov13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by himanshubhardwaj on 13/11/19.
 * Statemnt: https://codeforces.com/contest/436/problem/A
 * Submission: https://codeforces.com/contest/436/submission/64846572
 * Algo: Greedy, Clone
 */
public class FeedWithCandy {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int x = Integer.parseInt(str[1]);

        ArrayList<Candies> treeSet[] = new ArrayList[2];
        treeSet[0] = new ArrayList<>();
        treeSet[1] = new ArrayList<>();


        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            int t = Integer.parseInt(str[0]);
            treeSet[t].add(new Candies(i, Integer.parseInt(str[1]), Integer.parseInt(str[2])));
        }

//        System.out.println(treeSet[0]);
//        System.out.println(treeSet[1]);
//        System.out.println();
//        System.out.println();

        int chance0 = maxToffies(0, treeSet, x);
        //int chance1 = 0;
        int chance1 = maxToffies(1, treeSet, x);
        System.out.print(Math.max(chance0, chance1));
    }

    private static int maxToffies(int initial, ArrayList<Candies>[] treeSet, long x) {
        treeSet = createCopy(treeSet);
        Candies candies = null;
        int pos = -1;
        boolean flag = false;
        int count = 0;

        while (!flag || candies != null) {
            flag = true;
            candies = null;

            for (int i = 0; i < treeSet[initial].size(); i++) {
                if (treeSet[initial].get(i).taken) {
                    continue;
                }

                if (treeSet[initial].get(i).height <= x) {
                    if (candies == null) {
                        candies = treeSet[initial].get(i);
                        pos = i;
                    } else if (candies.weight < treeSet[initial].get(i).weight) {
                        candies = treeSet[initial].get(i);
                        pos = i;
                    }
                }
            }

            if (candies == null) {
                return count;
            } else {
                count++;
                treeSet[initial].get(pos).taken = true;
                initial = (initial + 1) % 2;
                x = x + candies.weight;
            }
        }

        return count;
    }

    private static ArrayList<Candies>[] createCopy(ArrayList<Candies>[] treeSet) {
        ArrayList<Candies>[] treeSetCopy = new ArrayList[treeSet.length];

        for (int i = 0; i < treeSet.length; i++) {
            treeSetCopy[i] = getClone(treeSet[i]);
        }


        return treeSetCopy;
    }

    private static ArrayList<Candies> getClone(ArrayList<Candies> candies) {
        ArrayList<Candies> clone = new ArrayList<>();
        for (Candies c : candies) {
            clone.add(new Candies(c));
        }

        return clone;
    }


}


class Candies implements Comparable<Candies> {
    int index;
    long height;
    long weight;
    boolean taken = false;

    @java.beans.ConstructorProperties({"index", "height", "weight"})
    public Candies(int index, int height, int weight) {
        this.index = index;
        this.height = height;
        this.weight = weight;
    }

    public Candies(Candies c) {
        this.index = c.index;
        this.height = c.height;
        this.weight = c.weight;
        this.taken = c.taken;
    }

    @Override
    public int compareTo(Candies o) {
        if (this.height != o.height) {
            return Long.compare(this.height, o.height);
        } else if (this.weight != o.weight) {
            return Long.compare(o.weight, this.weight);
        } else {
            return Integer.compare(this.index, o.index);
        }
    }

    public String toString() {
        return "Candies(index=" + this.index + ", height=" + this.height + ", weight=" + this.weight + ")";
    }
}