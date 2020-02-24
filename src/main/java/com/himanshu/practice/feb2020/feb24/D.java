package com.himanshu.practice.feb2020.feb24;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 24/02/20.
 */
public class D {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {

            String[] str = br.readLine().split(" ");
            int a = Integer.parseInt(str[0]);
            int b = Integer.parseInt(str[1]);
            int c = Integer.parseInt(str[2]);

            Operations operation = new Operations(a, b, c);
            operation.compute();

            pw.append(operation.minMoves + "\n");
            pw.append(operation.minNum[0] + " " + operation.minNum[1] + " " + operation.minNum[2] + "\n");

        }
        pw.flush();
        pw.close();

    }
}

class Operations {
    int a;
    int b;
    int c;
    long minMoves;
    int[] minNum;

    public Operations(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
        minMoves = Integer.MAX_VALUE;
        minNum = new int[3];
    }

    public void compute() {

        int tA, tB, tC;

        for (int i = 1; i <= 20001; i++) {
            for (int jM = 1; jM * i <= 20001; jM++) {

                if (Math.abs((jM * i) - b) > minMoves) {
                    if ((jM * i) > b) {
                        break;
                    }
                }


                for (int kM = 1; kM * jM * i <= 20001; kM++) {
                    tA = i;
                    tB = jM * i;
                    tC = tB * kM;


                    long cs = cost(tA, tB, tC);
                    if (cs < minMoves) {
                        minMoves = cs;
                        minNum[0] = tA;
                        minNum[1] = tB;
                        minNum[2] = tC;
                    }

                    if (Math.abs(tC - c) > minMoves) {
                        if (tC > c) {
                            break;
                        }
                    }
                }


            }
            if (Math.abs(i - a) > minMoves) {
                if (i > a) {
                    break;
                }
            }
        }

    }

    int more(int i, int b) {
        if (i == 0 || b == 0) {
            return Integer.MAX_VALUE;
        }

        int result = ((b / i) + ((b % i == 0) ? 0 : 1)) * i;

        return result;
    }

    int less(int i, int b) {
        if (i == 0 || b == 0) {
            return Integer.MAX_VALUE;
        }
        int result = ((b / i)) * i;
        return result;
    }

    long cost(int ta, int tb, int tc) {
        return ((long) Math.abs(ta - a)) + (long) Math.abs(tb - b) + (long) Math.abs(tc - c);
    }
}
