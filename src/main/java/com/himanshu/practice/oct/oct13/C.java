package com.himanshu.practice.oct.oct13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 13/10/19.
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        long n = Long.parseLong(str[0]);
        long p = Long.parseLong(str[1]);
        int w = Integer.parseInt(str[2]);
        int d = Integer.parseInt(str[3]);
        int gcd = gcd(w, d);

        if (p % gcd != 0) {
            System.out.println(-1);
            return;
        }
        p = p / gcd;
        w = w / gcd;
        d = d / gcd;
        long x0 = -1;
        long y0 = -1;


        for (int i = 0; i <= 1000000; i++) {
            x0 = i;
            if (((p - (w * x0)) % d) == 0) {
                y0 = (p - (w * x0)) / d;

                if (x0 + y0 <= n) {
                    break;
                }
            }
        }

        if ((((x0 * w) + (y0 * d)) != p)) {
            for (int i = 0; i <= 1000000; i++) {
                y0 = i;
                if (((p - (d * y0)) % w) == 0) {
                    x0 = (p - (d * y0)) / w;

                    if (x0 + y0 <= n) {
                        break;
                    }
                }
            }
        }

        if ((((x0 * w) + (y0 * d)) != p)) {
            System.out.println(-1);
            return;
        }

        //     System.out.println(x0 + "\t" + y0);


//        if ((x0 >= 0) && (y0 >= 0) && (x0 + y0 <= n)) {
//            System.out.println(x0 + " " + y0 + " " + (n - (x0 + y0)));
//            return;
//        }

        for (int r = 0; r <= 10000000; r++) {
            long x = x0 - (r * d);
            long y = y0 - (r * w);

            if ((x >= 0) && (y >= 0) && (x + y <= n) && ((w*x+d*y)==p)) {
                System.out.println(x + " " + y + " " + (n - (x + y)));
                return;
            }

            x = x0 + (r * d);
            y = y0 + (r * w);

            if ((x >= 0) && (y >= 0) && (x + y <= n && ((w*x+d*y)==p))) {
                System.out.println(x + " " + y + " " + (n - (x + y)));
                return;
            }


        }

        System.out.println(-1);

    }


    static int gcd(int a, int b) {
        if (a == 1 || b == 1) {
            return 1;
        }

        if (a == 0) {
            return b;
        }

        if (b == 0) {
            return a;
        }
        if (a < b) {
            return gcd(b, a);
        }

        return gcd(a % b, b);
    }
}
