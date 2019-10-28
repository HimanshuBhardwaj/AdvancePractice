package com.himanshu.practice.oct.oct24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by himanshubhardwaj on 24/10/19.
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 % 2 - o2 % 2;
            }
        };

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            Integer[] charfreq = new Integer[2];
            charfreq[0] = 0;
            charfreq[1] = 0;
            int a = 0;
            int b = 0;
            Integer[] size = new Integer[n];
            int oddCount = 0;

            for (int i = 0; i < n; i++) {
                String str = br.readLine();
                for (char c : str.toCharArray()) {
                    charfreq[c - '0']++;
                }
                if ((str.length() % 2) == 0) {
                    a++;
                } else {
                    b++;
                    oddCount++;
                }
            }
            charfreq[0] = charfreq[0] % 2;
            charfreq[1] = charfreq[1] % 2;
            a = a % 2;
            b = b % 2;

            if (charfreq[0] == 0) {
                if (charfreq[1] == 0) {
                    pw.append(n + "\n");
                } else {
                    if (b % 2 == 1) {
                        pw.append(n + "\n");
                    } else {
                        pw.append((n - 1) + "\n");
                    }
                }
            } else {
                if (charfreq[1] == 0) {
                    if (b % 2 == 1) {
                        pw.append(n + "\n");
                    } else {
                        pw.append((n - 1) + "\n");
                    }
                } else {
                    if (oddCount == 1) {
                        pw.append((n - 1) + "\n");
                    } else if (oddCount == 0) {
                        pw.append((n - 1) + "\n");
                    } else {
                        charfreq[0] = 0;
                        charfreq[1] = 0;
                        pw.append(n + "\n");
                    }
                }
            }
        }
        pw.flush();
        pw.close();
    }

    private static String maximumPalindrome(Integer[] charfreq, Integer[] size) {
        for (int x : size) {
            System.out.print(x + " ");
        }
        System.out.println();
        int count = 0;
        for (int i = 0; i < size.length; i++) {
            Arrays.sort(charfreq, Collections.reverseOrder());
            if (size[i] <= charfreq[0]) {
                charfreq[0] -= size[i];
                count++;
            } else if (size[i] <= charfreq[1]) {
                charfreq[1] -= size[i];
                count++;
            } else {
                if ((((charfreq[0] % 2) + (charfreq[1] % 2))) == (size[i] % 2)) {
                    count++;
                }
            }
        }
        return count + "\n";
    }

}
