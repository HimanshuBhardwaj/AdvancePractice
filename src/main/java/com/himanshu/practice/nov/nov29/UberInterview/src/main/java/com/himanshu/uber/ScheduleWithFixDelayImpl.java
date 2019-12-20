package com.himanshu.uber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * @author Himanshu Bhardwaj
 * Date 09/Dec/2019
 */
public class ScheduleWithFixDelayImpl implements ScheduleWithFixDelay, Runnable {
    private TreeMap<Long, ArrayList<Runnable>> tasks;


    public ScheduleWithFixDelayImpl(TreeMap<Long, ArrayList<Runnable>> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void scheduleWithFixedDelay(Runnable task, long delay) {
        ScheduleWithFixDelayDecorator scheduleWithFixDelayDecorator = new ScheduleWithFixDelayDecorator(task, delay, tasks);
        Thread thread = new Thread(scheduleWithFixDelayDecorator);
        thread.start();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            long currentTime = System.currentTimeMillis();
            if (tasks.containsKey(currentTime)) {
                synchronized (tasks) {
                    if (tasks.containsKey(currentTime)) {
                        //this is double checked locking
                        for (Runnable runnable : tasks.get(currentTime)) {
                            ScheduleWithFixDelayDecorator scheduleWithFixDelayDecorator = (ScheduleWithFixDelayDecorator) runnable;
                            Thread thread = new Thread(scheduleWithFixDelayDecorator);
                            thread.start();
                        }
                    }
                }
            }

        }


    }
}
