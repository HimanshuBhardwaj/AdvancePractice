package com.himanshu.practice.feb2020.feb9.atz;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Himanshu Bhardwaj
 * Date 09/Feb/2020
 */
public class B {
    public static void main(String[] args) {
        int r = 5;
        int c = 5;

        int[][] arr = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                arr[i][j] = 1;
            }
        }

        arr[0][0] = 0;

        List<List<Integer>> list = new ArrayList<>();

        for (int i = 0; i < r; i++) {
            ArrayList<Integer> l = new ArrayList<>();

            for (int j = 0; j < c; j++) {
                l.add(arr[i][j]);
            }
            list.add(l);
        }

        System.out.println(new Solution2().mDs(r, c, list));


    }
}


class Solution2 {
    int mDs(int rows, int columns, List<List<Integer>> grid) {
        int[][] arr = new int[rows][columns];
        int numZero = 0;

        int r = 0;
        for (List<Integer> row : grid) {
            int c = 0;
            for (int cell : row) {
                arr[r][c] = cell;
                if (cell == 0) {
                    numZero++;
                }
                c++;
            }
            r++;
        }


//        System.out.println();
//        System.out.println();
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 0; j < arr[0].length; j++) {
//                System.out.print(arr[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("-----" + numZero);


        // WRITE YOUR CODE HERE

        return BFS(arr);
    }

    private int BFS(int[][] arr) {
        boolean[][] visited = new boolean[arr.length][arr[0].length];

        Queue<Pos> queue = new LinkedList<>();

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 1) {
                    visited[i][j] = true;
                    queue.add(new Pos(i, j));
                }
            }
        }

        int days = 0;


        while (queue.size() > 0) {
            Queue<Pos> queue1 = new LinkedList<>(queue);
            queue = new LinkedList<>();

            while (queue1.size() > 0) {
                Pos top = queue1.poll();

                if (isValid(top.r, top.column - 1, arr.length, arr[0].length, visited)) {
                    Pos pos = new Pos(top.r, top.column - 1);
                    visited[top.r][top.column - 1] = true;
                    queue.add(pos);
                }
                if (isValid(top.r, top.column + 1, arr.length, arr[0].length, visited)) {
                    Pos pos = new Pos(top.r, top.column + 1);
                    visited[top.r][top.column + 1] = true;
                    queue.add(pos);

                }
                if (isValid(top.r - 1, top.column, arr.length, arr[0].length, visited)) {
                    Pos pos = new Pos(top.r - 1, top.column);
                    visited[top.r - 1][top.column] = true;
                    queue.add(pos);

                }
                if (isValid(top.r + 1, top.column, arr.length, arr[0].length, visited)) {
                    Pos pos = new Pos(top.r + 1, top.column);
                    visited[top.r + 1][top.column] = true;
                    queue.add(pos);
                }
            }
            days++;
        }


        return days-1;
    }

    boolean isValid(int r, int c, int tR, int tC, boolean[][] isVisited) {
        if (r < 0 || r >= tR || c < 0 || c >= tC || isVisited[r][c]) {
            return false;
        }
        return true;
    }


    static class Pos {
        int r;
        int column;

        @java.beans.ConstructorProperties({"r", "column"})
        public Pos(int r, int column) {
            this.r = r;
            this.column = column;
        }

        public String toString() {
            return "Solution2.Pos(r=" + this.r + ", column=" + this.column + ")";
        }
    }

    private int update(int rows, int columns, int[][] arr) {
        int tA[][] = getClone(arr);


        int reduced = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (tA[i][j] == 1) {
                    if (change(i - 1, j, rows, columns, arr)) {
                        reduced++;
                    }
                    if (change(i + 1, j, rows, columns, arr)) {
                        reduced++;
                    }
                    if (change(i, j - 1, rows, columns, arr)) {
                        reduced++;
                    }
                    if (change(i, j + 1, rows, columns, arr)) {
                        reduced++;
                    }
                }
            }
        }

//        System.out.println();
//        System.out.println();
//        for (int i = 0; i < tA.length; i++) {
//            for (int j = 0; j < tA[0].length; j++) {
//                System.out.print(arr[i][j] + " ");
//            }
//            System.out.println();
//        }

        return reduced;
    }

    int[][] getClone(int[][] arr) {
        int[][] tA = new int[arr.length][arr[0].length];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                tA[i][j] = arr[i][j];
            }
        }
        return tA;
    }

    private boolean change(int r, int c, int tR, int tC, int[][] arr) {
        if (r < 0 || r >= tR || c < 0 || c >= tC || arr[r][c] == 1) {
            return false;
        }
        arr[r][c] = 1;
        return true;
    }
}