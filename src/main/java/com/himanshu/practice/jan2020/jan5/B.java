package com.himanshu.practice.jan2020.jan5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * @author Himanshu Bhardwaj
 * Date 04/Jan/2020
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Seq seq[] = new Seq[n];
        TreeSet<Seq> treeSet = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split(" ");
            seq[i] = new Seq(i);

            for (int j = 1; j < str.length; j++) {
                int num = Integer.parseInt(str[j]);
                if (num > seq[i].max) {
                    seq[i].max = num;
                }

                if (num < seq[i].min) {
                    seq[i].min = num;
                }

                seq[i].integers.add(num);
            }

            treeSet.add(seq[i]);
        }

        int r = n;
        int rsA = 0;
        for (Seq s : treeSet) {
            s.rank = r;
            r--;
            int max = s.integers.get(s.integers.size() - 1);
            boolean flag = true;
            s.rankSelfAscent = rsA;

            for (int i = s.integers.size() - 2; flag && (i >= 0); i--) {
                if (s.integers.get(i) < max) {
                    rsA++;
                    flag = false;
                    s.isInfected = true;
                }
                max = Math.max(max, s.integers.get(i));
            }
        }

//        System.out.println();
//        for (Seq s : treeSet) {
//            System.out.println(s);
//            System.out.println();
//        }
//        System.out.println();


        long count = 0;


        for (Seq s : treeSet) {
            if (s.isInfected) {
                count+=n;
                continue;
            }
            Seq tempSeq = new Seq(Integer.MAX_VALUE);
            tempSeq.max = s.min;
            Seq higher = treeSet.higher(tempSeq);
            if (higher != null) {
                //count = count + (treeSet.size() - higher.rank) + higher.rankSelfAscent - ((higher.isInfected) ? 1 : 0);
                count = count + higher.rank + (higher.rankSelfAscent);
            } else {
                count += rsA;
            }
        }

        System.out.print(count);


    }
}

class Seq implements Comparable<Seq> {
    long min;
    long max;
    int index;
    int rank;
    int rankSelfAscent;
    ArrayList<Integer> integers = new ArrayList<>();
    boolean isInfected = false;

    public Seq(int i) {
        this.index = i;
        this.min = Integer.MAX_VALUE;
        this.max = Integer.MIN_VALUE;
    }

    @Override
    public int compareTo(Seq o) {
        if (this.max != o.max) {
            return Long.compare(this.max, o.max);
        } else {
            return Integer.compare(this.index, o.index);
        }
    }

    public String toString() {
        return "Seq(min=" + this.min + ", max=" + this.max + ", index=" + this.index + ", rank=" + this.rank + ", rankSelfAscent=" + this.rankSelfAscent + ", integers=" + this.integers + ", isInfected=" + this.isInfected + ")";
    }
}