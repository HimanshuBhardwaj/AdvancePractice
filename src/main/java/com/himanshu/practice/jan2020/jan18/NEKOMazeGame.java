package com.himanshu.practice.jan2020.jan18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * Date 20/Jan/2020
 * Submission: https://codeforces.com/contest/1293/submission/69184228
 * Algo: Ad-Hoc
 * Statement: https://codeforces.com/contest/1293/problem/C
 */
public class NEKOMazeGame {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");

        int n = Integer.parseInt(str[0]);
        int q = Integer.parseInt(str[1]);

        Game game = new Game(n);
        PrintWriter pw = new PrintWriter(System.out);

        while (q-- > 0) {
            str = br.readLine().split(" ");
            int r = Integer.parseInt(str[0]);
            int c = Integer.parseInt(str[1]);
            game.insert(r, c);
            pw.append((game.isPossible()) ? "Yes\n" : "No\n");
        }
        pw.flush();
        pw.close();
    }
}

class Game {
    int[][] grid;
    int numberVerticalBar;
    int n;

    public Game(int n) {
        grid = new int[2][n];
        numberVerticalBar = 0;
        this.n = n;
    }


    boolean isPossible() {
        return numberVerticalBar == 0;
    }

    void insert(int r, int c) {
        r--;
        c--;
        if (grid[r][c] == 1) {
            grid[r][c] = 0;
            if (grid[(r + 1) % 2][c] == 1) {
                numberVerticalBar--;
            }
            if (c > 0) {
                if (grid[(r + 1) % 2][c - 1] == 1) {
                    numberVerticalBar--;
                }
            }
            if (c + 1 < n) {
                if (grid[(r + 1) % 2][c + 1] == 1) {
                    numberVerticalBar--;
                }
            }


        } else {
            grid[r][c] = 1;
            if (grid[(r + 1) % 2][c] == 1) {
                numberVerticalBar++;
            }

            if (c > 0) {
                if (grid[(r + 1) % 2][c - 1] == 1) {
                    numberVerticalBar++;
                }
            }

            if (c + 1 < n) {
                if (grid[(r + 1) % 2][c + 1] == 1) {
                    numberVerticalBar++;
                }
            }
        }
    }
}
