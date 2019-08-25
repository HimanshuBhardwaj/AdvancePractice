package com.himanshu.practice.aug.aug25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 25/08/19.
 */
public class C {
    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tt = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);
        String output[] = new String[tt];
        Thread[] threads = new Thread[tt];

        boolean[] isSet = new boolean[1000002];
        boolean[] answer = new boolean[1000002];
        int[] sp = new int[1000002];
        int[] evenFactors = new int[1000002];
        int[] oddFactors = new int[1000002];

        NumberOFColours.Sieve(new boolean[1000003], 1000002, sp, 1000002);


        for (int i = 1; i < isSet.length; i++) {
            NumberOFColours.count(i, sp, isSet, answer, evenFactors, oddFactors);
        }



        for (int t = 1; t <= tt; t++) {
            String[] str = br.readLine().split(" ");

            NumberOFColours numberOFColours = new NumberOFColours(Integer.parseInt(str[0]), Integer.parseInt(str[1]), output, t, answer);
            threads[t - 1] = new Thread(numberOFColours);
            threads[t - 1].start();

//            pw.append("Case #" + t + ": " + numberOFColours.countNumber() + "\n");
        }


        for (int t = 1; t <= tt; t++) {
            try {
                threads[t - 1].join();
            } catch (Exception e) {

            }
        }

        for (String s : output) {
            pw.append(s);
        }

        pw.flush();
    }


}


class NumberOFColours implements Runnable {
    int l;
    int r;
    int caseN;
    String[] output;
    boolean[] answer;


    public NumberOFColours(int l, int r, String[] output, int caseN, boolean[] answer) {
        this.l = l;
        this.r = r;
        this.output = output;
        this.caseN = caseN;
        this.answer = answer;
    }


    int countNumber() {
        int countN = 0;

        for (int i = l; i <= r; i++) {
            if (answer[i]) {
                countN++;
            }
        }
        return countN;
    }


    static boolean count(int x, int[] sp, boolean[] answer, boolean[] isSet, int[] evenFactors, int[] oddFactors) {
        if (isSet[x]) {
            return answer[x];
        }

        if (x == 1) {
            oddFactors[x] = 1;
            isSet[x] = true;
            answer[x] = true;
            return answer[x];
        }

        if (x == 2) {
            oddFactors[x] = 1;
            evenFactors[x] = 1;
            isSet[x] = true;
            answer[x] = true;
            return answer[x];
        }


        int even = 0;
        int odd = 1;
        int tx = x;


        evenFactors[x] = (sp[x] % 2 == 0) ? (evenFactors[x / sp[x]] + oddFactors[x / sp[x]]) : (evenFactors[x / sp[x]]);
        oddFactors[x] = (sp[x] % 2 == 1) ? (1 + oddFactors[x / sp[x]]) : (oddFactors[x / sp[x]]);


        if (Math.abs(evenFactors[tx] - oddFactors[tx]) <= 2) {
            answer[tx] = true;
        } else {
            answer[tx] = false;
        }

        isSet[tx] = true;
        return answer[tx];
    }


    static void Sieve(boolean v[], int len, int sp[], int MAX) {
        for (int i = 2; i < MAX; i += 2) {
            sp[i] = 2;//even numbers have smallest prime factor 2
            v[i] = true;
        }

        for (int i = 3; i < MAX; i += 2) {
            if (!v[i]) {
                sp[i] = i;
                for (long j = i; (j * i) < MAX; j += 2) {
                    if (!v[(int) (j * i)]) {
                        v[(int) (j * i)] = true;
                        sp[(int) (j * i)] = i;
                    }
                }
            }
        }
    }

    @Override
    public void run() {
        int result = countNumber();
        synchronized (output) {
            output[caseN - 1] = "Case #" + caseN + ": " + result + "\n";
        }
    }
}