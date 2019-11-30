package com.himanshu.practice.nov.nov29;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sun.awt.Mutex;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

//Each thread should print one by one
//Do it with mutex, semophore and wait notify
@Slf4j
public class SyncroniseAllthreads {
    public static void main(String[] args) throws InterruptedException {
        int numThread = 10;
        AtomicInteger value = new AtomicInteger(0);
        Mutex mutex = new Mutex();

        ArrayList<Thread> threadPOCS = new ArrayList<>();

        for (int i = 0; i < numThread; i++) {
            threadPOCS.add(new Thread(new ThreadPOC(i, value, mutex, numThread)));
        }

        for (int i = 0; i < numThread; i++) {
            threadPOCS.get(i).start();
        }

        Thread.sleep(10000);

        for (int i = 0; i < numThread; i++) {
            threadPOCS.get(i).interrupt();
        }


    }
}


@AllArgsConstructor
class ThreadPOC implements Runnable {
    int index;
    AtomicInteger value;
    Mutex mutex;
    int numthread;

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) try {
            System.out.println("\t\t" + index + "," + Thread.activeCount());
            if (value.get() % numthread == index) {
                System.out.println(index + "\t" + value.get());
                value.incrementAndGet();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}