package com.himanshu.practice.oct.oct8;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 08/10/19.
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String[] str = br.readLine().split(" ");
            int h = Integer.parseInt(str[0]);
            int n = Integer.parseInt(str[1]);

            str = br.readLine().split(" ");
            int[] positions = new int[str.length];
            TreeSet<Integer> treeSet = new TreeSet<>();

            for (int i = 0; i < positions.length; i++) {
                treeSet.add(Integer.parseInt(str[i]));
            }

            pw.append(getNumSetps(h, treeSet) + "\n");
        }
        pw.flush();
    }

    private static int getNumSetps(int h, TreeSet<Integer> positions) {
        int currentPos = positions.last();
        int crystalRequired = 0;

        if (positions.size() == 1) {
            return 0;
        }


        while (currentPos > 2 && positions.size() > 0) {
            positions.remove(currentPos);

            Integer nextCliff = positions.lower(currentPos);
            if (nextCliff == null) {
                break;
            }

            if ((currentPos - nextCliff) > 1) {
                currentPos = nextCliff + 1;
            } else {
                Integer nextNextCliff = positions.lower(nextCliff);

                if (nextNextCliff == null) {
                    crystalRequired++;
                    break;
                }

                if ((nextCliff - nextNextCliff) == 1) {
                    positions.remove(nextCliff);
                    currentPos = nextNextCliff;
                } else {
                    positions.remove(nextCliff);
                    currentPos = nextNextCliff + 1;
                    crystalRequired++;
                }
            }


        }


        return crystalRequired;
    }
}
