package com.himanshu.practice.oct.oct12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 12/10/19.
 */
public class KGame {
    public static void main(String[] args) throws IOException {
        TreeMap<Integer, TreeSet<String>> treeSet = new TreeMap<>();
        treeSet.put(0, new TreeSet<String>());
        treeSet.put(1, new TreeSet<String>());
        treeSet.put(2, new TreeSet<String>());
        treeSet.put(3, new TreeSet<String>());


        for (int i = 21; i <= 21; i++) {
            for (int j = 6; j <= 6; j++) {
                if (Simulate(i, j, i, 0) == 0) {
                    treeSet.get(findPos(i, j)).add("Alice");
                    if (findPos(i, j) == 3) {
                        System.out.println(i + "\t" + j + ":\t" + "Alice");
                    }
                } else {
                    treeSet.get(findPos(i, j)).add("Bob");
                    if (findPos(i, j) == 3) {
                        System.out.println(i + "\t" + j + ":\t" + "Bob");
                    }
                }


            }
        }

        System.out.println(treeSet);

        if (args != null) {
            return;
        }


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
//
        while (t-- > 0) {
            String str[] = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int k = Integer.parseInt(str[1]);

            if (n == 0) {
                System.out.println("Bob");
                continue;
            }


            if (n < 2 || k == n) {
                System.out.println("Alice");
                continue;
            }

            int pos = findPos(n, k);
            if (k > n) {
                switch (pos) {
                    case 0:
                        System.out.println("Alice");
                        break;
                    case 1:
                        System.out.println("Alice");
                        break;
                    case 2:
                        System.out.println("Bob");
                        break;
                    case 3:
                        System.out.println("Bob");
                        break;
                }
            } else {
                switch (pos) {
                    case 0:
                        System.out.println("Alice");
                        break;
                    case 1:
                        System.out.println("Alice");
                        break;
                    case 2:
                        System.out.println("Bob");
                        break;
                    case 3:
                        System.out.println("Bob");
                        break;
                }

            }

        }
    }


    static int findPos(int n, int k) {
        int fb = (n % 3 == 0) ? 1 : 0;
        int sb = (k % 3 == 0) ? 1 : 0;

        if (fb == 1) {
            return 2 + sb;
        } else {
            return sb;
        }
    }


    //turn==0 --> Alice  else Bob
    static int Simulate(int n, int k, int pos, int turn) {
        if (pos < 0) {
            return -1;
        }


        if (pos == 0) {
            return (turn + 1) % 2;
        }
        if (pos == 1 || pos == 2 || (pos - k) == 0) {
            return turn;
        }

        int a = -1, b = -1, c = -1;


        a = Simulate(n, k, pos - 1, (turn + 1) % 2);
        b = Simulate(n, k, pos - 2, (turn + 1) % 2);
        c = Simulate(n, k, pos - k, (turn + 1) % 2);

        if ((a == turn) || b == turn || c == turn) {
            return turn;
        } else {
            return (turn + 1) % 2;
        }
    }
}
