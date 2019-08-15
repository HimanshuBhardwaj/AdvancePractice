package com.himanshu.practice.aug.aug14;

/**
 * Created by himanshubhardwaj on 14/08/19.
 */
public class PermutuationPOC {
    static int number = 0;

    public static void main(String[] args) {
        char[] ch = "abcde".toCharArray();
        printPermutuation(0, ch.length - 1, ch);

    }

    private static void printPermutuation(int index, int last, char[] ch) {
        if (last == index) {
            System.out.println(number + "\t" + new String(ch));
            number++;
            return;
        }

        for (int i = index; i <= last; i++) {
            swap(index, i, ch);
            printPermutuation(index + 1, last, ch);
            swap(index, i, ch);
        }
    }

    private static void swap(int index, int i, char[] ch) {
        char c = ch[index];
        ch[index] = ch[i];
        ch[i] = c;
    }


}
