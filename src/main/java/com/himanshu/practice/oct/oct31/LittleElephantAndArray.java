package com.himanshu.practice.oct.oct31;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


/**
 * Created by himanshubhardwaj on 31/10/19.
 * complexity: O(nlogn + m sqrt(n)logn)
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


        TreeSet<Number>[] setA = new TreeSet[100001];
        for (int i = 0; i < setA.length; i++) {
            setA[i] = new TreeSet<>(comparator);
        }

        str = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str[i]);
            if (arr[i] > 100000) {
                continue;
            }
            setA[arr[i]].add(new Number(i, -1));
        }


        HashSet<Integer> nonEmptyNum = new HashSet<>();
        for (int i = 0; i < setA.length; i++) {
            if (setA[i].size() < i) {
                setA[i] = null;
            } else {
                nonEmptyNum.add(i);
                int pos = 0;
                for (Number num : setA[i]) {
                    num.index = pos++;
                }
            }
        }


//        for (int x : treeMap.keySet()) {
//            System.out.println(x + "\t" + treeMap.get(x));
//        }
//        System.out.println();


        Number tempNumberl = new Number(0, 0);
        Number tempNumberr = new Number(0, 0);


        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            int l = Integer.parseInt(str[0]) - 2;
            int r = Integer.parseInt(str[1]) - 1;
            tempNumberl.position = l;
            tempNumberr.position = r;

            int count = 0;

            for (int numbers : nonEmptyNum) {
                TreeSet<Number> set = setA[numbers];
                Number rf = set.floor(tempNumberr);
                Number lf = set.floor(tempNumberl);
                int x = numbers;

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
}

