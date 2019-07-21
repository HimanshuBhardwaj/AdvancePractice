package com.himanshu.practice.july.july20;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by himanshubhardwaj on 20/07/19.
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String str[] = br.readLine().split(" ");
        int AP = Integer.parseInt(str[0]);
        ArrayList<Party> parties = new ArrayList();
        int totalSeat = AP;

        for (int i = 1; i < str.length; i++) {
            Party party = new Party(i + 1, Integer.parseInt(str[i]));
            parties.add(party);
            totalSeat += party.numSeats;
        }

        int requiredMajority = (totalSeat / 2) + 1;
        Collections.sort(parties);

        ArrayList<Party> coalisionparty = new ArrayList<>();
        coalisionparty.add(new Party(1, AP));
        int currentSeats = AP;
        requiredMajority -= AP;

        for (int i = 0; i < parties.size(); i++) {
            if (requiredMajority > 0) {
                if ((AP / 2) >= parties.get(i).numSeats) {
                    coalisionparty.add(parties.get(i));
                    requiredMajority -= parties.get(i).numSeats;
                }
            } else {
                break;
            }
        }

        if (requiredMajority > 0) {
            System.out.println(0);
        } else {
            PrintWriter pw = new PrintWriter(System.out);
            pw.append(coalisionparty.size()+"\n");
            for (Party p:coalisionparty) {
                pw.append(p.index+" ");
            }
            pw.flush();
        }



    }
}


class Party implements Comparable<Party> {
    int index;
    int numSeats;

    @java.beans.ConstructorProperties({"index", "numSeats"})
    public Party(int index, int numSeats) {
        this.index = index;
        this.numSeats = numSeats;
    }

    @Override
    public int compareTo(Party o) {
        return o.numSeats - this.numSeats;
    }
}