package com.himanshu.practice.feb2020.feb02.srm777;

/**
 * @author Himanshu Bhardwaj
 * Date 03/Feb/2020
 */
public class LimpingDog {
    public static void main(String[] args) {
        LimpingDog limpingDog = new LimpingDog();
        System.out.println(limpingDog.countSteps(59+42+1));
    }

    public int countSteps(int time) {
        int step = 0;
        int timeConsumed = 0;

        while (timeConsumed <= time) {
            if ((step % 4) == 2) {
                timeConsumed += 2;
            } else {
                timeConsumed++;
            }

            if (timeConsumed <= time) {
                step++;
            } else {
                break;
            }

            if ((step > 0) && ((step % 47) == 0)) {
                timeConsumed += 42;
            }

        }

        return step;

    }

}
