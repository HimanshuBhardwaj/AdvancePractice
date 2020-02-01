package com.himanshu.practice.jan2020.jan25;

/**
 * @author Himanshu Bhardwaj
 * Date 25/Jan/2020
 */
public class BinaryHeapLeaf {
    public static void main(String[] args) {
        for (int i = 2; i <= 10000; i++) {
            BinaryHeapLeaf binaryHeapLeaf = new BinaryHeapLeaf();
            ContestSolution contestSolution = new ContestSolution();
            int[] result1 = binaryHeapLeaf.maxDiff(i);
            int[] result2 = contestSolution.maxDiff(i);
            if ((result1[0] != result2[0]) || (result1[1] != result2[1])) {
                System.out.println(i + "\t" + result1[0] + "," + result1[1] + "\t\t" + result2[0] + "," + result2[1]);
                //break;
            } else {
                //System.out.println(i + ".. Okay");
            }
        }

    }

    public int[] maxDiff(int N) {


        int internalLeaves = ((N - 1) % 2 == 0) ? ((N - 1) / 2) : (1 + (((N - 1) / 2)));
        int answer[] = new int[2];

        if (N == 2) {
            answer[0] = 1;
            answer[1] = 1;
            return answer;
        }

        answer[0] = N - internalLeaves;


        int i = 1;

        for (; i <= N; i++) {
            if (2 * i > N) {
                break;
            }
        }

        int count = 0;

        while (i > 1) {
            count++;
            i = i / 2;
        }




        answer[1] = N - count;
        return answer;
    }
}

class ContestSolution {
    public boolean isLeaf(int N, int v) {

        return 2 * v > N;

    }


    public int[] maxDiff(int N) {

        int numberOfLeaves = 0;

        int minLeafDepth = N + 47;


        // iterate over all possible depths

        for (int depth = 0; ; ++depth) {

            // given the depth, compute the IDs of the vertices in this depth

            int lo = 1 << depth, hi = 1 << (depth + 1);

            // if we are already too deep, stop

            if (lo > N) break;

            // look at all vertices and count leaves among them

            for (int v = lo; v < hi; ++v) {

                if (v > N) break;

                if (isLeaf(N, v)) {

                    ++numberOfLeaves;

                    minLeafDepth = Math.min(minLeafDepth, depth);

                }

            }

        }

        return new int[]{numberOfLeaves, N - minLeafDepth};

    }

}

