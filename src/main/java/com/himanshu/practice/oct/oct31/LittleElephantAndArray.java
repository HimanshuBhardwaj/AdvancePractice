package com.himanshu.practice.oct.oct31;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;


/**
 * Created by himanshubhardwaj on 31/10/19.
 */
public class LittleElephantAndArray {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        PrintWriter pw = new PrintWriter(System.out);


        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        int[] arr = new int[n];

        Comparator<Number> comparator = new Comparator<Number>() {
            @Override
            public int compare(Number o1, Number o2) {
                return Integer.compare(o1.position, o2.position);
            }
        };

        TreeMap<Integer, TreeSet<Number>> treeMap = new TreeMap<>();

        str = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str[i]);
            if (arr[i] > 100000) {
                continue;
            }

            if (!treeMap.containsKey(arr[i])) {
                treeMap.put(arr[i], new TreeSet<Number>(comparator));
            }
            treeMap.get(arr[i]).add(new Number(i, -1));
        }


        TreeSet<Integer> tempSet = new TreeSet<>(treeMap.keySet());
        for (int x : tempSet) {
            TreeSet<Number> set = treeMap.get(x);
            if (set.size() < x) {
                treeMap.remove(x);
                continue;
            }
            int pos = 0;
            for (Number num : set) {
                num.index = pos++;
            }
        }

//        for (int x : treeMap.keySet()) {
//            System.out.println(x + "\t" + treeMap.get(x));
//        }
//        System.out.println();

        HashMap<Integer, TreeSet<Number>> newMap = new HashMap<>(treeMap);


        Number tempNumberl = new Number(0, 0);
        Number tempNumberr = new Number(0, 0);

        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            int l = Integer.parseInt(str[0]) - 2;
            int r = Integer.parseInt(str[1]) - 1;
            tempNumberl.position = l;
            tempNumberr.position = r;

            int count = 0;

            for (int x : treeMap.keySet()) {
                TreeSet<Number> set = treeMap.get(x);
                Number rf = set.floor(tempNumberr);
                Number lf = set.floor(tempNumberl);


                if (lf != null && rf != null) {
                    if ((rf.index - lf.index) == x) {
                        count++;
                    }
                } else if (lf != null) {
                    //not possible case

                } else if (rf != null) {
                    if (rf.index == (x - 1)) {
                        count++;
                    }
                }
            }

            pw.append(count + "\n");
        }
        pw.flush();
        pw.close();


    }
}


class Number {
    int position;
    int index;

    @java.beans.ConstructorProperties({"position", "index"})
    public Number(int position, int index) {
        this.position = position;
        this.index = index;
    }

    public String toString() {
        return "Number(position=" + this.position + ", index=" + this.index + ")";
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Number)) return false;
        final Number other = (Number) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.position != other.position) return false;
        if (this.index != other.index) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.position;
        result = result * PRIME + this.index;
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Number;
    }
}
