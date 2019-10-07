package com.himanshu.practice.oct.oct6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 06/10/19.
 * Statement: https://codeforces.com/contest/1223/problem/C
 * Algo: Binary Search
 * Submission: https://codeforces.com/contest/1223/submission/62053612
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int q = Integer.parseInt(br.readLine());


        while (q-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String[] priceS = br.readLine().split(" ");
            ArrayList<Long> prices = new ArrayList<>();

            for (String p : priceS) {
                prices.add(Long.parseLong(p));
            }

            TreeSet<Number> priceTree = new TreeSet<>();
            for (int i = 0; i < n; i++) {
                priceTree.add(new Number(prices.get(i), i));
            }


            int x, y, a, b;


            String str[] = br.readLine().split(" ");
            x = Integer.parseInt(str[0]);
            a = Integer.parseInt(str[1]);

            str = br.readLine().split(" ");
            y = Integer.parseInt(str[0]);
            b = Integer.parseInt(str[1]);


            long k = Long.parseLong(br.readLine());
            int ticketReq = -1;


            if (!isPossibile((TreeSet<Number>) priceTree.clone(), n, x, a, y, b, k, n)) {
                pw.append("-1\n");
                continue;
            }

            if (n <= 3) {
                for (int i = 0; i < n; i++) {
                    ticketReq = (isPossibile((TreeSet<Number>) priceTree.clone(), n, x, a, y, b, k, i + 1)) ? 1 : -1;
                    if (ticketReq != -1) {
                        ticketReq = i + 1;
                        break;
                    }
                }
                pw.append(ticketReq + "\n");
            } else {
                int start = 1;
                int end = n;

                while ((end - start) > 3) {
                    int mid = (start + end) / 2;
                    if (!isPossibile((TreeSet<Number>) priceTree.clone(), n, x, a, y, b, k, mid)) {
                        start = mid;
                    } else {
                        end = mid;
                    }
                }

                for (int i = start; i <= end; i++) {
                    if (isPossibile((TreeSet<Number>) priceTree.clone(), n, x, a, y, b, k, i)) {
                        pw.append(i + "\n");
                        break;
                    }
                }
            }
        }

        pw.flush();
        pw.close();
    }


    static private boolean isPossibile(TreeSet<Number> priceTree, int n, int x, int a, int y, int b, long k, int limit) {
        long totalCollection = 0;
        int ticketReq = -1;

        for (int i = 0; i < limit; i++) {
            if (((i + 1) % a) == 0 && ((i + 1) % b == 0)) {
                totalCollection += ((priceTree.last().number * (x + y)) / 100);
                priceTree.remove(priceTree.last());
            }
        }

        if (totalCollection >= k) {
            return true;
        }

        if (x < y) {
            int temp = y;
            int temp1 = b;
            y = x;
            b = a;
            x = temp;
            a = temp1;
        }




        for (int i = 0; i < limit; i++) {

//            if (((i + 1) % a == 0) || ((i + 1) % b == 0)) {
                if (((i + 1) % a) == 0 && ((i + 1) % b == 0)) {
                    continue;
                }

                if ((i + 1) % a == 0) {
                    totalCollection += ((priceTree.last().number * x) / 100);
                    priceTree.remove(priceTree.last());
                }

//                if ((i + 1) % b == 0) {
//                    totalCollection += ((priceTree.last().number * y) / 100);
//                }

//                System.out.println((i + 1) + "\t" + totalCollection + "\t" + priceTree.last());



                if (totalCollection >= k) {
                    ticketReq = i + 1;
                    break;
                }
//            }
        }

        if (totalCollection >= k) {
            return true;
        }


        for (int i = 0; i < limit; i++) {

//            if (((i + 1) % a == 0) || ((i + 1) % b == 0)) {
            if (((i + 1) % a) == 0 && ((i + 1) % b == 0)) {
                continue;
            }

//            if ((i + 1) % a == 0) {
//                totalCollection += ((priceTree.last().number * x) / 100);
//            }

                if ((i + 1) % b == 0) {
                    totalCollection += ((priceTree.last().number * y) / 100);
                    priceTree.remove(priceTree.last());
                }

//                System.out.println((i + 1) + "\t" + totalCollection + "\t" + priceTree.last());



            if (totalCollection >= k) {
                ticketReq = i + 1;
                break;
            }
//            }
        }

        return (ticketReq != -1);

    }
}


class Number implements Comparable<Number> {
    long number;
    int index;

    @java.beans.ConstructorProperties({"number", "index"})
    public Number(long number, int index) {
        this.number = number;
        this.index = index;
    }

    @Override
    public int compareTo(Number o) {
        return (this.number != o.number) ? Long.compare(this.number, o.number) : Long.compare(this.index, o.index);
    }

    public String toString() {
        return "Number(number=" + this.number + ", index=" + this.index + ")";
    }
}

/*

1
8
100 200 100 200 100 200 100 100
10 2
15 3
107

*
*
* */