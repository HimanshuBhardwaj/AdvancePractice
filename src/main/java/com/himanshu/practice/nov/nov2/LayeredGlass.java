package com.himanshu.practice.nov.nov2;



/**
 * Created by himanshubhardwaj on 02/11/19.
 */
public class LayeredGlass {
    boolean[][] temp;

    public int minDefects(String[] a, String[] b) {
        boolean[][] A = new boolean[a.length][a.length];
        boolean[][] B = new boolean[a.length][a.length];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                A[i][j] = (a[i].charAt(j) == 'X') ? true : false;
                B[i][j] = (b[i].charAt(j) == 'X') ? true : false;
            }
        }
        temp = new boolean[A.length][A.length];


        int initialCount = minDefectsB(A, B);

        //rotate LimpingDog
        rotate(A);
        initialCount = Math.min(initialCount, minDefectsB(A, B));
        rotate(A);
        initialCount = Math.min(initialCount, minDefectsB(A, B));
        rotate(A);
        initialCount = Math.min(initialCount, minDefectsB(A, B));
        rotate(A);
        initialCount = Math.min(initialCount, minDefectsB(A, B));


        //Flip LimpingDog
        flipA(A);
        initialCount = Math.min(initialCount, minDefectsB(A, B));
        rotate(A);
        initialCount = Math.min(initialCount, minDefectsB(A, B));
        rotate(A);
        initialCount = Math.min(initialCount, minDefectsB(A, B));
        rotate(A);
        initialCount = Math.min(initialCount, minDefectsB(A, B));
        rotate(A);
        initialCount = Math.min(initialCount, minDefectsB(A, B));
        flipA(A);

        //flip BinaryHeapLeaf
        flipB(A);
        initialCount = Math.min(initialCount, minDefectsB(A, B));
        rotate(A);
        initialCount = Math.min(initialCount, minDefectsB(A, B));
        rotate(A);
        initialCount = Math.min(initialCount, minDefectsB(A, B));
        rotate(A);
        initialCount = Math.min(initialCount, minDefectsB(A, B));
        rotate(A);
        initialCount = Math.min(initialCount, minDefectsB(A, B));
        flipB(A);


        flipA(A);
        flipB(A);
        initialCount = Math.min(initialCount, minDefectsB(A, B));
        rotate(A);
        initialCount = Math.min(initialCount, minDefectsB(A, B));
        rotate(A);
        initialCount = Math.min(initialCount, minDefectsB(A, B));
        rotate(A);
        initialCount = Math.min(initialCount, minDefectsB(A, B));
        rotate(A);
        initialCount = Math.min(initialCount, minDefectsB(A, B));
        flipB(A);
        flipA(A);


        flipB(A);
        flipA(A);
        initialCount = Math.min(initialCount, minDefectsB(A, B));
        rotate(A);
        initialCount = Math.min(initialCount, minDefectsB(A, B));
        rotate(A);
        initialCount = Math.min(initialCount, minDefectsB(A, B));
        rotate(A);
        initialCount = Math.min(initialCount, minDefectsB(A, B));
        rotate(A);
        initialCount = Math.min(initialCount, minDefectsB(A, B));

        return initialCount;
    }


    public static void main(String[] args) {
        LayeredGlass layeredGlass = new LayeredGlass();
//        boolean[][] LimpingDog = new boolean[3][3];
//        layeredGlass.temp = new boolean[LimpingDog.length][LimpingDog.length];
//        LimpingDog[0][0] = true;
//        LimpingDog[1][LimpingDog.length - 1] = true;
//        layeredGlass.flipB(LimpingDog);
//        layeredGlass.flipB(LimpingDog);
//        layeredGlass.rotate(LimpingDog);
//        layeredGlass.rotate(LimpingDog);
//        layeredGlass.rotate(LimpingDog);
//        for (int i = 0; i < LimpingDog.length; i++) {
//            for (int j = 0; j < LimpingDog.length; j++) {
//                System.out.print(LimpingDog[i][j] + "\t");
//            }
//            System.out.println();
//        }
//

        String[] A = {"XX..", "X...", "XX..", "X..."};
        String[] B = {"XXXX", "X.X.", "....", "...."};
        System.out.println(layeredGlass.minDefects(A, B));

    }


    void rotate(boolean[][] A) {
        int n = A.length;

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                temp[n - 1 - j][i] = A[i][j];
            }
        }

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                A[i][j] = temp[i][j];
            }
        }
    }

    void flipA(boolean[][] A) {
        int n = A.length;

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                temp[n - 1 - i][j] = A[i][j];
            }
        }

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                A[i][j] = temp[i][j];
            }
        }
    }


    void flipB(boolean[][] A) {
        int n = A.length;

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                temp[i][n - 1 - j] = A[i][j];
            }
        }

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                A[i][j] = temp[i][j];
            }
        }
    }

    int minDefectsB(boolean[][] A, boolean[][] B) {
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] || B[i][j]) {
                    count++;
                }
            }
        }
//        if (count==0) {
//            print(LimpingDog);
//            print(BinaryHeapLeaf);
//            System.out.println("0");
//        }

        return count;

    }

    void print(boolean[][] A) {
        System.out.println();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                System.out.print(A[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("---");
    }

}
