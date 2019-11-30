package com.himanshu.practice.nov.nov29;


//3:18

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class TheLeastRoundWay {
    public static void main(String[] args) throws IOException {
        String s = "RDRDRRRRRRDDDDDRRDDRDDDRDDDDRDRRRRRRDDRRDDRRRRDDRDDRDDRRDD";
        //System.out.println(s.length());
//        s = "DDDDDDDRRDDDDDDRDDDRDDDDDRDDDRDDDRRRRRRRRRRRRRRRRRRRRRDRRD";


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        Index[][] indices = new Index[n][n];
        int[][] a = new int[n][n];

        boolean containsZ = false;

        for (int i = 0; i < n; i++) {
            String[] str = bufferedReader.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                a[i][j] = Integer.parseInt(str[j]);
                if (a[i][j] == 0) {
                    containsZ = true;
                }
            }
        }


//        if (n == 30) {
//            printCost(a, s);
//            if (s != null) {
//                return;
//            }
//        }

        indices[0][0] = new Index(numT(a[0][0]), numF(a[0][0]));


        for (int i = 1; i < n; i++) {
            indices[0][i] = new Index(0, 0);
            indices[i][0] = new Index(0, 0);
            indices[0][i].numT = indices[0][i - 1].numT + numT(a[0][i]);
            indices[0][i].numF = indices[0][i - 1].numF + numF(a[0][i]);
            indices[i][0].numT = indices[i - 1][0].numT + numT(a[i][0]);
            indices[i][0].numF = indices[i - 1][0].numF + numF(a[i][0]);
        }


        int ti = -1;
        int tj = -1;


        HashSet<State> zeroStates = new HashSet<>();
        for (int i = 1; i < a.length; i++) {
            for (int j = 1; j < a.length; j++) {
                if (a[i][j] == 0) {
                    a[i][j] = 10;
                    ti = i;
                    tj = j;
                    zeroStates.add(new State(i, j));
                }
                if (Math.min(indices[i - 1][j].numT + numT(a[i][j]), indices[i - 1][j].numF + numF(a[i][j])) <
                        Math.min(indices[i][j - 1].numT + numT(a[i][j]), indices[i][j - 1].numF + numF(a[i][j]))) {
                    int two = indices[i - 1][j].numT + numT(a[i][j]);
                    int five = indices[i - 1][j].numF + numF(a[i][j]);
                    indices[i][j] = new Index(two, five);
                } else if (Math.min(indices[i - 1][j].numT + numT(a[i][j]), indices[i - 1][j].numF + numF(a[i][j])) >
                        Math.min(indices[i][j - 1].numT + numT(a[i][j]), indices[i][j - 1].numF + numF(a[i][j]))) {
                    int two = indices[i][j - 1].numT + numT(a[i][j]);
                    int five = indices[i][j - 1].numF + numF(a[i][j]);
                    indices[i][j] = new Index(two, five);
                } else {
                    int two = Math.min(indices[i - 1][j].numT, indices[i][j - 1].numT) + numT(a[i][j]);
                    int five = Math.min(indices[i - 1][j].numF, indices[i][j - 1].numF) + numF(a[i][j]);
                    indices[i][j] = new Index(two, five);
                }
            }
        }


        if (containsZ) {
            if (Math.min(indices[n - 1][n - 1].numF, indices[n - 1][n - 1].numT) == 0) {
                printMinPath(indices, n, a, zeroStates);
            } else {
                System.out.print(printZeroContainingPath(a, ti, tj));
            }
            return;
        }

        printMinPath(indices, n, a, zeroStates);


    }

    private static void printCost(int[][] a, String s) {
        int t = 0, f = 0;
        int i = 0, j = 0;

        for (int k = 0; k < s.length(); k++) {
            t += numT(a[i][j]);
            f += numF(a[i][j]);
            if (s.charAt(k) == 'R') {
                j++;
            } else {
                i++;
            }
        }
        System.out.println("(" + t + "," + f + ")");
    }

    private static void printMinPath(Index[][] indices, int n, int[][] a, HashSet<State> zeroStates) {
        State tState = new State(0, 0);
        //System.out.println(Math.min(indices[n - 1][n - 1].numT, indices[n - 1][n - 1].numF) + "...");
        //System.out.println();
        //System.out.println();


//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print("(" + indices[i][j].numT + "," + indices[i][j].numF + ")\t");
//            }
//            System.out.println();
//        }

        StringBuilder sb = new StringBuilder();
//        sb.append(Math.min(indices[n - 1][n - 1].numF, indices[n - 1][n - 1].numT) + "\n");

        int i = n - 1;
        int j = n - 1;

        while (i > 0 && j > 0) {
            tState.i = i - 1;
            tState.j = j;
            if (((!zeroStates.contains(tState)) &&
                    (indices[i][j].numT == (indices[i - 1][j].numT + numT(a[i][j]))) && (indices[i][j].numF == (indices[i - 1][j].numF + numF(a[i][j]))))) {
                sb.append("D");
                i = i - 1;
            } else {
                sb.append("R");
                j = j - 1;
            }
        }

        while (i > 0) {
            sb.append("D");
            i--;
        }

        while (j > 0) {
            sb.append("R");
            j--;
        }

        System.out.println(Math.min(indices[n - 1][n - 1].numF, indices[n - 1][n - 1].numT));
        System.out.println(sb.reverse().toString());
    }

    static int numT(int num) {
        if (num == 0) {
            return 10;
        }
        int count = 0;

        while (num % 2 == 0) {
            count++;
            num = num / 2;
        }

        return count;
    }

    static int numF(int num) {
        if (num == 0) {
            return 10;
        }

        int count = 0;

        while (num % 5 == 0) {
            count++;
            num = num / 5;
        }

        return count;
    }

    private static String printZeroContainingPath(int[][] a, int ti, int tj) {
        StringBuilder sb = new StringBuilder();
        sb.append(1 + "\n");

        int pos = tj;

        while (pos-- > 0) {
            sb.append("R");
        }

        pos = ti;

        while (pos-- > 0) {
            sb.append("D");
        }

        for (int k = 1; (k + tj) < a.length; k++) {
            sb.append("R");
        }

        for (int k = 1; (k + ti) < a.length; k++) {
            sb.append("D");
        }
        return sb.toString();
    }


}

class Index {
    int numT;
    int numF;

    @java.beans.ConstructorProperties({"numT", "numF"})
    public Index(int numT, int numF) {
        this.numT = numT;
        this.numF = numF;
    }
}

class State {
    int i;
    int j;

    @java.beans.ConstructorProperties({"i", "j"})
    public State(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof State)) return false;
        final State other = (State) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.i != other.i) return false;
        if (this.j != other.j) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof State;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.i;
        result = result * PRIME + this.j;
        return result;
    }
}
