package com.himanshu.practice.feb2020.feb21;

/**
 * Created by himanshubhardwaj on 21/02/20.
 */
public class OneGcd {
    int y;

    public static void main(String[] args) {
        OneGcd oneGcd = new OneGcd();
        long[] x = {5,3};
        int[] y = {3,7};
        int[] resulr = oneGcd.solve(x, y);

        for (int xx : resulr) {
            System.out.println(xx);
        }
    }

    public int[] solve(long[] X, int[] Y) {
        int[] result = new int[X.length];

        for (int i = 0; i < X.length; i++) {
            result[i] = (int) solveHelper(X[i], Y[i]);
        }

        return result;
    }


    private long solveHelper(long x, int y) {
        long n1 = numberDivisible(y + x - 1, y);
        long n2 = numberDivisible(x - 1, y);
        long numDivisible = n1 - n2;
        return y - numDivisible;
    }

    //this will give number less than equal to num and are divisibe by 2,3,5,7
    private long numberDivisible(long num, int y) {
        if (num <= 0) {
            return 0;
        }
        this.y = y;


        long result = T(num, 2) + T(num, 3) + T(num, 5) + T(num, 7)
                - T(num, 6) - T(num, 10) - T(num, 14) - T(num, 15) - T(num, 21) - T(num, 35)
                + T(num, 105) + T(num, 70) + T(num, 42) + T(num, 30) - T(num, 210);

        return result;
    }

    private long T(long num, int i) {
        return (((y / i) * i) == y) ? num / i : 0;
//        switch (i) {
//            case 2:
//                return (divisor(y, 2) ? (num / i) : 0);
//            case 3:
//                return (divisor(y, 2) ? (num / i) : 0);
//            case 5:
//                return (divisor(y, 2) ? (num / i) : 0);
//            case 7:
//                return (divisor(y, 2) ? (num / i) : 0);
//            case 6:
//                return (divisor(y, 2) ? (num / i) : 0);
//            case 10:
//                return (divisor(y, 2) ? (num / i) : 0);
//            case 14:
//                return (divisor(y, 2) ? (num / i) : 0);
//            case 15:
//                return (divisor(y, 2) ? (num / i) : 0);
//            case 21:
//                return (divisor(y, 2) ? (num / i) : 0);
//            case 35:
//                return (divisor(y, 2) ? (num / i) : 0);
//            case 105:
//                return (divisor(y, 2) ? (num / i) : 0);
//            case 70:
//                return (divisor(y, 2) ? (num / i) : 0);
//            case 42:
//                return (divisor(y, 2) ? (num / i) : 0);
//            case 30:
//                return (divisor(y, 2) ? (num / i) : 0);
//            case 210:
//                return (divisor(y, 2) ? (num / i) : 0);
//        }
//        return 0;
    }

    private int gcd(int a, int b) {
        if (a > b) {
            gcd(b, a);
        }

        if (a == 0) {
            return b;
        }

        if (a == 1) {
            return 1;
        }


        return gcd(b % a, a);
    }

    int[] arrP = {2, 3, 5, 7, 6, 10, 14, 15, 21, 35, 105, 70, 42, 30, 210};

    private boolean divisor(int y, int i) {
        if (y % i != 0) {
            return false;
        }
        for (int x : arrP) {
            if (x != i) {
                if (x % i == 0) {

                }
            }
        }

        return false;
    }
}
