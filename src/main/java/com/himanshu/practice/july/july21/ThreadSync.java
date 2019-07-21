package com.himanshu.practice.july.july21;


import com.sun.corba.se.impl.orbutil.concurrent.Mutex;
import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by himanshubhardwaj on 21/07/19.
 * You have n threads, make each thread print value 0,1,2,.. till end
 */
public class ThreadSync {
    public static void main(String[] args) throws InterruptedException {
        int numThread = 3;
        Number number = new Number();
        Mutex mutex = new Mutex();

//        Thread thread1 = new Thread(new ThreadDoubleCheckedLocking(numThread, 0, number, mutex));
//        Thread thread2 = new Thread(new ThreadDoubleCheckedLocking(numThread, 1, number, mutex));
//        Thread thread3 = new Thread(new ThreadDoubleCheckedLocking(numThread, 2, number, mutex));
//
//        thread3.start();
//        thread2.start();
//        thread1.start();
//
//        Thread.sleep(50);
//        thread3.interrupt();
//        thread1.interrupt();
//        thread2.interrupt();


        Thread thread1 = new Thread(new WaitNotify(numThread, 0, number, mutex));
        Thread thread2 = new Thread(new WaitNotify(numThread, 1, number, mutex));
        Thread thread3 = new Thread(new WaitNotify(numThread, 2, number, mutex));

        thread3.start();
        thread2.start();
        thread1.start();

        Thread.sleep(50);
        thread1.interrupt();
        thread2.interrupt();
        thread3.interrupt();
    }
}

class ThreadDoubleCheckedLocking implements Runnable {
    private int threadIndex;
    private Mutex mutex;
    private int numThread;
    private volatile Number number;

    public ThreadDoubleCheckedLocking(int n, int index, Number number, Mutex mutex) {
        this.threadIndex = index;
        this.numThread = n;
        this.mutex = mutex;
        this.number = number;
    }


    @Override
    public void run() {
        System.out.println(String.format("Thread %s has started", this.threadIndex));
        while (!Thread.currentThread().isInterrupted()) {
            if (number.getNum() % numThread == threadIndex) {
                try {
                    mutex.acquire();
                    if (number.getNum() % numThread == threadIndex) {
                        System.out.println(this.threadIndex + "\t" + Thread.currentThread().getId() + "\t" + number.getNum());
                        number.increment();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    mutex.release();
                }
            }
        }
    }
}


class WaitNotify implements Runnable {
    private int threadIndex;
    private Mutex mutex;
    private int numThread;
    private volatile Number number;


    public WaitNotify(int n, int index, Number number, Mutex mutex) {
        this.threadIndex = index;
        this.numThread = n;
        this.mutex = mutex;
        this.number = number;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                synchronized (number) {
                    if (number.getNum() % numThread != threadIndex) {
                        number.wait();
                    }
                    if (number.getNum() % numThread == threadIndex) {
                        System.out.println(this.threadIndex + "\t" + Thread.currentThread().getId() + "\t" + number.getNum());
                        number.increment();
                        number.notifyAll();
//                    number.notify();
                    } else {
                        number.wait();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}


@Getter
class Number {
    private volatile int num;

    public Number() {
        this.num = 0;
    }

    void increment() {
        //increment is not atomic
        num++;
    }
}