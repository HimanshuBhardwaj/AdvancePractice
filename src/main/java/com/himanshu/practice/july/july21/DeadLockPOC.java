package com.himanshu.practice.july.july21;

import lombok.Getter;

/**
 * Created by himanshubhardwaj on 21/07/19.
 */
public class DeadLockPOC {
    public static void main(String[] args) {
        DNumber dNumber = new DNumber();
        Test test = new Test(dNumber);

        synchronized (test) {
            test.increment();
            test.increment();
            test.increment();
            test.increment();
        }

    }
}

class Test {
    DNumber dNumber;

    public Test(DNumber dNumber) {
        this.dNumber = dNumber;
    }

    synchronized void increment() {
        System.out.println(dNumber.getNum());
        synchronized (this) {
            dNumber.increment();
        }
    }


}


@Getter
class DNumber {
    private volatile int num;

    public DNumber() {
        this.num = 0;
    }

    void increment() {
        //increment is not atomic
        num++;
    }
}


