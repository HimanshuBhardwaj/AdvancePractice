package com.himanshu.practice.aug.aug14;


import sun.awt.Mutex;

import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;
/*
Conclusion:
    Wait notify is faster than mutex
    speed factor: 18000/150 = 120 times
* */
public class ThreadSync {
    public static void main(String[] args) throws InterruptedException {
        int n = 200;
        AtomicInteger atomicInteger = new AtomicInteger(0);
        Mutex mutex = new Mutex();
        Thread[] threads = new Thread[n];
        PrintWriter printWriter = new PrintWriter(System.out);

        for (int i = 0; i < n; i++) {
            threads[i] = new Thread(new ThreadPOCDCL(i, atomicInteger, mutex, n, printWriter));
        }

        for (int i = 0; i < n; i++) {
            threads[i].start();
        }

        Thread.sleep(10000);

        for (int i = 0; i < n; i++) {
            threads[i].interrupt();
        }
        printWriter.flush();

    }

}


class ThreadPOCDCL implements Runnable {
    private int threadId;
    private AtomicInteger value;
    private Mutex mutex;
    private int numberThread;
    private PrintWriter printWriter;

    public ThreadPOCDCL(int threadId, AtomicInteger atomicInteger, Mutex mutex, int n, PrintWriter printWriter) {
        this.threadId = threadId;
        this.value = atomicInteger;
        this.mutex = mutex;
        this.numberThread = n;
        this.printWriter = printWriter;
    }


    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                if (value.intValue() % numberThread == threadId) {
                    mutex.lock();
                    try {
                        if (value.intValue() % numberThread == threadId) {
                            printWriter.append(Thread.currentThread().getId() + "\t" + threadId + "\t" + value.getAndIncrement() + "\n");
                        }
                    } catch (Exception e) {
                        //  e.printStackTrace();

                    } finally {
                        mutex.unlock();
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class ThreadPOCWaitNotify implements Runnable {
    private int threadId;
    private AtomicInteger value;
    private Mutex mutex;
    private int numberThread;
    private PrintWriter printWriter;

    public ThreadPOCWaitNotify(int threadId, AtomicInteger atomicInteger, Mutex mutex, int n, PrintWriter printWriter) {
        this.threadId = threadId;
        this.value = atomicInteger;
        this.mutex = mutex;
        this.numberThread = n;
        this.printWriter = printWriter;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                synchronized (value) {
                    if (value.intValue() % numberThread == threadId) {
                        System.out.println(Thread.currentThread().getId() + "\t" + threadId + "\t" + value.getAndIncrement());
                        value.notifyAll();
                    } else {
                        value.wait();
                    }
                }
            } catch (InterruptedException e) {
//                e.printStackTrace();
            }
        }
    }
}