package com.himanshu.practice.jan2020.jan25;

/**
 * @author Himanshu Bhardwaj
 * Date 25/Jan/2020
 */
public class C {
    public static void main(String[] args) {
        CheatingAfterTests cheatingAfterTests = new CheatingAfterTests();
        int [] arr = {0,11};
        System.out.println();
        System.out.println(cheatingAfterTests.cheat(arr));

    }
}

class CheatingAfterTests {
    int cheat(int[] report) {
        int mostSigNNd = -1;
        int sum = 0;
        int improvement = 0;

        for (int x : report) {
            sum += x;
        }

        for (int i = 0; i < report.length; i++) {
            int tempImprovement = improve(report[i]);
            if (tempImprovement > improvement) {
                improvement = tempImprovement;
            }
        }

        return sum + improvement;

    }

    private int improve(int improve) {
        String digit = Integer.toString(improve);

        int possibleImprovement = 0;

        for (int i = 0; i < digit.length(); i++) {
            if (digit.charAt(i) != '9') {
                possibleImprovement = (int) (((int) ('9' - digit.charAt(i))) * Math.pow(10, digit.length() - i-1));
                break;
            }
        }
        return possibleImprovement;
    }
}
