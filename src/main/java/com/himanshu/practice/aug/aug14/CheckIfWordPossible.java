package com.himanshu.practice.aug.aug14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 15/08/19.
 */
public class CheckIfWordPossible {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]); // number of dices
        int m = Integer.parseInt(str[1]); // face o each dice

        String[] dice = new String[n];

        for (int i = 0; i < dice.length; i++) {
            dice[i] = br.readLine();
        }

        int[] pos = new int[n];

        System.out.println(isPossible(word, dice, pos, 0));


    }


    private static boolean isPossible(String word, String[] dice, int[] pos, int index) {
        if (index >= dice.length) {
            return false;
        }

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < dice.length; i++) {
            sb.append(dice[i].charAt(pos[i]));
        }

        if (sb.toString().equals(word)) {
            return true;
        }

        for (int i = 0; i < dice[0].length(); i++) {
            pos[index] = i;
            if (isPossible(word, dice, pos, index + 1)) {
                return true;
            }
        }
        return false;
    }


}
/*


abc
3 2
aa
cb
ee

* */