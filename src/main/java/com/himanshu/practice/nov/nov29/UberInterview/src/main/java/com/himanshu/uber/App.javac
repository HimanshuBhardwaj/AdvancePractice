package com.himanshu.uber;

import lombok.extern.slf4j.Slf4j;

/**
 * Hello world!
 */

@Slf4j
public class App {
    public static void main(String[] args) throws Exception {
        Scheduler scheduler = new Scheduler(10);
        Example example = new Example();
        //scheduler.runTask(example,System.currentTimeMillis()+1000);
        scheduler.scheduleWithFixedDelay(example, 1000);


        Thread.sleep(1000000);


    }
}


class Example implements Runnable {

    @Override
    public void run() {
        System.out.println("Hello World");
    }
}