package com.himanshu.practice.nov.nov29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author Himanshu Bhardwaj
 * Date 01/Dec/2019
 */
public class Winner {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] score = new String[3 * 1000000 + 300];
        HashMap<String, Integer> playersToScoreMapping = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split(" ");
            int sc = Integer.parseInt(str[1]);
            if (!playersToScoreMapping.containsKey(str[0])) {
                playersToScoreMapping.put(str[0], sc + 1000100);
            } else {
                playersToScoreMapping.put(str[0], playersToScoreMapping.get(str[0]) + sc);
            }

//            System.out.println(str[0]);
//            System.out.println(playersToScoreMapping.get(str[0]));
//            System.out.println(score[playersToScoreMapping.get(str[0])]);
            if (score[playersToScoreMapping.get(str[0])] == null) {
                score[playersToScoreMapping.get(str[0])] = str[0];
            }
        }

        int largestScore = Integer.MIN_VALUE;
        String name = null;

        for (Map.Entry<String, Integer> entry : playersToScoreMapping.entrySet()) {
            int s = entry.getValue();
            if (s > largestScore) {
                largestScore = s;
                name = entry.getKey();
            }
        }

        System.out.println(playersToScoreMapping);
        System.out.println(largestScore);
        System.out.println(score[largestScore]);


        for (int i = score.length - 1; i >= 0; i--) {
            if (score[i] != null) {
                if (i == largestScore) {
                    System.out.print(score[i]);
                    return;
                }
            }
        }


    }
}

