package com.himanshu.practice.feb2020.feb9.atz;

import java.util.List;

/**
 * @author Himanshu Bhardwaj
 * Date 09/Feb/2020
 */
public class A {
    public static void main(String[] args) {

    }
}


class Solution {
    int nATT(int rows, int column,
             List<List<Integer>> grid) {
        int[][] adjList = new int[rows][column];

        int r = 0;
        for (List<Integer> list : grid) {
            int c = 0;
            for (int gc : list) {
                adjList[r][c] = gc;
                c++;
            }
            r++;
        }

        boolean[][] visited = new boolean[rows][column];
        int commectedComponent = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < column; j++) {
                if ((adjList[i][j] == 1) && (visited[i][j] == false)) {
                    commectedComponent++;
                    DFS(i, j, adjList, visited);
                }
            }
        }
        return commectedComponent;


    }

    private void DFS(int row, int column, int[][] adjList, boolean[][] visited) {
        if (row < 0 || row >= adjList.length || column < 0 || column >= adjList[0].length || visited[row][column]||adjList[row][column]==0 ) {
            return;
        }
        visited[row][column] = true;
        DFS(row + 1, column, adjList, visited);
        DFS(row - 1, column, adjList, visited);
        DFS(row, column + 1, adjList, visited);
        DFS(row, column - 1, adjList, visited);
    }
}