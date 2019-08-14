package com.himanshu.practice.july.july23;

import java.util.concurrent.Semaphore;

public class SemophorePOC {
    public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(3);
        System.out.println("IsFair: "+semaphore.isFair());
        acquire(semaphore);
        System.out.println("Free Instances: "+semaphore.availablePermits());
        acquire(semaphore);
        System.out.println("Free Instances: "+semaphore.availablePermits());
        acquire(semaphore);


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    System.out.println("Sleeping for 10000 ms");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                release(semaphore);
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        System.out.println("Free Instances: "+semaphore.availablePermits());
        System.out.println("Waiting");
        acquire(semaphore);
        System.out.println("End");




    }

    static void acquire(Semaphore semaphore) throws InterruptedException {
        semaphore.acquire();
        System.out.println("Acquired");
    }

    static void release(Semaphore semaphore) {
        semaphore.release();
        System.out.println("Release");
    }


}
