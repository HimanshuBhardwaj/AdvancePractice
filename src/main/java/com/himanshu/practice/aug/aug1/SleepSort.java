package com.himanshu.practice.aug.aug1;

import lombok.AllArgsConstructor;

import static java.lang.Thread.sleep;

@AllArgsConstructor
public class SleepSort implements Runnable {
    int number;


    @Override
    public void run() {
        try {
            sleep(number*10);
            System.out.print(number + " ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        Thread[] array = new Thread[100];

        for (int i = 0; i < array.length; i++) {
            array[i] = new Thread(new SleepSort((int) (Math.random() * 1000)));
        }

        for (int i = 0; i < array.length; i++) {
            array[i].start();
        }
    }
}
