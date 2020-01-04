package com.himanshu.practice.dec.dec31;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Himanshu Bhardwaj
 * Date 31/Dec/2019
 */
public class VasyaAndArrays {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long sum = 0;
        Queue<Integer> aq = new LinkedList<>();
        Queue<Integer> bq = new LinkedList<>();
        String[] str = br.readLine().split(" ");

        for (int i = 0; i < str.length; i++) {
            int num = Integer.parseInt(str[i]);
            aq.add(num);
            sum += num;
        }


        n = Integer.parseInt(br.readLine());
        str = br.readLine().split(" ");

        for (int i = 0; i < str.length; i++) {
            int num = Integer.parseInt(str[i]);
            bq.add(num);
            sum -= num;
        }

        if (sum != 0) {
            System.out.println(-1);
            return;
        } else {
            System.out.println(minimumPArtition(aq, bq));
        }

    }

    private static int minimumPArtition(Queue<Integer> aq, Queue<Integer> bq) {
        int count = 0;
        long sa = 0, sb = 0;

        while (aq.size() > 0 && bq.size() > 0) {
            if (sa < sb) {
                sa += aq.poll();
            } else {
                sb += bq.poll();
            }

            if (sa == sb) {
                //System.out.println(aq + "\t\t" + bq);
                count++;
                sa = 0;
                sb = 0;
            }
        }

        if (aq.size() > 0 || bq.size() > 0) {
            while (aq.size() > 0) {
                sa += aq.poll();
            }

            while (bq.size() > 0) {
                sb += bq.poll();
            }

            if (sa == sb) {
                return count + 1;
            } else {
                return -1;
            }
        }

        return count;
    }
}
