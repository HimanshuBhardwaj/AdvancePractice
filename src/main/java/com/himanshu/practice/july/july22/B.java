package com.himanshu.practice.july.july22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 22/07/19.
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String str[] = br.readLine().split(" ");
        TreeSet<Piller> pillers = new TreeSet<>();
        Piller maximumPiller = null;

        for (int i = 0; i < n; i++) {
            int diskZise = Integer.parseInt(str[i]);
            Piller piller = new Piller(i, null, diskZise);
            pillers.add(piller);

            if (maximumPiller == null) {
                maximumPiller = piller;
            } else {
                if (maximumPiller.diskSizes.first() < piller.diskSizes.first()) {
                    maximumPiller = piller;
                }
            }
        }

        pillers.remove(maximumPiller);

        Piller temp = new Piller(0, null, 0);

        while (!pillers.isEmpty()) {
            //System.out.println(pillers);
            temp.index = maximumPiller.index - 1;
            Piller left = pillers.floor(temp);
            temp.index = maximumPiller.index + 1;
            Piller right = pillers.ceiling(temp);
            //System.out.println("LEFT: " + left + "\tRight: " + right);

            if (left == null && right == null) {
                System.out.println("YES");
                return;

            } else if (left != null && right == null) {
                if (left.diskSizes.first() < maximumPiller.diskSizes.first()) {
                    pillers.remove(left);
                    maximumPiller = left;
                } else {
                    System.out.println("NO");
                    return;
                }
            } else if (right != null && left == null) {
                if (right.diskSizes.first() < maximumPiller.diskSizes.first()) {
                    pillers.remove(right);
                    maximumPiller = right;
                } else {
                    System.out.println("NO");
                    return;
                }
            } else {
                if (left.diskSizes.first() < right.diskSizes.first()) {
                    if (right.diskSizes.first() < maximumPiller.diskSizes.first()) {
                        pillers.remove(right);
                        maximumPiller = right;
                    } else {
                        System.out.println("NO");
                        return;
                    }
                } else {
                    if (left.diskSizes.first() < maximumPiller.diskSizes.first()) {
                        pillers.remove(left);
                        maximumPiller = left;
                    } else {
                        System.out.println("NO");
                        return;
                    }
                }
            }
        }

        System.out.println("YES");


    }
}

class Piller implements Comparable<Piller> {
    int index;
    TreeSet<Integer> diskSizes;


    public Piller(int index, TreeSet<Integer> diskSizes, int disk) {
        this.index = index;
        this.diskSizes = new TreeSet<>();
        this.diskSizes.add(disk);
    }

    @Override
    public int compareTo(Piller o) {
        return this.index - o.index;
    }

    public String toString() {
        return "Piller(index=" + this.index + ", diskSizes=" + this.diskSizes.first() + "),\t";
    }
}