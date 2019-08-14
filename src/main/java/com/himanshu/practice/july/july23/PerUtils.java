package com.himanshu.practice.july.july23;

public class PerUtils {
    static int count = 0;

    public static void printAllPermutations(int start, int end, int[] str) {
        if (start > end) {
            count++;
            System.out.print(count+":\t");
            for (int i = 0; i <= end; i++) {
                System.out.print(str[i] + ", ");
            }
            System.out.println();
            return;
        }

        for (int i = start; i <= end; i++) {
            if (lastOccurence(i, str)) {
                int temp = str[i];
                str[i] = str[start];
                str[start] = temp;
                printAllPermutations(start + 1, end, str);
                temp = str[i];
                str[i] = str[start];
                str[start] = temp;
            }
        }

    }

    private static boolean lastOccurence(int index, int[] str) {
        for (int i = index + 1; i < str.length; i++) {
            if (str[i] == str[index]) {
                return false;
            }
        }
        return true;
    }
}
