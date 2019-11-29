package com.himanshu.practice.nov.nov16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by himanshubhardwaj on 16/11/19.
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int a[] = new int[n];
        String str[] = br.readLine().split(" ");
        Day[] days = new Day[n];
        PrintWriter pw = new PrintWriter(System.out);

        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(str[i]);
            days[i] = new Day();
        }

        int dayNumber = 0;
        long sum = 0;
        boolean falg = false;

        for (int i = 0; i < n; i++) {
            days[dayNumber].eventOrder.add(a[i]);
            days[dayNumber].contains.add(a[i]);
            sum += a[i];
            if (sum == 0) {
                dayNumber++;
            }
        }

        for (Day d : days) {
            if (!d.isValidDay()) {
                System.out.println(-1);
                return;
            }
        }

        pw.append((dayNumber) + "\n");
        for (int i = 0; i < dayNumber; i++) {
            pw.append(days[i].eventOrder.size() + " ");
        }
        pw.flush();
        pw.close();
    }
}

class Day {
    ArrayList<Integer> eventOrder;
    HashSet<Integer> contains;

    public Day() {
        eventOrder = new ArrayList<>();
        contains = new HashSet<>();
    }

    boolean isValidDay() {
        if (eventOrder.size() == 0) {
            return true;
        }
        long count = 0;

        HashSet<Integer> eventSet = new HashSet<>();

        for (int e : eventOrder) {
            count += e;
            if (e > 0) {
                if (eventSet.contains(-e)) {
                    return false;
                }
                if (eventSet.contains(e)) {
                    return false;
                }
                eventSet.add(e);
            } else {
                if (!eventSet.contains(-e)) {
                    return false;
                }
                if (eventSet.contains(e)) {
                    return false;
                }
                eventSet.add(e);
            }
        }

        if (count == 0) {
            return true;
        }

        return false;
    }
}
