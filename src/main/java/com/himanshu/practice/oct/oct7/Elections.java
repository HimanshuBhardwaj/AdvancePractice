package com.himanshu.practice.oct.oct7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by himanshubhardwaj on 07/10/19.
 */
public class Elections {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        ArrayList<Voter> voters = new ArrayList<>(n);

        int[] count = new int[m];
        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            Voter voter = new Voter(Integer.parseInt(str[0]) - 1, Integer.parseInt(str[1]));
            voters.add(voter);
            count[voter.party]++;
        }

        if (isWinning(count)) {
            System.out.println(0);
            return;
        }


        Collections.sort(voters);
        int maxVotes = -1;

        for (int c : count) {
            maxVotes = Math.max(maxVotes, c);
        }


        long cost = Long.MAX_VALUE;


        int start = count[0];
        int end = maxVotes + 1;

//        while ((end - start) > 5) {
//            int mid = (start + end) / 2;
//            if (couldWin(voters, count, mid) != Long.MAX_VALUE) {
//                end = mid;
//            } else {
//                start = mid;
//            }
//        }

        for (int i = end; i >= start; i--) {
            long tempCost = couldWin(voters, count, i);
            cost = Math.min(tempCost, cost);
        }
        System.out.println(cost);
    }

    private static long couldWin(ArrayList<Voter> voters, int[] count, int numVotes) {
        int[] countClone = count.clone();
        ArrayList<Voter> votersClone = new ArrayList<>();

        for (Voter v : voters) {
            votersClone.add(new Voter(v));
        }

        int countVotes = count[0];
        long cost = 0l;

        for (int i = 1; i < count.length; i++) {
            if (countClone[i] >= numVotes) {
                countVotes += (1 + countClone[i] - numVotes);
                cost += normalise(votersClone, countClone, i, numVotes - 1);
            }
        }

        if (countVotes > numVotes) {
            return Long.MAX_VALUE;
        }

        int pos = 0;
        while ((countVotes < numVotes) && pos < votersClone.size()) {
            if (votersClone.get(pos).party != 0) {
                cost += votersClone.get(pos).cost;
                countVotes++;
                countClone[votersClone.get(pos).party]--;
                countClone[0]++;
                votersClone.get(pos).party = 0;
            }
            pos++;
        }

        if (isWinning(countClone)) {
            return cost;
        }

        return Long.MAX_VALUE;
    }

    private static long normalise(ArrayList<Voter> voters, int[] count, int partyIndex, int numVotes) {
        long cost = 0;
        for (int i = 0; i < voters.size(); i++) {
            if (voters.get(i).party == partyIndex) {
                cost += voters.get(i).cost;
                count[partyIndex]--;
                count[0]++;
                voters.get(i).party = 0;
            }
            if (count[partyIndex] <= numVotes) {
                return cost;
            }
        }
        return cost;
    }


    static boolean isWinning(int[] count) {
        for (int i = 1; i < count.length; i++) {
            if (count[i] >= count[0]) {
                return false;
            }
        }
        return true;
    }
}


class Voter implements Comparable<Voter>, Cloneable {
    int party;
    int cost;

    @java.beans.ConstructorProperties({"party", "cost"})
    public Voter(int party, int cost) {
        this.party = party;
        this.cost = cost;
    }

    public Voter(Voter v) {
        this.party = v.party;
        this.cost = v.cost;
    }


    @Override
    public int compareTo(Voter o) {
        return Integer.compare(this.cost, o.cost);
    }

    public String toString() {
        return "Voter(party=" + this.party + ", cost=" + this.cost + ")";
    }
}

/*

5
2 100
3 200
4 300
5 400
5 900


*
* */