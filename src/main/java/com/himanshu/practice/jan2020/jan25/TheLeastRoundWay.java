package com.himanshu.practice.jan2020.jan25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * @author Himanshu Bhardwaj
 * Date 01/Feb/2020
 */
public class TheLeastRoundWay {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int mat[][] = new int[n][n];
        int zr = -1;
        int zc = -1;

        HashSet<Pos> violatedPos = new HashSet<>();


        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split(" ");
            for (int j = 0; j < str.length; j++) {
                mat[i][j] = Integer.parseInt(str[j]);
                if (mat[i][j] == 0) {
                    mat[i][j] = 10;
                    violatedPos.add(new Pos(i, j));
                    if (zc == -1) {
                        zr = i;
                        zc = j;
                    }
                }
            }
        }


        LeastValuePath leastValuePath2 = new LeastValuePath(mat, 2);
        LeastValuePath leastValuePath5 = new LeastValuePath(mat, 5);
        leastValuePath2.compute(violatedPos);
        leastValuePath5.compute(violatedPos);


        if (zc == -1) {
            int min = Math.min(leastValuePath2.count, leastValuePath5.count);
            System.out.println(min);
            if (leastValuePath2.count < leastValuePath5.count) {
                System.out.println(leastValuePath2.path.toString());
            } else {
                System.out.println(leastValuePath5.path.toString());
            }
        } else {
            int min = Math.min(leastValuePath2.count, leastValuePath5.count);
            if (min > 1) {
                System.out.println(1);
                System.out.println(LeastValuePath.printPAth(zr, zc, n));
            } else {
                System.out.println(min);
                if (leastValuePath2.count < leastValuePath5.count) {
                    System.out.println(leastValuePath2.path.toString());
                } else {
                    System.out.println(leastValuePath5.path.toString());
                }
            }
        }


    }
}

class LeastValuePath {
    int count;
    StringBuilder path;
    int[][] matrix;
    int[][] dp;
    int value;

    public LeastValuePath(int[][] mat, int value) {
        this.matrix = mat;
        this.value = value;
    }

    void compute(HashSet<Pos> violatedPos) {
        dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                dp[i][j] = numberOFMultiples(matrix[i][j]);
                if (i > 0 && j > 0) {
                    dp[i][j] += Math.min(dp[i - 1][j], dp[i][j - 1]);
                } else if (i > 0) {
                    dp[i][j] += dp[i - 1][j];
                } else if (j > 0) {
                    dp[i][j] += dp[i][j - 1];
                }
            }
        }
        this.count = dp[dp.length - 1][dp.length - 1];
        computeMinimumPath(violatedPos);
    }

    int numberOFMultiples(int num) {
        int count = 0;
        while (num > 1) {
            if (num % value == 0) {
                count++;
                num = num / value;
            } else {
                break;
            }
        }
        return count;
    }

    void printDP() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println();
        }
    }

    void computeMinimumPath(HashSet<Pos> violatedPos) {
        int minimum = dp[dp.length - 1][dp.length - 1];
        int positionR = dp.length - 1;
        int positionC = dp.length - 1;
        path = new StringBuilder();


        while (positionR > 0 || positionC > 0) {
            minimum -= numberOFMultiples(matrix[positionR][positionC]);
            if (positionR > 0 && positionC > 0) {
                if (dp[positionR - 1][positionC] == minimum && !violatedPos.contains(new Pos(positionR - 1, positionC))) {
                    path.append("D");
                    positionR--;
                } else {
                    path.append("R");
                    positionC--;
                }
            } else if (positionC > 0) {
                path.append("R");
                positionC--;
            } else {
                path.append("D");
                positionR--;
            }
        }

        path = path.reverse();
    }

    static String printPAth(int r, int c, int n) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < r; i++) {
            sb.append("D");
        }
        for (int i = 0; i < (n - 1); i++) {
            sb.append("R");
        }

        for (int i = 0; i < (n - 1 - r); i++) {
            sb.append("D");
        }
        return sb.toString();
    }

}


class Pos implements Comparable<Pos> {
    int r;
    int c;

    @java.beans.ConstructorProperties({"r", "c"})
    public Pos(int r, int c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public int compareTo(Pos o) {
        if (this.r != o.r) {
            return Integer.compare(this.r, o.r);
        } else {
            return Integer.compare(this.c, o.c);
        }
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Pos)) return false;
        final Pos other = (Pos) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.r != other.r) return false;
        if (this.c != other.c) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Pos;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.r;
        result = result * PRIME + this.c;
        return result;
    }
}