package com.himanshu.practice.oct.oct24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by himanshubhardwaj on 28/10/19.
 * Statement: https://codeforces.com/contest/1251/problem/E1
 */
public class Voting {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            ArrayList<Voter> voters = new ArrayList<>(n);

            for (int i = 0; i < n; i++) {
                String[] str = br.readLine().split(" ");
                int m = Integer.parseInt(str[0]);
                long p = Long.parseLong(str[1]);
                Voter v = new Voter(p, m);
                voters.add(v);
            }

            final Comparator<Voter> numReqC = new Comparator<Voter>() {
                @Override
                public int compare(Voter o1, Voter o2) {
                    return Integer.compare(o1.numVoterRequied, o2.numVoterRequied);
                }
            };

            Comparator<Voter> priceC = new Comparator<Voter>() {
                @Override
                public int compare(Voter o1, Voter o2) {
                    return Long.compare(o1.price, o2.price);
                }
            };

            PriorityQueue<Voter> numReq = new PriorityQueue<>(numReqC);

            for (Voter v : voters) {
                numReq.add(v);
            }

            PriorityQueue<Voter> pricePQ = new PriorityQueue<>(priceC);


            for (Voter v : voters) {
                pricePQ.add(v);
            }


            long cost = 0l;
            int numVoted = 0;


            while (!pricePQ.isEmpty()) {
                if (pricePQ.peek().hasVoted) {
                    pricePQ.poll();
                    continue;
                }

                if (numReq.peek().hasVoted) {
                    numReq.poll();
                    continue;
                }

                if (numReq.peek().numVoterRequied <= numVoted) {
                    numReq.peek().hasVoted = true;
                    numReq.poll();
                    numVoted++;
                } else {
                    cost += pricePQ.peek().price;
                    pricePQ.peek().hasVoted = true;
                    numVoted++;
                }
            }


            pw.append(cost + "\n");
        }
        pw.flush();
        pw.close();
    }
}


class Voter {
    long price;
    int numVoterRequied;
    boolean hasVoted = false;

    @java.beans.ConstructorProperties({"price", "numVoterRequied"})
    public Voter(long price, int numVoterRequied) {
        this.price = price;
        this.numVoterRequied = numVoterRequied;
    }
}
