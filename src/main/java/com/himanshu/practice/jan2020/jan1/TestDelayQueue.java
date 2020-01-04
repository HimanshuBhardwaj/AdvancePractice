package com.himanshu.practice.jan2020.jan1;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author Himanshu Bhardwaj
 * Date 03/Jan/2020
 */
public class TestDelayQueue {
    public static void main(String[] args) {
        DelayQueue<Task> queue = new DelayQueue<>();
    }
}


class Task implements Runnable, Delayed {


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());

    }

    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }
}