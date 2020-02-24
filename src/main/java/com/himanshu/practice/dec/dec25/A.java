package com.himanshu.practice.dec.dec25;

/**
 * Created by himanshubhardwaj on 11/01/20.
 */
public class A {
    public static void main(String[] args) {
        String a = "OperateString";

        Thread threada = new Thread(new B(a));
        Thread threadb = new Thread(new B(a));

        threada.start();
        threadb.start();



    }
}


class B implements Runnable {
    String s;

    public B(String a) {
        this.s = a;
    }


    @Override
    public void run() {
        try {
            Thread.sleep(250);
            synchronized (s) {
                System.out.println("Lock taken by " + Thread.currentThread().getId());
                Thread.sleep(25000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}