package com.himanshu.practice.oct.oct29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by himanshubhardwaj on 30/10/19.
 */
public class VasyaAndArray {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        PrintWriter pw = new PrintWriter(System.out);


        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        ArrayList<Segment> option1 = new ArrayList<>();
        ArrayList<Segment> option0 = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            if (Integer.parseInt(str[0]) == 1) {
                option1.add(new Segment(Integer.parseInt(str[1]) - 1, Integer.parseInt(str[2]) - 1));
            } else {
                option0.add(new Segment(Integer.parseInt(str[1]) - 1, Integer.parseInt(str[2]) - 1));
            }
        }

        int arr[] = new int[n];


        Comparator<Segment> comparator = new Comparator<Segment>() {
            @Override
            public int compare(Segment o1, Segment o2) {
                if (o1.start != o2.start) {
                    return Long.compare(o1.start, o2.start);
                } else {
                    return Long.compare(o1.end, o2.end);
                }
            }
        };


        //Collections.sort(option1, comparator);
        ArrayList<Segment> sortedSegments = mergeSegments(option1);

        int previous = 300 + sortedSegments.size();

        for (Segment s : sortedSegments) {
            int start = s.start;
            int end = s.end;
            int tempP = previous--;

            for (int i = start; i <= end; i++) {
                if (arr[i] == 0) {
                    arr[i] = tempP++;
                } else {
                    continue;
                }
            }
        }

//        System.out.println("Sorted Segments");
//        for (Segment s : sortedSegments) {
//            System.out.println(s);
//        }
//
//        System.out.println("-------");


        Collections.sort(option0, comparator);

        ArrayList<Segment> unsortedSegment = option0;

//        System.out.println();
//        System.out.println();
//        System.out.println("UNSorted Segments");
//        for (Segment s : unsortedSegment) {
//            System.out.println(s);
//        }
//
//        System.out.println("-------");


        for (Segment s : unsortedSegment) {
            int start = s.start;
            int end = s.end;
            boolean success = false;

            for (int i = start + 1; i <= end; i++) {
                if (arr[i] < arr[i - 1]) {
                    success = true;
                    break;
                }
            }
            if (!success) {
                System.out.print("NO");
                return;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = 1;
            }
        }

        System.out.println("YES");

        for (int x : arr) {
            pw.append(x + " ");
        }
        pw.flush();
        pw.close();


    }


    static ArrayList<Segment> mergeSegments(ArrayList<Segment> option1) {
        Comparator<Segment> comparator = new Comparator<Segment>() {
            @Override
            public int compare(Segment o1, Segment o2) {
                return Long.compare(o1.start, o2.start);
            }
        };

        Collections.sort(option1, comparator);
        ArrayList<Segment> segments = new ArrayList<>();
        segments.add(option1.get(0));
        int end = option1.get(0).end;


        for (int i = 1; i < option1.size(); i++) {
            if (end >= (option1.get(i).start - 1)) {
                segments.get(segments.size() - 1).end = option1.get(i).end;
                end = segments.get(segments.size() - 1).end;
            } else {
                end = option1.get(i).end;
                segments.add(new Segment(option1.get(i)));
            }
        }
        Collections.sort(segments, comparator);
        return segments;
    }
}


class Segment {
    int start;
    int end;

    public Segment(Segment segment) {
        this.start = segment.start;
        this.end = segment.end;
    }

    @java.beans.ConstructorProperties({"start", "end"})
    public Segment(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public String toString() {
        return "Segment(start=" + this.start + ", end=" + this.end + ")";
    }
}
