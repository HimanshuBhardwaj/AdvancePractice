package com.himanshu.practice.july.july28;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 28/07/19.
 */
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        String[] str;
        PrintWriter pw = new PrintWriter(System.out);

        for (int ca = 1; ca <= t; ca++) {
            str = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int q = Integer.parseInt(str[1]);

            int arr[] = new int[n];

            str = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(str[i]);
            }

            SegmentTree segmentTree = new SegmentTree(arr);
            pw.append("Case #" + ca + ": ");

            for (int i = 0; i < q; i++) {
                str = br.readLine().split(" ");
                int p = Integer.parseInt(str[0]);
                int v = Integer.parseInt(str[1]);

                if (n != 1) {
                    segmentTree.update(p, v);
                    pw.append(maximumRequiredRange(segmentTree, n) + " ");
                } else {
                    pw.append((isXorEven(v) ? 1 : 0) + " ");
                }

            }
            pw.append("\n");
        }
        pw.flush();
        pw.close();
        br.close();


    }

    private static int maximumRequiredRange(SegmentTree segmentTree, int n) {
        int maxRange = 0;
        for (int i = n; i >= 1; i--) {
            for (int start = 0; start + i - 1 < n; start++) {
                int end = start + i - 1;
                int xor = segmentTree.rangeXor(start, end);
                if (isXorEven(xor)) {
                    return i;
                }
            }
        }

        return 0;
    }

    static boolean isXorEven(int number) {

        int count = 0;

        while (number > 0) {
            count += number % 2;
            number = number / 2;
            if (number == 0) {
                break;
            }
        }
        //System.out.println(number + "\t" + ((count % 2) == 0));

        return ((count % 2) == 0);
    }
}


//class SegmentTree {
//    int[] arr;
//
//    public SegmentTree(int[] arr) {
//        this.arr = arr;
//    }
//
//    public int rangeXor(int start, int end) {
//        int a = 0;
//
//        for (int i = start; i <= end; i++) {
//            a = a ^ arr[i];
//        }
//        return a;
//    }
//
//    public void update(int index, int value) {
//        arr[index] = value;
//    }
//}


class SegmentTree {
    int[] segmentTree;
    int endNode;
    int endPos;
    int size;


    public SegmentTree(int[] arr) {
        size = (int) Math.pow(2, Math.ceil(Math.log(arr.length) / Math.log(2)));
        size = 2 * size - 1;

        segmentTree = new int[size];

        for (int i = 0; i < (segmentTree.length + 1) / 2; i++) {
            if (i < arr.length) {
                segmentTree[i + segmentTree.length / 2] = arr[i];
                endNode = i + segmentTree.length / 2;
            } else {
                segmentTree[i + segmentTree.length / 2] = 0;
            }
            if (i == (arr.length - 1)) {
                endPos = i + size / 2;
            }
        }


        for (int i = (segmentTree.length / 2) - 1; i >= 0; i--) {
            int child1 = 2 * i + 1;
            int child2 = 2 * i + 2;
            segmentTree[i] = segmentTree[child1] ^ segmentTree[child2];
        }
    }

    public int rangeXor(int start, int end) {
        return rangeXorHelper(0, segmentTree.length / 2, start, end, 0);
    }

    private int rangeXorHelper(int segTS, int segTE, int rS, int sE, int index) {
        if (segTE < 0 || segTE > size / 2 || segTE < segTS || segTE < rS || segTS > sE || index < 0 || index > endPos) {
            return 0;
        }

        if (rS <= segTS && sE >= segTE) {
            return segmentTree[index];
        }


        int mid = segTS + (segTE - segTS) / 2;


        return rangeXorHelper(segTS, mid, rS, sE, 2 * index + 1) ^
                rangeXorHelper(mid + 1, segTE, rS, sE, 2 * index + 2);
    }

    public void update(int index, int value) {
        int posSegmentTree = (segmentTree.length / 2) + index;
        segmentTree[posSegmentTree] = value;
        posSegmentTree = (posSegmentTree - 1) / 2;

        while (posSegmentTree >= 0) {
            int left = (2 * posSegmentTree + 1);
            int right = (2 * posSegmentTree + 2);
            if (right < segmentTree.length && left < segmentTree.length) {
                segmentTree[posSegmentTree] = segmentTree[left] ^ segmentTree[right];
            } else if (left < segmentTree.length) {
                segmentTree[posSegmentTree] = segmentTree[left];
            }

            if (posSegmentTree == 0) {
                break;
            } else {
                posSegmentTree = (posSegmentTree - 1) / 2;
            }
        }
    }

}